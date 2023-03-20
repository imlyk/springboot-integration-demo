package com.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @ClassName: RedisConfig
 * @Author: lequal
 * @Date: 2022/10/31
 * @Description:
 */
@Configuration
public class RedisConfig {


    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        // JSON序列化
//        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();

//        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // key使用String
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());

        // value使用JSON序列化
//        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setValueSerializer(RedisSerializer.json());
        redisTemplate.setHashValueSerializer(RedisSerializer.json());

        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

}
