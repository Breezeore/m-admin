package com.spring.app.module.mail.Impl;


import com.spring.app.module.mail.EmailService;
import com.spring.app.module.mail.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: EmailServiceImpl
 * @Date: 2023/11/9 16:41
 * @Description:
 **/
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 邮件发件人
     */
    @Value("${mail.fromMail.fromAddress}")
    private String fromAddress;

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    private VerificationCodeService verificationCodeService;


    @Override
    public boolean sendEmailVerificationCode(String toAddress, String content, String verifyCode) {
        // 创建邮件正文
        Context context = new Context();
        List<String> verifyCodeList = new ArrayList<>(verifyCode.length());
        for (int i = 0; i < verifyCode.length(); i++) {
            verifyCodeList.add(String.valueOf(verifyCode.charAt(i)));
        }
        context.setVariable("verifyCode", verifyCodeList);
        context.setVariable("content", content);

        // 将模块引擎内容解析成html字符串
        String emailContent = templateEngine.process("EmailVerificationCode", context);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            // true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromAddress);
            helper.setTo(toAddress);
            helper.setSubject(content);
            helper.setText(emailContent, true);
            mailSender.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
}
