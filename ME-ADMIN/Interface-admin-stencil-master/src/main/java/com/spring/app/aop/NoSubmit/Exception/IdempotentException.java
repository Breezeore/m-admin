package com.spring.app.aop.NoSubmit.Exception;

/**
 * @ClassName: IdempotentException
 * @Date: 2023/11/5 19:53
 * @Description: 幂等抛出异常
 */
public class IdempotentException extends RuntimeException{

    public IdempotentException() {
        super();
    }

    public IdempotentException(String message) {
        super(message);
    }

    public IdempotentException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdempotentException(Throwable cause) {
        super(cause);
    }

    protected IdempotentException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
