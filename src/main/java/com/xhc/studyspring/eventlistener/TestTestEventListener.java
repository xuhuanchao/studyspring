package com.xhc.studyspring.eventlistener;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听器，指定监听{@link TestEvent}事件
 *
 * @Author: xhc
 * @Date: 2020/3/23 15:02
 * @Description:
 */
@Component
@Log4j2
public class TestTestEventListener implements ApplicationListener<TestEvent> {

    @Override
    public void onApplicationEvent(TestEvent event) {
        log.info("TestEventListener2 获取事件：" +event);
    }
}
