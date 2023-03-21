package com.jedis.string;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jedis.BaseTester;
import com.jedis.common.Constants;
import com.jedis.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @ClassName: CustomTest
 * @Author: lequal
 * @Date: 2023/03/21
 * @Description:
 */
@SpringBootTest
public class CustomTest extends BaseTester {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void saveString() {
        final String phone = "13022225555";
        String key = Constants.CODE + phone;
        // 模拟手机验证码
        jedis.set(key, "125800");
        // 成功存入数据

        System.out.println("获取到redis中保存的数据" + jedis.get(key));
    }

    @Test
    void testHash() throws JsonProcessingException {
        User lequal = new User(1, "lequal", 18);

        String key = Constants.USER + lequal.getId();
        Map<String, String> stringStringMap = mapper.readValue(mapper.writeValueAsString(lequal), new TypeReference<Map<String, String>>() {
        });

        System.out.println(stringStringMap);

        jedis.hset(key, stringStringMap);

        System.out.println("获取对象：" + jedis.hgetAll(key));
    }
}
