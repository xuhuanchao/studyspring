package com.xhc.studyspring.scan;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: xhc
 * @Date: 2020/3/19 18:38
 * @Description:
 */
@Data
@Component
public class Bullet {

    @Value("普通子弹")
    private String name;

    @Value("黑火药")
    private String type;

    @Override
    public String toString() {
        return "Bullet{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
