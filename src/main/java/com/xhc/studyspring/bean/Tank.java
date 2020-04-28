package com.xhc.studyspring.bean;

import lombok.Data;

/**
 * @Author: xhc
 * @Date: 2020/3/18 15:47
 * @Description:
 */
@Data
public class Tank {

    private String name;

    private Long health;

    private Person driver;

    public Tank() {
    }

    public Tank(String name, Long health) {
        this.name = name;
        this.health = health;
    }

    public Tank(String name, Long health, Person driver) {
        this.name = name;
        this.health = health;
        this.driver = driver;
    }

}
