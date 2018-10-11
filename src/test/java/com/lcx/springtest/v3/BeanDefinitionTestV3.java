package com.lcx.springtest.v3;

import com.lcx.spring.beans.BeanDefinition;
import com.lcx.spring.beans.ConstructorArgument;
import com.lcx.spring.beans.factory.config.RuntimeBeanReference;
import com.lcx.spring.beans.factory.config.TypedStringValue;
import com.lcx.spring.beans.factory.support.DefaultBeanFactory;
import com.lcx.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.lcx.spring.core.io.ClassPathResource;
import com.lcx.spring.core.io.Resource;
import org.junit.Assert;
import org.junit.Test;


import java.util.List;

public class BeanDefinitionTestV3 {

	@Test
	public void testConstructorArgument() {
		
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = new ClassPathResource("springtestv3.xml");
		reader.loadBeanDefinitions(resource);

		BeanDefinition bd = factory.getBeanDefinition("testBeanv3");
		Assert.assertEquals("com.lcx.springtest.bean.TestBeanV3", bd.getBeanClassName());
		
		ConstructorArgument args = bd.getConstructorArgument();
		List<ConstructorArgument.ValueHolder> valueHolders = args.getArgumentValues();
		
		Assert.assertEquals(2, valueHolders.size());
		
		RuntimeBeanReference ref1 = (RuntimeBeanReference)valueHolders.get(0).getValue();
		Assert.assertEquals("user", ref1.getBeanName());

		
		TypedStringValue strValue = (TypedStringValue)valueHolders.get(1).getValue();
		Assert.assertEquals( "1", strValue.getValue());
	}

}
