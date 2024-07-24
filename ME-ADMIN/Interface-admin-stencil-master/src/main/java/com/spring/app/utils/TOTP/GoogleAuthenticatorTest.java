package com.spring.app.utils.TOTP;

import com.google.zxing.WriterException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName GoogleAuthenticatorTest
 * @Date 2023/9/8 11:04
 **/
public class GoogleAuthenticatorTest {

    public static void main(String[] args) throws WriterException, IOException {

        // 生成随机的密钥
        String secretKey = GoogleAuthenticatorUtils.getRandomSecretKey();
        System.out.println("随机密钥：" + secretKey);

        // 根据验证码，账户，服务商生成 TOPT 密钥的 URI
        String uri = GoogleAuthenticatorUtils.getGoogleAuthenticatorBarCode(secretKey, "Web-three-wallet", "safety-verification");
        System.out.println("TOPT密钥URI：" + uri);

        // 根据 TOPT 密钥的 URI生成二维码，存储在本地
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\BREEZE\\Desktop\\Google.png");
        GoogleAuthenticatorUtils.createQRCode(uri, fileOutputStream, 200, 200);
        fileOutputStream.close();

        String lastCode = null;
        while (true) {
            // 根据密钥获取此刻的动态口令
            String code = GoogleAuthenticatorUtils.getTOTPCode(secretKey);
            if (!code.equals(lastCode)) {
                System.out.println("刷新了验证码：" + code + " " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
            }
            lastCode = code;
            try {
                Thread.sleep(1000);  // 线程暂停1秒
            } catch (InterruptedException e) {};
        }
    }

}
