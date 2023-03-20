package com.redis.service;

import cn.hutool.json.JSONUtil;
import com.redis.bean.User;
import com.redis.common.Global;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: UserService
 * @Author: lequal
 * @Date: 2022/10/31
 * @Description: TODO
 */
@Slf4j
@Service
public class UserService {

    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    public void addUser1(User user) {
        redisTemplate.opsForValue().set(Global.Key.USER + user.getName(), JSONUtil.toJsonStr(user));
        log.info("保存用户成功");
    }

    public void addUser(User user) {
        redisTemplate.opsForValue().set(Global.Key.USER + user.getName(), user);
        log.info("保存用户成功");
    }

    public User getUser(String key) {
        return (User)redisTemplate.opsForValue().get(Global.Key.USER + key);
    }
}
