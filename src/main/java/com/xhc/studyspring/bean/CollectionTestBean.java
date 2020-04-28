package com.xhc.studyspring.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @Author: xhc
 * @Date: 2020/3/20 13:38
 * @Description:
 */
@Data
@Component(value = "annotationCollection")
public class CollectionTestBean {

    @Autowired
    @Qualifier(value = "simpleList2")
    private List list;

    private List<Tank> list2;

    private Set set;

    private Map map;

    private Properties properties;
}
