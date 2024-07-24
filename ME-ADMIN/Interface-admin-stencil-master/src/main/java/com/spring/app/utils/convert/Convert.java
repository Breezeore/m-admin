package com.spring.app.utils.convert;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @ClassName: fromJavaBean
 * @Date: 2023/11/6 16:00
 * @Description:
 **/


public class Convert {
    public static Map<String, Object> fromJavaBean(Object bean) {
        if (null == bean) return null;

        try {
            Map<String, Object> map = BeanUtils.describe(bean);
            // 移除key=class
            map.remove("class");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T toJavaBean(Class<?> clazz, Map map) {
        try {
            T newBeanInstance = (T) clazz.newInstance();
            BeanUtils.populate(newBeanInstance, map);
            return newBeanInstance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}