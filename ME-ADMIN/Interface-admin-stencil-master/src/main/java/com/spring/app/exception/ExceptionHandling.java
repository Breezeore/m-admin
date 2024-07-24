package com.spring.app.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.spring.app.aop.NoSubmit.Exception.IdempotentException;
import com.spring.app.response.RespData;
import com.spring.app.response.RespMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * @ClassName ExceptionProcessingCenter
 * @Date 2023/7/26 9:05
 **/
@Slf4j
@RestControllerAdvice
public class ExceptionHandling {
    @Autowired
    private RespMethod respMethod;

    /**
     * 幂等性异常判断抛出
     * @param e
     * @return
     */
    @ExceptionHandler(IdempotentException.class)
    public RespData handleIdempotentException(IdempotentException e) {
        return respMethod.operateErrorByMessage(e.getMessage());
    }

    /**
     * 请求参数或请求方式错误：例如有傻逼post请求写成了get请求
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public RespData handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return respMethod.operateErrorByMessage("参数或请求方式错误!");
    }

    /**
     * 参数验证异常(前端json格式提交)
     *
     * @param e 异常
     * @return 响应实体
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RespData handleParameterException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String messages = bindingResult.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("；"));
        return respMethod.parameterCheck(messages);
    }

    /**
     * 参数验证异常(前端form表单格式提交)
     *
     * @param e 异常
     * @return http响应对象
     */
    @ExceptionHandler(BindException.class)
    public RespData handlerBindException(BindException e) {
        String messages = e.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("；"));
        return respMethod.parameterCheck(messages);
    }


    // 拦截：未登录异常
    @ExceptionHandler(NotLoginException.class)
    public RespData handlerException(NotLoginException e) {
        // 返回给前端
        return respMethod.identityError();
    }

    // 拦截：缺少权限异常
    @ExceptionHandler(NotPermissionException.class)
    public RespData handlerException(NotPermissionException e) {
        // 返回给前端
        return respMethod.roleError();
    }

    // 拦截：缺少角色异常
    @ExceptionHandler(NotRoleException.class)
    public RespData handlerException(NotRoleException e) {
        // 返回给前端
        return respMethod.powerError();
    }

    //其他异常
    @ExceptionHandler
    public RespData ControllerExceptionGetAll(Exception exception) {
        //打印报错
        exception.printStackTrace();
        // 返回给前端
        return respMethod.systemError();
    }
}
