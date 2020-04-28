package com.xhc.studyspring.service;

import com.xhc.studyspring.bean.Gun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhc
 * @Date: 2020/3/19 18:28
 * @Description:
 */
@Service
public class GunService {

    @Autowired
    private ApplicationContext ctx;

    @Value("revolver")
    private String defaultGunName;


    /**
     * 查ioc容器中类型为Gun的bean 是否有名字是name的，如果有返回bean
     * @param name
     * @return
     */
    public Gun queryGunByName(String name){
        String[] gunNames = ctx.getBeanNamesForType(Gun.class);
        List<Gun> gunList = new ArrayList<>();

        for(int i=0, j=gunNames.length; i<j; i++){
            Gun gun = (Gun)ctx.getBean(gunNames[i]);
            if(gun.getName().equals(name)){
                return gun;
            }
        }

        System.out.println("没有找到["+name+"]枪，返回默认的");
        return (Gun)ctx.getBean(defaultGunName);
    }
}
