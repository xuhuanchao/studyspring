package com.xhc.studyspring;

import com.xhc.studyspring.bean.Person;
import com.xhc.studyspring.configuration.AnnotionConfig;
import com.xhc.studyspring.configuration.JavaConfig;
import com.xhc.studyspring.bean.Gun;
import com.xhc.studyspring.scan.Bullet;
import com.xhc.studyspring.service.GunService;
import com.xhc.studyspring.service.SayHelloServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

/**
 * 测试spring 的applicationContext 使用3中方式配置
 *
 * 1. xml配置 {@link #useXmlConfigApp()}
 * 2. java方式配置 {@link #useJavaConfigApp()}
 * 3. 注解方式配置 {@link #useAnnotationConfigApp()}
 *
 * @Author: xhc
 * @Date: 2020/3/18 18:00
 * @Description:
 */
public class TestApplicationContextApp {

    /**
     * 输出ApplicationContext 的基本信息
     * @param applicationContext
     */
    public static void printApplicationContext(ApplicationContext applicationContext){
        String applicationName = applicationContext.getApplicationName();
        String appId = applicationContext.getId();
        ApplicationContext parent = applicationContext.getParent();
        String displayName = applicationContext.getDisplayName();

        System.out.println("\n========================\n打印 《applicationContext》 信息");
        System.out.println("applicationName="+applicationName);
        System.out.println("appId=" + appId);
        System.out.println("displayName=" + displayName);
        System.out.println("parent=" + (parent == null ? "null" : "有父容器"));



        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        int beanDefinitionCount = applicationContext.getBeanDefinitionCount();
        System.out.println("ioc容器中全部bean的数量：" + beanDefinitionCount);
        System.out.println("遍历每个bean名称：");
        for(int i=0, j=beanDefinitionNames.length; i<j; i++){
            System.out.println(i+1 +"." +beanDefinitionNames[i]);
        }
        System.out.println("打印 《applicationContext》 信息结束\n");
    }

    /**
     * 通过xml配置spring
     * @return
     */
    public static AbstractApplicationContext useXmlConfigApp(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[]{"applicationContext.xml"});
        printApplicationContext(ctx);


//        System.out.println("==测试scope=prototype 多实例");
//        Person person1 = ctx.getBean(Person.class);
//        Person person2 = ctx.getBean(Person.class);
//        person1.setName("test");
//        System.out.println("person1 hashcode=" + System.identityHashCode(person1) + ", person2 hashcode=" + System.identityHashCode(person2));
//        System.out.println("person1=" + person1 + "person2=" + person2);
        return ctx;
    }


    /**
     * 通过java @Configuration 的方式配置spring，使用@Bean 声明bean
     * @return
     */
    public static AbstractApplicationContext useJavaConfigApp(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(new Class[]{JavaConfig.class});
        printApplicationContext(ctx);

        Object laoli = ctx.getBean("laoli");
        System.out.println("获取名字为laoli的bean：" + laoli);

        // 使用GenericApplicationContext的 registerBean方法注册bean到spring 容器
        ctx.registerBean("xiaoli", Person.class, new Object[]{"小李", 18});
        System.out.println("新注册的bean=" + ctx.getBean("xiaoli"));


        SayHelloServiceImpl sayHelloService = (SayHelloServiceImpl)ctx.getBean("sayHelloServiceImpl");
        System.out.println(sayHelloService.sayHello2());

        return ctx;
    }


    /**
     * 通过注解配置spring  @Service、@Component、@Repository、@Controlle 、 @Autowired等
     *
     * 使用xml的方式，需要在xml配置	<context:component-scan base-package=""/>
     * 使用java方式，需要在使用@Configuration的类上，配置@ComponentScan(basePackages=””)
     *
     * @return
     */
    public static AbstractApplicationContext useAnnotationConfigApp(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(new Class[]{AnnotionConfig.class});
        printApplicationContext(ctx);
        GunService gunService = ctx.getBean(GunService.class);
        Gun gun = gunService.queryGunByName("沙鹰");
        System.out.println("查询结果："+gun);

        //查询子弹类，子弹类是通过该扫描时，通过@Component注册的
        Bullet bullet = (Bullet) ctx.getBean("bullet");
        gun.setBullet(bullet);

        System.out.println("装弹后的枪：" + gun);
        return ctx;
    }


    public static void main(String[] args) {
        AbstractApplicationContext ctx = null;

        if(args != null && args.length>0 && StringUtils.hasLength(args[0])){
            String type = args[0];
            switch (type) {
                case "xml":
                    ctx = useXmlConfigApp();
                    break;
                case "java":
                    ctx = useJavaConfigApp();
                    break;
                case "annotation":
                    ctx = useAnnotationConfigApp();
                    break;
                default:
                    System.out.println("参数不正确");
                    throw new IllegalArgumentException("["+ args[0] + "]，不在取值范围");
//                    break;
            }
        }

        ctx = useJavaConfigApp();  //useXmlConfigApp   useJavaConfigApp  useAnnotationConfigApp

        ctx.registerShutdownHook();

    }
}
