package com.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @ClassName: UserServiceTester
 * @Author: lequal
 * @Date: 2022/10/31
 * @Description: TODO
 */
@SpringBootTest
public class UserServiceTester {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void test() {
        System.out.println(redisTemplate);
    }
}
