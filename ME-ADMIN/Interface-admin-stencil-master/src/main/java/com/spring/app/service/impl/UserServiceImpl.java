package com.spring.app.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.spring.app.mapper.system.UserMapper;
import com.spring.app.module.mail.EmailService;
import com.spring.app.module.mail.VerificationCodeService;
import com.spring.app.pojo.table.User;
import com.spring.app.prefix.RedisPrefix;
import com.spring.app.response.RespData;
import com.spring.app.response.RespMethod;
import com.spring.app.service.UserService;
import com.spring.app.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import static com.spring.app.pojo.table.table.UserTableDef.USER;


/**
 * @ClassName: AdminServiceImpl
 * @Date: 2023/10/29 1:12
 * @Description:
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RespMethod respMethod;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisPrefix redisPrefix;

    @Autowired
    private EmailService emailService;
    
    @Autowired
    private VerificationCodeService verificationCodeService;

    @Value("${sa-token.aes-secret-key}")
    private String aesSecretKey;

    @Override
    public RespData login(String username, String password) {
        password = SaSecureUtil.aesEncrypt(aesSecretKey,password);
        QueryWrapper queryWrapper = new QueryWrapper()
                .select(USER.ALL_COLUMNS)
                .from(USER)
                .where(USER.USERNAME.eq(username));

        User user = userMapper.selectOneByQuery(queryWrapper);
        //判断用户是否存在
        if (user != null) {

            //前置判断账号是否被锁定(超过五次账户被锁定十分钟)
            String key = redisPrefix.userLoginLock(username);
            //只要存了的就应该有值
            if (redisUtil.hasKey(key)) {
                if (Integer.parseInt(String.valueOf(redisUtil.get(key))) >= 5) {
                    //是否注销已经在线上且登录成功的账户
                    //StpUtil.login(user.getId());
                    long expire = redisUtil.getExpire(key);
                    //将expire转换为分钟
                    long minute = expire / 60;
                    minute = minute + 1;
                    return respMethod.loginErrorMessage("登录失败次数过多 | 请" + minute + "分钟后再试!");
                }
            }

            if(password.equals(user.getPassword())){
                //查看账户已经被封禁
                if(user.getStatus() == 1){
                    redisUtil.del(key);
                    return respMethod.loginErrorMessage("账号已被封禁 | 请联系管理员!");
                }else {
                    //没被封禁
                    redisUtil.del(key);
                    StpUtil.login(user.getId());
                    SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                    return respMethod.loginSuccess(tokenInfo.tokenValue);
                }
            }

            //后置密码错误添加账户锁定
            Object value = redisUtil.get(key);
            if(value == null){
                //没有存入
                redisUtil.set(key,1,600);
            }else {
                //有加一
                redisUtil.incr(key,1);
            }

        }
        //没查到用户不让登录
        return respMethod.loginError();
    }

    @Override
    public RespData logout() {
        StpUtil.logout();
        return respMethod.operateSuccessByMessage("退出成功");
    }

    @Override
    public RespData emailLoginCode(String email) {

        //emailCOdeKey
        String emailCodeKey = redisPrefix.emailCodePrefix(email);
        //加锁的key
        String emailCodeLockKey = redisPrefix.emailCodeLockKey(email);

        if (redisUtil.hasKey(emailCodeLockKey)) {
            return respMethod.operateErrorByMessage("邮箱验证码已发送 | 请稍后再试！");
        }


        //根据email查询用户
        QueryWrapper queryWrapper = new QueryWrapper()
                .select(USER.ALL_COLUMNS)
                .from(USER)
                .where(USER.EMAIL.eq(email));
        User user = userMapper.selectOneByQuery(queryWrapper);
        //判断用户是否存在
        if (user == null) {
            return respMethod.operateErrorByMessage("用户并不存在 | 请联系管理员！");
        }
        if (user.getStatus() == 1) {
            return respMethod.operateErrorByMessage("账户已被封禁 | 请联系管理员！");
        }

        //value
        String emailCode = verificationCodeService.generateVerificationCode();

        //存入邮箱验证格式
        HashMap<String, Object> map = new HashMap<>();
        map.put("email",email);
        map.put("code",emailCode);
        map.put("userId",user.getId());
        map.put("frequency",0);

        //存入redis 时长五分钟有效
        redisUtil.set(emailCodeKey,map,300);
        //锁住
        redisUtil.set(emailCodeLockKey,1,60);

        // 异步执行发送
        CompletableFuture.supplyAsync(() -> {
            try {
                emailService.sendEmailVerificationCode(email, "邮箱账户登录", emailCode);
                return "success"; // 返回占位符，表示异步任务执行成功
            } catch (Exception e) {
                throw new RuntimeException("error", e); // 返回占位符，表示异步任务执行失败
            }
        });

        return respMethod.operateSuccessByMessage("验证码已发送,请注意查收！验证码五分钟内有效！");
    }

    @Override
    public RespData emailLogin(String email, String code) {
        String emailCodeKey = redisPrefix.emailCodePrefix(email);
        //查询redis中的验证码
        if (!redisUtil.hasKey(emailCodeKey)){
            return respMethod.operateErrorByMessage("验证码不存在或已失效 | 请重新获取！");
        }
        HashMap<String, Object> map = (HashMap<String, Object>) redisUtil.get(emailCodeKey);
        //判断验证码是否正确
        if (!code.equalsIgnoreCase(map.get("code").toString())){
            //错误
            int frequency = Integer.parseInt(map.get("frequency").toString());
            if (frequency >= 5){
                //删除redis中的验证码
                redisUtil.del(emailCodeKey);
                return respMethod.operateErrorByMessage("失败次数过多 | 请重新获取！");
            }
            map.put("frequency",frequency + 1);
            redisUtil.set(emailCodeKey,map,redisUtil.getExpire(emailCodeKey));
            return respMethod.operateErrorByMessage("验证码错误或已失效！");
        }

        //登录成功
        StpUtil.login(map.get("userId"));
        //删除redis中的验证码
        redisUtil.del(emailCodeKey);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return respMethod.loginSuccess(tokenInfo.tokenValue);
    }
}
