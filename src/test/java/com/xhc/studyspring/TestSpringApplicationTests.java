package com.xhc.studyspring;


import com.xhc.studyspring.bean.Tank;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSpringApplicationTests {

	@Resource
	private ApplicationContext applicationContext;

	@Before
	public void init(){
		System.out.println("准备开始执行test单元测试！");
	}


	@Test
	public void testContextLoads() {
		System.out.println("测试容器初始化：");
		Assert.assertNotNull(applicationContext);


	}

	@Test
	@Order(1)
	public void testPrimary(){
		System.out.println("测试多个同类class的bean，使用primary=true，通过getBean(class)可以获取到primary的bean");
		//相同类型bean 可根据primary=true ，设置为优先选择
		Tank tank = applicationContext.getBean(Tank.class);
		System.out.println("tank=" + tank);
		Assert.assertEquals("与预期不符合", "虎式", tank.getName());
	}




}
