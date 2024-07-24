package com.spring.app.pojo.table;

import com.mybatisflex.annotation.Table;
import com.spring.app.controller.system.login.validated.emailLogin;
import com.spring.app.controller.system.login.validated.userLogin;
import lombok.Data;

import javax.validation.constraints.*;


/**
 * @ClassName: Admin
 * @Date: 2023/10/29 1:07
 * @Description:
 **/
@Data
@Table("user")
public class User {

    private String id;

    private String name;

    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号格式不正确！")
    private String phone;

    @NotBlank(message = "邮箱不可为空！" , groups = emailLogin.class)//邮箱登录获取验证码参数校验
    @Email(message = "邮箱格式不正确！" , groups = emailLogin.class)//邮箱登录获取验证码参数校验
    private String email;

    @NotBlank(message = "用户名不可为空！" , groups = userLogin.class)
    private String username;

    @NotBlank(message = "密码不可为空！" , groups = userLogin.class)
    private String password;

    private String role;

    @Max(value = 1, message = "状态值不正确！")
    @Min(value = 0, message = "状态值不正确！")
    private Integer status;
}
