package com.lcx.springtest.v3;

import com.lcx.spring.beans.BeanDefinition;
import com.lcx.spring.beans.factory.support.DefaultBeanFactory;
import com.lcx.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.lcx.spring.context.support.ConstructorResolver;
import com.lcx.spring.core.io.ClassPathResource;
import com.lcx.spring.core.io.Resource;
import com.lcx.springtest.bean.TestBeanV3;
import org.junit.Assert;
import org.junit.Test;


public class ConstructorResolverTest {

	@Test
	public void testAutowireConstructor() {
		
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = new ClassPathResource("springtestv3.xml");
		reader.loadBeanDefinitions(resource);

		BeanDefinition bd = factory.getBeanDefinition("testBeanv3");
		
		ConstructorResolver resolver = new ConstructorResolver(factory);

		TestBeanV3 testBeanV3 = (TestBeanV3)resolver.autowireConstructor(bd);
		
		// 验证参数version 正确地通过此构造函数做了初始化
		// PetStoreService(AccountDao accountDao, ItemDao itemDao,int version)
		Assert.assertEquals(1, testBeanV3.getAge().intValue());
		
		Assert.assertNotNull(testBeanV3.getUser());

		
		
	}
}
