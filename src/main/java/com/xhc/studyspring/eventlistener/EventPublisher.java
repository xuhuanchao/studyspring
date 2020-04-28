package com.xhc.studyspring.eventlistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 自定义事件发布器
 * 使用{@link ApplicationEventPublisher} 发布事件
 *
 * @Author: xhc
 * @Date: 2020/3/23 15:06
 * @Description:
 */
@Component
public class EventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationContext applicationContext;


    public void publishEvent(){
        TestEvent event = new TestEvent(applicationContext);
        applicationEventPublisher.publishEvent(event);
    }


}
