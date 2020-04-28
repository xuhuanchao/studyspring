package com.xhc.studyspring.aop;

import lombok.extern.log4j.Log4j2;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 使用spring自带的aop通知
 * @Author: xhc
 * @Date: 2020/3/25 15:12
 * @Description:
 */
@Log4j2
public class SayHelloAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        log.info("在切点方法执行前，进行通知");
    }


    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        log.info("在切点方法执行后，进行通知");
    }
}
