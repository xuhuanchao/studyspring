package com.xhc.studyspring.controller;

import com.xhc.studyspring.TestApplicationContextApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * spring mvc @RestController的使用
 *
 * @Author: xhc
 * @Date: 2020/3/23 13:39
 * @Description:
 */
@RestController
public class TestRestController {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 返回前打印信息，验证 RequestHandledEvent 事件是处理完请求后触发
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/hello", method = {RequestMethod.GET, RequestMethod.POST})
    public String hello() throws Exception {
        System.out.println("do work ...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("controller finish");
        TestApplicationContextApp.printApplicationContext(applicationContext);
        return "hello world";
    }


    @RequestMapping(value = "/some")
    public Map getSomething(){
        Map map = new HashMap();
        map.put("name", "xhc");
        return map;
    }
}
