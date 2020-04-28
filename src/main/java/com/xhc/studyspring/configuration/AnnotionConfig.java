package com.xhc.studyspring.configuration;

import com.xhc.studyspring.bean.Gun;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: xhc
 * @Date: 2020/3/18 15:46
 * @Description:
 */
@Configuration
@ComponentScan(basePackages = {"com.xhc.studyspring.scan", "com.xhc.studyspring.service"})
public class AnnotionConfig {


    @Bean(name = "revolver")
    public Gun revolver(){
        return new Gun("左轮", 10l);
    }

    @Bean(name = "shotgun")
    public Gun shotgun(){
        return new Gun("散弹", 50l);
    }

}
