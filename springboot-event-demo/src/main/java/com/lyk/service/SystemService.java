package com.lyk.service;

import com.lyk.eventpublisher.events.UserLoginSuccessEvent;
import com.lyk.po.UserPO;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author lyk
 * @version 1.0
 * @date 2023/11/11 17:10
 * @description 事件监听：方式 1
 */
@Service
public class SystemService implements ApplicationListener<UserLoginSuccessEvent> {

    public void loginLog(UserPO userPO) {
        // 用户登录成功记录日志
        System.out.println("记录用户登录日志：" + userPO.getUserName());
    }

    @Override
    public void onApplicationEvent(UserLoginSuccessEvent event) {
        System.out.println("SystemService == 收到事件" + event);


        UserPO userPO = (UserPO) event.getSource();
        loginLog(userPO);
    }
}
