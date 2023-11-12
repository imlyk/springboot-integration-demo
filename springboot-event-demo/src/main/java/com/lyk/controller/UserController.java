package com.lyk.controller;

import com.lyk.eventpublisher.EventPublisher;
import com.lyk.eventpublisher.events.UserLoginSuccessEvent;
import com.lyk.po.UserPO;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyk
 * @version 1.0
 * @date 2023/11/11 16:04
 * @description
 */
@RequestMapping("/user")
@RestController
public class UserController {


    /**
     * 注入事件发布器
     */
    @Resource
    private EventPublisher eventPublisher;

    /**
     * @author lyk
     * @description 业务场景：
     * 假设用户登录成功之后：
     * 1、自动加积分
     * 2、系统自动记录用户的登录日志
     * 3、发送邮件通知
     * @date 2023/11/11 16:08
     * @param userName
     * @param password
     * @return org.springframework.http.ResponseEntity<?>
     **/
    @GetMapping("/login/{userName}/{password}") // 只是演示，就不按照 restful 风格搞了
    public ResponseEntity<?> login(@PathVariable("userName") String userName, @PathVariable("password") String password) {


        // 1、生成事件
        UserLoginSuccessEvent userLoginSuccessEvent = new UserLoginSuccessEvent(new UserPO(1L, userName, password));
        // 2、发布事件
        eventPublisher.sendEvent(userLoginSuccessEvent);


        return new ResponseEntity<>(userName + " -> 登录成功", HttpStatus.OK);
    }
}
