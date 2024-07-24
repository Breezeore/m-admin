package com.spring.app.utils.DataProcessing;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName GenerateUtils
 * @Date 2023/6/5 17:17
 * @Author BREEZE
 **/
@Component
public class GenerateUtils {

    /**
     * 生成验证码
     * @param codeLength
     * @return
     */
    public static String generateCode(Integer codeLength){
        //定义验证码
        String code = "";
        //定义验证码的范围
        String codeRange = "0123456789";
        //定义随机数
        int random = 0;
        //循环生成验证码
        for (int i = 0; i < codeLength; i++) {
            //生成随机数
            random = (int) (Math.random() * codeRange.length());
            //拼接验证码
            code += codeRange.charAt(random);
        }
        return code;
    }


    /**
     * 获取当前时间，转换为yyyy-MM-dd
     * @return
     */
    public static String getNowDate(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 获取当前时间，转换为yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getNowDateTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }


    public static String getNowMonth() {
        return new SimpleDateFormat("yyyy-MM").format(new Date());
    }

}
