package com.xhc.studyspring;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.springframework.core.io.ClassPathResource;

import java.io.File;


/**
 * 测试spring 的 {@link org.springframework.web.context.support.RequestHandledEvent} 事件
 * 测试 spring mvc 使用 内嵌tomcat 方式启动。
 *
 * TestEventListener 类可以观察到 ServletRequestHandledEvent 事件
 *
 * @Author: xhc
 * @Date: 2020/3/23 13:15
 * @Description:
 */
public class TestSpringMvcApp {


    /**
     * 测试 spring mvc 使用 内嵌tomcat 方式启动。
     * TestEventListener 类可以观察到 ServletRequestHandledEvent 事件
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String webappDirLocation = "";
        ClassPathResource classPathResource = new ClassPathResource("webapp");
        File file = classPathResource.getFile();
        System.out.println("file=" + file.getAbsolutePath());

        Tomcat tomcat = new Tomcat();
        String webPort = "8081";
        tomcat.setPort(Integer.valueOf(webPort));


        StandardContext ctx = (StandardContext) tomcat.addWebapp( "/", file.getAbsolutePath());

        // Declare an alternative location for your "WEB-INF/classes" dir
        // Servlet 3.0 annotation will work
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();



    }
}
