package com.xhc.studyspring.eventlistener;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听器
 *
 * @Author: xhc
 * @Date: 2020/3/23 11:50
 * @Description:
 */
@Component
@Log4j2
public class TestEventListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("TestEventListener 获取事件：" + event );
    }
}
