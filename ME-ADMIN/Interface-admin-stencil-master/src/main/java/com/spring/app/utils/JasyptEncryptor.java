package com.spring.app.utils;

import org.jasypt.properties.PropertyValueEncryptionUtils;
import org.jasypt.util.text.BasicTextEncryptor;


/**
 * @ClassName JasyptEncryptor
 * @Date 2023/10/17 14:51
 * @Version 1.0
 **/
public class JasyptEncryptor {

    private static final String salt = "XXXXXXXXXXXXXXXXXXXXx";

    private static final BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();

    static {    
        basicTextEncryptor.setPassword(salt);
    }

    private JasyptEncryptor(){}

    /**
     * 明文加密
     * @param plaintext
     * @return
     */
    public static String encode(String plaintext){
        System.out.println("明文字符串：" + plaintext);
        String ciphertext = basicTextEncryptor.encrypt(plaintext);
        System.out.println("加密后字符串：" + ciphertext);
        return ciphertext;
    }

    public static void main(String[] args) {
        String encode = encode("47.97.110.82");
        decode(encode);
    }

    /**
     * 解密
     * @param ciphertext
     * @return
     */
    public static String decode(String ciphertext){
        System.out.println("加密字符串：" + ciphertext);
        ciphertext = "ENC(" + ciphertext + ")";
        if (PropertyValueEncryptionUtils.isEncryptedValue(ciphertext)){
            String plaintext = PropertyValueEncryptionUtils.decrypt(ciphertext,basicTextEncryptor);
            System.out.println("解密后的字符串：" + plaintext);
            return plaintext;
        }
        System.out.println("解密失败");
        return "";
    }
}
