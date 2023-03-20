package com.jedis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @ClassName: BaseTester
 * @Author: lequal
 * @Date: 2022/10/31
 * @Description: 测试基类
 */
public abstract class BaseTester {

    public static final JedisPool pool;
    protected Jedis jedis = null;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大连接数
        jedisPoolConfig.setMaxTotal(10);
        // 最大空闲连接数
        jedisPoolConfig.setMaxIdle(10);
        // 最大等待时间
        jedisPoolConfig.setMaxWait(Duration.ofSeconds(300));

        pool = new JedisPool(jedisPoolConfig, "192.168.58.212", 6379, 1000, "12345678");
    }


    /**
     * @Author: lequal
     * @Description @BeforeEach注解在每个方法执行之前都会执行一次，因此考虑在这里进行初始化
     * @Date 2022/10/31 15:04
     */
    @BeforeEach
    void initJedis() {
        jedis = pool.getResource();
        System.out.println("初始化Jedis完成...");
    }

    @AfterEach
    void close() {
        if (null != jedis) {
            jedis.close();
            System.out.println("Jedis连接已关闭...");
        }
    }
}
