package com.spring.app.service;

import com.spring.app.response.RespData;

/**
 * @ClassName: AdminService
 * @Date: 2023/10/29 1:10
 * @Description:
 **/
public interface UserService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    RespData login(String username, String password);

    /**
     * 注销
     * @return
     */
    RespData logout();

    /**
     * 邮箱登录获取验证码
     * @param email
     * @param code
     * @return
     */
    RespData emailLoginCode(String email);

    /**
     * 邮箱登录
     * @param email
     * @param code
     * @return
     */
    RespData emailLogin(String email, String code);
}
