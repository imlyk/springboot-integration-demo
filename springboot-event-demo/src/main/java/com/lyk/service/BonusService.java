package com.lyk.service;

import com.lyk.eventpublisher.events.UserLoginSuccessEvent;
import com.lyk.po.UserPO;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author lyk
 * @version 1.0
 * @date 2023/11/11 16:16
 * @description 事件监听：方式 1
 */
@Service
public class BonusService {


    /**
     * @author lyk
     * @description
     * @date 2023/11/11 16:17
     * @return void
     **/
    @EventListener
    public void addReward(UserLoginSuccessEvent event) {

        System.out.println("BonusService == 收到事件" + event);

        UserPO userPO = (UserPO) event.getSource();

        System.out.println(userPO.getUserName() + "登录成功,积分 +1");
    }
}
