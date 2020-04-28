package com.xhc.studyspring.bean;

import org.springframework.beans.factory.annotation.Required;

/**
 * @Author: xhc
 * @Date: 2020/3/20 16:37
 * @Description:
 */
public class Book {

    private String name ;

    private Person person;

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", person=" + person +
                '}';
    }

    public String getName() {
        return name;
    }

    @Required
    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    @Required
    public void setPerson(Person person) {
        this.person = person;
    }
}
