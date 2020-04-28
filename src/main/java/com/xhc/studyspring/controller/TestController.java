package com.xhc.studyspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;

/**
 * spring mvc @Controller的使用
 *
 * @Author: xhc
 * @Date: 2020/3/23 15:56
 * @Description:
 */
@Controller
@RequestMapping("/jsp")
public class TestController {


    /**
     * 返回前打印信息，验证 RequestHandledEvent 事件是处理完请求后触发
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/jsp")
    public String helloJsp() throws Exception{
        System.out.println("do work ...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("controller finish");
        return "hello";
    }
}
