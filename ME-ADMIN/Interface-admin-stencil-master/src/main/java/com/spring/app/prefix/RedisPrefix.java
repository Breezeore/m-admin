package com.spring.app.prefix;

import org.springframework.stereotype.Component;

/**
 * @ClassName: RedisPrefix
 * @Date: 2023/11/10 10:55
 * @Description:
 **/
@Component
public class RedisPrefix {

    /**
     * 邮箱验证码前缀
     * @param email
     * @return
     */
    public String emailCodePrefix(String email){
        return "emailCodePrefix=>：" + email;
    }

    /**
     * 邮箱验证码锁
     * @param email
     * @return
     */
    public String emailCodeLockKey(String email) {
        return "emailCodeLockKey=>：" + email;
    }

    /**
     * 用户登录锁,多次登录失败后锁定
     * @param username
     * @return
     */
    public String userLoginLock(String username){
        return  "meAdminUserLoginLock=>：" + username;
    }


}
