package com.jedis.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName: RedisConfiguration
 * @Author: lequal
 * @Date: 2023/03/21
 * @Description: Jedis配置
 *
 * 由于该客户端是线程不安全的，现在已经不建议再使用该客户端，因此只做简单的演示
 */
@ConfigurationProperties(prefix = "spring.redis")
@Configuration
@Setter
public class JedisConfiguration {

    private String host;
    private Integer port;
    private String password;
    private Integer timeout;

    @Bean("jedisClient")
    public JedisPool jedisPool() {
        JedisPool pool = new JedisPool(jedisPoolConfig(), host, port, timeout, password, false);
        return pool;
    }

    private JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setJmxEnabled(false);
        return config;
    }

}
