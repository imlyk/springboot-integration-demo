package com.lyk.eventpublisher;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @author lyk
 * @version 1.0
 * @date 2023/11/11 16:11
 * @description
 */
@Service
public class EventPublisher implements ApplicationEventPublisherAware {

    /**
     * 专门用于发送通知，以便 Springboot 底层可以感知到事件
     */
    ApplicationEventPublisher applicationEventPublisher;


    /**
     * @author lyk
     * @description 发送用户的事件（无论什么事件都可以发。只要是 ApplicationEvent 即可）
     * @date 2023/11/11 16:14
     * @param event
     * @return void
     **/
    public void sendEvent(ApplicationEvent event) {
        applicationEventPublisher.publishEvent(event);
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
