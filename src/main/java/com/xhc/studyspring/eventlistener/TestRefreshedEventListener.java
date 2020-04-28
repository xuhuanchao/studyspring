package com.xhc.studyspring.eventlistener;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 事件监听器，指定监听{@link ContextRefreshedEvent}事件
 *
 * @Author: xhc
 * @Date: 2020/3/23 15:02
 * @Description:
 */
@Component
@Log4j2
public class TestRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("TestRefreshedEventListener 获取事件：" +event);
    }
}
