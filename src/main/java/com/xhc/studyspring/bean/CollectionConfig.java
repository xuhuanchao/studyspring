package com.xhc.studyspring.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: xhc
 * @Date: 2020/3/20 14:03
 * @Description:
 */
@Configuration
public class CollectionConfig {

    @Bean(name = "javaCollection")
    public CollectionTestBean collectionTestBean(List simpleList2){
        CollectionTestBean collectionTestBean = new CollectionTestBean();

        List<String> list = new ArrayList<>();
        list.add("白");
        list.add("绿");
        list.add("粉");
        collectionTestBean.setList(simpleList2);  // 这个list 没有用到，最后用的还是 CollectionTestBean @Autowired 指定的bean

        Set<String> set = new HashSet<>();
        set.add("北京");
        set.add("上海");
        collectionTestBean.setSet(set);



        return collectionTestBean;
    }


    @Bean(name = "simpleList")
    public List simpleList(){
        List<String> list = new ArrayList<>();
        list.add("黄");
        list.add("蓝");
        list.add("紫");
        return list;
    }

    @Bean(name = "simpleList2")
    public List simpleList2(){
        List<String> list = new ArrayList<>();
        list.add("黄2");
        list.add("蓝2");
        list.add("紫2");
        return list;
    }

}
