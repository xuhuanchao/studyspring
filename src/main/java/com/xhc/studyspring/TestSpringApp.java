package com.xhc.studyspring;

import com.xhc.studyspring.bean.Book;
import com.xhc.studyspring.bean.CollectionTestBean;
import com.xhc.studyspring.bean.Person;
import com.xhc.studyspring.bean.Tank;
import com.xhc.studyspring.eventlistener.EventPublisher;
import com.xhc.studyspring.service.SayHelloService;
import com.xhc.studyspring.service.SayHiService;
import lombok.extern.log4j.Log4j2;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.io.*;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 *
 * 测试spring功能，使用resources/spring.xml 配置文件
 *
 * 1. inner bean  {@link #innerBeanTest()}
 * 2. 集合的注入  {@link #testInjectionCollection()}
 * 3. 自动装配  {@link #testAutoAssemble()}
 * 4. @required注解  {@link #testRequired()}
 * 5. 注入方式：构造注入、设值注入  {@link #testInjectionType()}
 * 6. 监听、自定义事件 {@link #testEventListener()}
 * 7. FileSystemResource、ClassPathResource的使用 {@link #testResource()}
 * 8. spring 自带aop的使用  {@link #testAopBySpringProxyFactoryBean()}
 * 9. 使用Aspectjwearver 实现动态代理 {@link #testAopByOrgApectAspectjwearver}
 * @Author: xhc
 * @Date: 2020/3/20 13:26
 * @Description:
 */
@Log4j2
public class TestSpringApp {


    /**
     * inner bean 内部类只能在组装外部类时使用，会被容器忽略
     */
    public static void innerBeanTest(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[]{"sping.xml"});
        Tank tank1 = (Tank)ctx.getBean("tank1");
        System.out.println("tank=" + tank1);

        boolean result = ctx.containsBean("person1");
        System.out.println("容器中是否包含 id=person1 的bean : " + result);
        try {
            Person person2 = (Person)ctx.getBean(Person.class);
            System.out.println("person2=" + person2);
        } catch (BeansException e) {
//            e.printStackTrace();
            System.out.println("容器中不包含Person.class 的bean");
        }

        TestApplicationContextApp.printApplicationContext(ctx);
    }

    /**
     * 测试集合的注入
     */
    public static void testInjectionCollection(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[]{"sping.xml"});
        CollectionTestBean xmlCollection = (CollectionTestBean)ctx.getBean("collectionTestBean");
        System.out.println("xml injection collection =" + xmlCollection);


        CollectionTestBean javaCollection = (CollectionTestBean)ctx.getBean("javaCollection");
        System.out.println("java injection collection=" + javaCollection);

        CollectionTestBean annotationCollection = (CollectionTestBean)ctx.getBean("annotationCollection");
        System.out.println("annotation injection collection=" + annotationCollection);


        TestApplicationContextApp.printApplicationContext(ctx);
    }


    /**
     * 测试自动装配
     * 通过xml配置 构造方法自动装配
     * 通过java方法使用@Autowired装配
     */
    public static void testAutoAssemble(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[]{"sping.xml"});

        // 测试通过constructor-arg 自动装配bean
        Tank tank3 = (Tank)ctx.getBean("tank3");
        System.out.println("" +tank3);

        //@Autowired 默认使用类型匹配，如果同类型的有多个bean，可以用@Qualifier(value = "beanId")指定beanId
        CollectionTestBean annotationCollection = (CollectionTestBean)ctx.getBean("annotationCollection");
        System.out.println("annotation injection collection=" + annotationCollection);
    }


    /**
     * 测试@Required注解
     * 测试 属性添加@Required 注解，并配置了RequiredAnnotationBeanPostProcessor后，容器检查属性必须有值
     *
     */
    public static void testRequired(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[]{"sping.xml"});

        /*
            测试 @Required 注解
            如果配置了<bean class="org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor" />
            bean装配完成后检查 @Required 注解的set方法对应的属性是否有值，没有则抛异常BeanInitializationException
         */
        Book book = (Book)ctx.getBean("book");
        System.out.println(book);
    }


    /**
     * 测试注入方式，设值注入和构造注入
     * 同时使用构造和设值注入的优先级， 构造注入先执行，设值注入后执行
     *
     */
    public static void testInjectionType(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[]{"sping.xml"});

        //测试 同时使用构造和设值注入的优先级， 构造注入先执行，设值注入后执行
        Tank testTank = (Tank)ctx.getBean("testTank");
        System.out.println(testTank);
    }


    /**
     * 测试 spring的 事件监听 ， 自定义事件
     * 通过{@link EventPublisher} 发布自定义事件
     * 通过{@link com.xhc.studyspring.eventlistener.TestEventListener} 、
     * {@link com.xhc.studyspring.eventlistener.TestRefreshedEventListener}
     * {@link com.xhc.studyspring.eventlistener.TestTestEventListener} 监听事件
     *
     */
    public static void testEventListener(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[]{"sping.xml"});

        TestApplicationContextApp.printApplicationContext(ctx);

        //获取事件发布器
        EventPublisher eventPublisher = (EventPublisher)ctx.getBean("eventPublisher");
        eventPublisher.publishEvent();

        try {
//            ctx.registerBean("tank4", Tank.class, new Object[]{"测试坦克", 200l});
            ctx.stop();
            TimeUnit.SECONDS.sleep(3);
            Tank tank4 = (Tank)ctx.getBean("tank3");
            System.out.println("ctx stop后获取bean："+ tank4);

            ctx.refresh();
            TimeUnit.SECONDS.sleep(3);
            tank4 = (Tank)ctx.getBean("tank3");
            System.out.println("ctx refresh后获取bean："+ tank4);

            ctx.start();
            TimeUnit.SECONDS.sleep(3);
            Tank tank3 = (Tank)ctx.getBean("tank3");
            System.out.println("ctx start后获取bean："+ tank3);

            ctx.close();
            tank3 = (Tank)ctx.getBean("tank3");
            System.out.println("ctx close后获取bean："+ tank3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
     * 测试  {@link org.springframework.core.io.FileSystemResource}、 {@link org.springframework.core.io.ClassPathResource}
     * 加载资源，然后读文件内容
     *
     * FileSystemResource 通过File、或Path加载资源
     * ClassPathResource 通过ClassLoader加载资源，classpath为根目录
     *
     */
    public static void testResource(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[]{"sping.xml"});
        FileSystemResource fileSystemResource = new FileSystemResource("J:\\github\\studyspring\\src\\main\\resources\\resource.txt");
        ClassPathResource classPathResource = new ClassPathResource("resource.txt");

        BufferedReader br = null;
        try {
            System.out.println("\n=====使用FileSystemResource获取inputStream 读取文件内容：");
            //通过FileSystemResource 读文件
            InputStream inputStream = fileSystemResource.getInputStream();
            InputStreamReader isr = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            br = new BufferedReader(isr);
            String s = null;
            while((s = br.readLine()) != null){
                System.out.println(s);
            }

            br.close();

            System.out.println("\n=====使用ClassPathResource获取inputStream 读取文件内容：");
            //ClassPathResource 读文件
            inputStream = classPathResource.getInputStream();
            isr = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            br = new BufferedReader(isr);
            while((s = br.readLine()) != null){
                System.out.println(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * spring中使用aop有2种方式：
     * 基于jdk创建动态代理，对实现了接口的类做代理，
     * 基于cglib创建动态代理，对类进行代理（被代理类不必须实现接口），final方法不能代理的将会失败
     *
     * 1.使用spring自带的 MethodBeforeAdvice, AfterReturningAdvice
     *   在xml中配置代理工厂生成接口{@link SayHelloService}的代理对象sayHelloProxy
     *   在xml中配置{@link ProxyFactoryBean}时，添加<property name="proxyTargetClass" value="true"></property> 可以使用cglib
     *
     *   注意：
     *   当xml中 引入了Aspectjwearver 包中的<aop:...> 标签后，xml中配置的PointcutAdvisor 将被org.aspectj 识别，即使没有通过{@link ProxyFactoryBean} 生成代理类，也会自动产生代理。
     *   此时如果调用{@link ProxyFactoryBean} 创建的代理类的切面方法，那么将会有3个代理被调用，个人认为有1次是ProxyFactoryBean创建的代理，有2次是org.aspectj
     *   最好不要spring的aop和Aspectjwearver一起用
     *
     *
     */
    public static void testAopBySpringProxyFactoryBean(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[]{"sping.xml"});

        // 通过xml配置，使用spring自带通知，通过ProxyFactoryBean创建代理类。获取SayHelloService 接口的代理类
        SayHelloService sayHelloService = (SayHelloService)ctx.getBean("sayHelloServiceProxy");
        log.info("获得代理类：" +sayHelloService.getClass());
        String s = sayHelloService.sayHello();
        log.info("调用代理类方法完毕，方法返回：" + s);

        TestApplicationContextApp.printApplicationContext(ctx);
    }


    /**
     * 使用Aspectjwearver实现代理。 通过配置 <aop:config></aop:config> 配置切面。或者使用@Aspect配置，需要开启<aop:aspectj-autoproxy/>
     * 通过<aop:aspectj-autoproxy proxy-target-class="true" /> 指定使用cglib 代理
     *
     */
    public static void testAopByOrgApectAspectjwearver(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[]{"AopForAspectj.xml"});

        // 通过xml配置，使用spring自带通知，通过ProxyFactoryBean创建代理类。获取代理类
        SayHiService sayHiService = (SayHiService)ctx.getBean(SayHiService.class);
        log.info("获得代理类：" +sayHiService.getClass());
        sayHiService.hi();
        log.info("调用代理类方法完毕");

        TestApplicationContextApp.printApplicationContext(ctx);
    }

    public static void main(String[] args) {
//        innerBeanTest();
//        testInjectionCollection();
//        testAutoAssemble();
//        testInjectionType();
//        testEventListener();
//        testResource();
//        testAopBySpringProxyFactoryBean();
        testAopByOrgApectAspectjwearver();
    }


}
