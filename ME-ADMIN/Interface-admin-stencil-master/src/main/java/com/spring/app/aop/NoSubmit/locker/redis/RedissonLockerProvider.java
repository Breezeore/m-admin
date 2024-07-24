package com.spring.app.aop.NoSubmit.locker.redis;


import com.spring.app.aop.NoSubmit.locker.core.LockerProvider;
import com.spring.app.aop.NoSubmit.locker.core.LockerService;

/**
 * @description: RedissonLockerProvider
 *
 * @author ITyunqing
 * @since 1.0.0
 */
public class RedissonLockerProvider implements LockerProvider {

    @Override
    public LockerService provider() {
        return RedissonDistributedLocker.getInstance();
    }
}
