package com.romanidze.studeeper.config.redis

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.data.redis.core.ReactiveStringRedisTemplate

/**
 * 06.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Configuration
class RedisConfig {

    @Bean
    fun redisOperations(
        factory: ReactiveRedisConnectionFactory
    ): ReactiveRedisOperations<String, String> {
        return ReactiveStringRedisTemplate(factory)
    }

}