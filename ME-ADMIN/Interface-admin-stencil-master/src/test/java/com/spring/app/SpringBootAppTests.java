package com.spring.app;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.spring.app.module.mail.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.UnknownHostException;

@SpringBootTest
class SpringBootAppTests {

    @Autowired
    private EmailService emailService;

    @Value("${sa-token.aes-secret-key}")
    private String aesSecretKey;


    @Test
    void contextLoads() throws UnknownHostException {
        String ciphertext = SaSecureUtil.aesEncrypt(aesSecretKey, "admin@admin");
        System.out.println(ciphertext);
    }

}
