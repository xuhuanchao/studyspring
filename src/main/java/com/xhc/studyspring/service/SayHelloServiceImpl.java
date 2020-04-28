package com.xhc.studyspring.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @Author: xhc
 * @Date: 2020/3/25 15:16
 * @Description:
 */
@Service
@Log4j2
public class SayHelloServiceImpl implements SayHelloService{

    @Override
    public String sayHello(){
        log.info("代理类方法执行");
        return "hello world";
    }

    @Override
    public String sayHello2() {
        log.info("代理类方法执行");
        return "hello world2";
    }
}
