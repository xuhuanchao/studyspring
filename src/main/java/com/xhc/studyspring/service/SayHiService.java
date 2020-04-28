package com.xhc.studyspring.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @Author: xhc
 * @Date: 2020/4/28 16:38
 * @Description:
 */
@Log4j2
@Service
public class SayHiService {

    public void hi(){
       log.info("hi");
    }
}
