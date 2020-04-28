package com.xhc.studyspring.bean;

import com.xhc.studyspring.scan.Bullet;
import lombok.Data;

/**
 * @Author: xhc
 * @Date: 2020/3/19 18:34
 * @Description:
 */
@Data
public class Gun {

    private String name;

    private Long health;

    private Bullet bullet;

    public Gun() {
    }

    public Gun(String name, Long health) {
        this.name = name;
        this.health = health;
    }

    public Gun(String name, Long health, Bullet bullet) {
        this.name = name;
        this.health = health;
        this.bullet = bullet;
    }


    @Override
    public String toString() {
        return "Gun{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", bullet=" + bullet +
                '}';
    }

}
