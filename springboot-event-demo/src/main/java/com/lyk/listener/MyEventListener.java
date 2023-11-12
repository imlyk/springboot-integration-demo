package com.lyk.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author lyk
 * @version 1.0
 * @date 2023/11/11 15:46
 * @description
 */
public class MyEventListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("===事件到达====" + event);
    }
}
