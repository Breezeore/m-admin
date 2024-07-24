package com.spring.app.module.mail;

/**
 * @ClassName: EmailService
 * @Date: 2023/11/9 16:41
 * @Description:
 **/
public interface EmailService {

    boolean sendEmailVerificationCode(String toAddress,String content,String verifyCode);
}
