package com.xhc.studyspring.configuration;

import com.xhc.studyspring.bean.Person;
import com.xhc.studyspring.bean.Tank;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

/**
 * @Author: xhc
 * @Date: 2020/3/18 15:46
 * @Description:
 */
@Configuration
@ComponentScan(basePackages = "com.xhc.studyspring.*")
public class JavaConfig {

    @Bean(name = "laoli")
    public Person person(){
        return new Person("老李", 66);
    }

    @Bean
    public Tank tank(@Qualifier("laoli") Person person){
        return new Tank("普通", 100l, person);
    }


}
