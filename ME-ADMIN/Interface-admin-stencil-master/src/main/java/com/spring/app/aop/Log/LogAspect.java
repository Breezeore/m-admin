package com.spring.app.aop.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @ClassName LogAspect
 * @Date 2023/7/27 23:27
 **/
@Aspect
@Component
public class LogAspect {

    /**
     * 定义@Before增强，拦截带有@Log注解的方法，并记录操作日志
     */
    @Before("@annotation(com.spring.app.aop.Log.Log)")
    public void before(JoinPoint joinPoint) throws Exception {
        // 获取目标方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取目标方法参数
        Object[] args = joinPoint.getArgs();
        // 获取目标方法所在类
        String className = joinPoint.getTarget().getClass().getName();

        // 获取Log注解信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log logAnnotation = method.getAnnotation(Log.class);

        // 记录操作日志,存储到数据库
        //System.out.println(className);
        //System.out.println(methodName);
        //System.out.println(Arrays.toString(args));
        //System.out.println(logAnnotation.operation());
    }
}
