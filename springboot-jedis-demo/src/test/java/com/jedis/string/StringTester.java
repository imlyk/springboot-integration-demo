package com.jedis.string;

import com.jedis.BaseTester;
import org.junit.jupiter.api.Test;

/**
 * @ClassName: StringTester
 * @Author: lequal
 * @Date: 2022/10/31
 * @Description: 测试String类型的操作
 */
public class StringTester extends BaseTester {

    @Test
    void groupRun() {
        testAdd();
        testGet();
        testDelete();
    }

    @Test
    void testAdd() {
        jedis.set("redis:client", "jedis");

        jedis.set("redis:server", "redis-server");
    }

    @Test
    void testGet() {
        String client = jedis.get("redis:client");
        System.out.println("client-->" + client);
    }

    @Test
    void testDelete() {
        jedis.del("redis:client", "null");
    }
}
