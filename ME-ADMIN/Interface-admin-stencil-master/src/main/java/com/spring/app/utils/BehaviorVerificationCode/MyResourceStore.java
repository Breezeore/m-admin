package com.spring.app.utils.BehaviorVerificationCode;

import cloud.tianai.captcha.common.constant.CaptchaTypeConstant;
import cloud.tianai.captcha.generator.common.constant.SliderCaptchaConstant;
import cloud.tianai.captcha.generator.impl.StandardSliderImageCaptchaGenerator;
import cloud.tianai.captcha.resource.common.model.dto.Resource;
import cloud.tianai.captcha.resource.common.model.dto.ResourceMap;
import cloud.tianai.captcha.resource.impl.DefaultResourceStore;
import cloud.tianai.captcha.resource.impl.provider.ClassPathResourceProvider;
import org.springframework.stereotype.Component;

import static cloud.tianai.captcha.generator.impl.StandardSliderImageCaptchaGenerator.DEFAULT_SLIDER_IMAGE_TEMPLATE_PATH;

/**
 * @ClassName MyResourceStore
 * @Date 2023/10/17 14:51
 * @Version 1.0
 **/
@Component
public class MyResourceStore extends DefaultResourceStore {

    public MyResourceStore() {

        // 滑块验证码 模板 (系统内置)
        ResourceMap template1 = new ResourceMap("default",4);
        template1.put(SliderCaptchaConstant.TEMPLATE_ACTIVE_IMAGE_NAME, new Resource(ClassPathResourceProvider.NAME, DEFAULT_SLIDER_IMAGE_TEMPLATE_PATH.concat("/1/active.png")));
        template1.put(SliderCaptchaConstant.TEMPLATE_FIXED_IMAGE_NAME, new Resource(ClassPathResourceProvider.NAME, DEFAULT_SLIDER_IMAGE_TEMPLATE_PATH.concat("/1/fixed.png")));
        ResourceMap template2 = new ResourceMap("default",4);
        template2.put(SliderCaptchaConstant.TEMPLATE_ACTIVE_IMAGE_NAME, new Resource(ClassPathResourceProvider.NAME, DEFAULT_SLIDER_IMAGE_TEMPLATE_PATH.concat("/2/active.png")));
        template2.put(SliderCaptchaConstant.TEMPLATE_FIXED_IMAGE_NAME, new Resource(ClassPathResourceProvider.NAME, DEFAULT_SLIDER_IMAGE_TEMPLATE_PATH.concat("/2/fixed.png")));
        // 旋转验证码 模板 (系统内置)
        ResourceMap template3 = new ResourceMap("default",4);
        template3.put(SliderCaptchaConstant.TEMPLATE_ACTIVE_IMAGE_NAME, new Resource(ClassPathResourceProvider.NAME, StandardSliderImageCaptchaGenerator.DEFAULT_SLIDER_IMAGE_TEMPLATE_PATH.concat("/3/active.png")));
        template3.put(SliderCaptchaConstant.TEMPLATE_FIXED_IMAGE_NAME, new Resource(ClassPathResourceProvider.NAME, StandardSliderImageCaptchaGenerator.DEFAULT_SLIDER_IMAGE_TEMPLATE_PATH.concat("/3/fixed.png")));

        // 1. 添加一些模板
        addTemplate(CaptchaTypeConstant.SLIDER, template1);
        addTemplate(CaptchaTypeConstant.SLIDER, template2);
        addTemplate(CaptchaTypeConstant.ROTATE, template3);

        // 2. 添加自定义背景图片
        addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/a.png","default"));
        addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/b.png","default"));
        addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/c.png","default"));
        addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/d.png","default"));
        addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/e.png","default"));
        addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/f.png","default"));
        addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/g.png","default"));
        addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/h.png","default"));
        addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/i.png","default"));
        addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/j.png","default"));
        addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/k.png","default"));
        addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/l.png","default"));
        addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/m.png","default"));
        addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/n.png","default"));
        addResource(CaptchaTypeConstant.WORD_IMAGE_CLICK, new Resource("classpath", "bgimages/a.png","default"));
        addResource(CaptchaTypeConstant.WORD_IMAGE_CLICK, new Resource("classpath", "bgimages/b.png","default"));
        addResource(CaptchaTypeConstant.WORD_IMAGE_CLICK, new Resource("classpath", "bgimages/c.png","default"));
        addResource(CaptchaTypeConstant.WORD_IMAGE_CLICK, new Resource("classpath", "bgimages/d.png","default"));
        addResource(CaptchaTypeConstant.WORD_IMAGE_CLICK, new Resource("classpath", "bgimages/e.png","default"));
        addResource(CaptchaTypeConstant.WORD_IMAGE_CLICK, new Resource("classpath", "bgimages/f.png","default"));
        addResource(CaptchaTypeConstant.WORD_IMAGE_CLICK, new Resource("classpath", "bgimages/g.png","default"));
        addResource(CaptchaTypeConstant.WORD_IMAGE_CLICK, new Resource("classpath", "bgimages/h.png","default"));
        addResource(CaptchaTypeConstant.WORD_IMAGE_CLICK, new Resource("classpath", "bgimages/i.png","default"));
        addResource(CaptchaTypeConstant.WORD_IMAGE_CLICK, new Resource("classpath", "bgimages/j.png","default"));
        addResource(CaptchaTypeConstant.WORD_IMAGE_CLICK, new Resource("classpath", "bgimages/k.png","default"));
        addResource(CaptchaTypeConstant.WORD_IMAGE_CLICK, new Resource("classpath", "bgimages/l.png","default"));
        addResource(CaptchaTypeConstant.WORD_IMAGE_CLICK, new Resource("classpath", "bgimages/m.png","default"));
        addResource(CaptchaTypeConstant.WORD_IMAGE_CLICK, new Resource("classpath", "bgimages/n.png","default"));
    }
}
