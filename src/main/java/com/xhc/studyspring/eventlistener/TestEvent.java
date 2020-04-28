package com.xhc.studyspring.eventlistener;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 *
 * @Author: xhc
 * @Date: 2020/3/23 15:03
 * @Description:
 */
public class TestEvent extends ApplicationEvent {


    public TestEvent(Object source) {
        super(source);
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{" +
                "source=" + source +
                '}';
    }
}
