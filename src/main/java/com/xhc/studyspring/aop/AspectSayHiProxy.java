package com.xhc.studyspring.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 使用aspectj 实现代理
 *
 * @Author: xhc
 * @Date: 2020/3/25 15:58
 * @Description:
 */
@Log4j2
public class AspectSayHiProxy {

    public void before(){
        log.info("通过xml配置Aspect，before。代理方法执行前进行通知...");
    }

    public void after(){
        log.info("通过xml配置Aspect，after。 代理方法执行后进行通知...");
    }


    public void around(ProceedingJoinPoint joinPoint){
        log.info("通过xml配置Aspect，around。 代理方法执行前进行通知...");
        try {
            Object proceed = joinPoint.proceed();
            log.info("通过xml配置Aspect，around。代理方法执行结果：" + proceed);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        log.info("通过xml配置Aspect，around。 代理方法执行后进行通知...");
    }
}
