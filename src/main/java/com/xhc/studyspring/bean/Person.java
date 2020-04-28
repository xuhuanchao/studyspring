package com.xhc.studyspring.bean;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Author: xhc
 * @Date: 2020/3/18 18:06
 * @Description:
 */
@Data
@Log4j2
public class Person implements DisposableBean, InitializingBean {

    private String name;
    private Integer age;

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("通过InitializingBean接口创建了Person对象：" + this);
    }

    @Override
    public void destroy() throws Exception {
        log.info("通过DisposableBean接口销毁了Person对象：" + this);
    }

    public void init(){
        log.info("通过自定义init方法创建了Tank对象：" + this );
    }

    public void cleanup(){
        log.info("通过自定义cleanup方法销毁了Tank对象：" + this );
    }
}
