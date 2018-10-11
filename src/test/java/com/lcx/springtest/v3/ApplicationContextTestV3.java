package com.lcx.springtest.v3;

import com.lcx.spring.context.ApplicationContext;
import com.lcx.spring.context.support.ClassPathXmlApplicationContext;
import com.lcx.springtest.bean.TestBeanV3;
import org.junit.Assert;
import org.junit.Test;


public class ApplicationContextTestV3 {

	@Test
	public void testGetBeanProperty() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("springtestv3.xml");
		TestBeanV3 testBeanV3 = (TestBeanV3)ctx.getBean("testBeanv3");
		
		Assert.assertNotNull(testBeanV3.getUser());
		Assert.assertEquals(1, testBeanV3.getAge().intValue());
		
	}

}
