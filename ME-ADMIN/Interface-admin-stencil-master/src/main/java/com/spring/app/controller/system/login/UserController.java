package com.spring.app.controller.system.login;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.spring.app.controller.system.login.validated.emailLogin;
import com.spring.app.controller.system.login.validated.userLogin;
import com.spring.app.pojo.table.User;
import com.spring.app.response.RespData;
import com.spring.app.response.RespMethod;
import com.spring.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName: AdminController
 * @Date: 2023/10/29 1:10
 * @Description:
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService adminService;

    @Autowired
    private RespMethod respMethod;

    /**
     * 用户登录
     * @param
     * @return
     */
    @PostMapping("/login")
    public RespData login(@RequestBody @Validated(value = userLogin.class) User user) {
        //正则表达式 长度大于8位且小于20位才行：问：为什么要在这里写，为什么不直接在Validated参数校验用注解验证，是为了返回模糊信息，增加后台被爆破的成本！
        if (!(user.getPassword().length() >= 8 && user.getPassword().length() <= 20)) {
            return respMethod.loginError();
        }
        return adminService.login(user.getUsername(), user.getPassword());
    }

    /**
     * 用户注销
     * @return
     */
    @GetMapping("/logout")
    @SaCheckLogin
    public RespData logout() {
        return adminService.logout();
    }

    /**
     * 邮箱登录获取邮箱验证码
     * @param
     * @return
     */
    @PostMapping("/email/code")
    public RespData emailLoginCode(@RequestBody @Validated(value = emailLogin.class) User user) {
        return adminService.emailLoginCode(user.getEmail());
    }


    /**
     * 邮箱登录获取邮箱验证码
     * @param
     * @return
     */
    @PostMapping("/email/login")
    public RespData emailLogin(@RequestBody Map<String, String> map) {
        String email = map.get("email");
        String code = map.get("code");
        //对邮箱进行正则表达式验证
        if (!email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")) {
            return respMethod.loginError();
        }
        if (code.length() != 6) {
            return respMethod.loginError();
        }

        return adminService.emailLogin(email, code);
    }


}
