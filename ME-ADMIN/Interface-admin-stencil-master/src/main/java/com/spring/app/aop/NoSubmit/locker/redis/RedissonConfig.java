package com.spring.app.aop.NoSubmit.locker.redis;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: Redisson配置类
 *
 * @author ITyunqing
 * @since 1.0.0
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String address;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;



    @Bean(destroyMethod = "shutdown")
    public Redisson redisson() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + address + ":" +  port)
                .setPassword(password);
        return (Redisson) Redisson.create(config);
    }

}
