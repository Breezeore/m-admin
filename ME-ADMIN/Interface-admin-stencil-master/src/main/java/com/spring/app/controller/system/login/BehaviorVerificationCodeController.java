package com.spring.app.controller.system.login;

import cloud.tianai.captcha.common.constant.CaptchaTypeConstant;
import cloud.tianai.captcha.common.response.ApiResponse;
import cloud.tianai.captcha.spring.application.ImageCaptchaApplication;
import cloud.tianai.captcha.spring.vo.CaptchaResponse;
import cloud.tianai.captcha.spring.vo.ImageCaptchaVO;
import cloud.tianai.captcha.validator.common.model.dto.ImageCaptchaTrack;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @ClassName: BehaviorVerificationCodeController
 * @Date: 2023/10/31 21:28
 * 行为验证码检测
 * @Description:
 **/
@RestController
@RequestMapping("/verificationCode")
public class BehaviorVerificationCodeController {

    @Autowired
    private ImageCaptchaApplication application;

    @RequestMapping("/codeImg")
    @ResponseBody
    public CaptchaResponse<ImageCaptchaVO> genCaptcha(HttpServletRequest request, @RequestParam(value = "type", required = false)String type) {
        if (StringUtils.isBlank(type)) {
            type = CaptchaTypeConstant.SLIDER;
        }
        if ("RANDOM".equals(type)) {
            int i = ThreadLocalRandom.current().nextInt(0, 4);
            if (i == 0) {
                type = CaptchaTypeConstant.SLIDER;
            } else if (i == 1) {
                type = CaptchaTypeConstant.CONCAT;
            } else if (i == 2) {
                type = CaptchaTypeConstant.ROTATE;
            } else {
                type = CaptchaTypeConstant.WORD_IMAGE_CLICK;
            }
        }
        return application.generateCaptcha(type);
    }

    @PostMapping("/check")
    @ResponseBody
    public ApiResponse<?> checkCaptcha(@RequestBody Data data,
                                       HttpServletRequest request) {
        return application.matching(data.getId(), data.getData());
    }

    @lombok.Data
    public static class Data {
        private String  id;
        private ImageCaptchaTrack data;
    }

}
