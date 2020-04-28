package com.xhc.studyspring.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 *
 * 通过@Aspect 注解实现AOP通知
 * 需要引入aspectj 包
 *
 * @Author: xhc
 * @Date: 2020/3/25 16:57
 * @Description:
 */
@Aspect
@Component
@Log4j2
public class AspectSyHiProxyAnnotation {

    /**
     * 定义切点， execution()规则
     * *: 返回值类型
     * com.xxx.xx: 包名
     * ..: 当前以及子包
     * *: 指定所有类
     * .hi(..): 指定方法名称为hi ，任意参数
     */
    @Pointcut("execution (* com.xhc.studyspring.service.*.hi(..))")
    public void point() {
    }


    @Before("point()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("通过@Aspect 实现aop通知。调用sayHello前...");
    }

    @After("point()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("通过@Aspect 实现aop通知。调用sayHello后...");
    }

    @Around("point()")
    public void doAround(JoinPoint joinPoint){
        log.info("通过@Aspect 实现aop通知。环绕sayHello，调用前...");
        try {
            Object proceed = ((ProceedingJoinPoint) joinPoint).proceed();
            log.info("通过@Aspect 实现aop通知。运行代理返回结果：" + proceed);
            log.info("通过@Aspect 实现aop通知。环绕sayHello，调用后...");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


    @AfterThrowing(pointcut = "point()", throwing="e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.info("通过@Aspect 实现aop通知。 异常时通知，异常信息" + e.getMessage());
    }
}

