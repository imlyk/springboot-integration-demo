package com.lyk.eventpublisher.events;

import com.lyk.po.UserPO;
import org.springframework.context.ApplicationEvent;

/**
 * @author lyk
 * @version 1.0
 * @date 2023/11/11 16:46
 * @description 用户登录成功事件类型
 */
public class UserLoginSuccessEvent extends ApplicationEvent {
    public UserLoginSuccessEvent(UserPO source) {
        super(source);
    }
}
