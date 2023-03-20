package com.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.bean.User;
import com.redis.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @ClassName: UserServiceTester
 * @Author: lequal
 * @Date: 2022/10/31
 * @Description:
 */
@SpringBootTest
public class UserServiceTester {

    @Resource(name = "userService")
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private final static ObjectMapper mapper = new ObjectMapper();

    @Test
    void testAddUser() {
        User user = new User(3, "heihei", 11, "广东省珠海市");
        userService.addUser(user);

        User userRes = userService.getUser("heihei");
        System.out.println(userRes);
    }


    @Test
    void testStringTemplate() throws JsonProcessingException {

        User user = new User(5, "laobiao", 26, "广东省佛山市");

        stringRedisTemplate.opsForValue().set("user:laobiao", mapper.writeValueAsString(user));

        String userRes = stringRedisTemplate.opsForValue().get("user:laobiao");
        User user1 = mapper.readValue(userRes, User.class);
        System.out.println(user1);
    }

    @Test
    void testHash() {

    }

}
