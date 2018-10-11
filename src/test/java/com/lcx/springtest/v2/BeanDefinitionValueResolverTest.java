package com.lcx.springtest.v2;
/**
 * @Created by:  IntelliJ Idea 2017.1
 * @Author: lcx
 * @Date: 2018/10/10
 * @Time: 下午5:32
 * @Title: BeanDefinitionValueResolverTest
 * @ProjectName: lcxspring
 * @Description: TODO
 */

import com.lcx.spring.beans.factory.config.RuntimeBeanReference;
import com.lcx.spring.beans.factory.config.TypedStringValue;
import com.lcx.spring.beans.factory.support.BeanDefinitionValueResolver;
import com.lcx.spring.beans.factory.support.DefaultBeanFactory;
import com.lcx.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.lcx.spring.core.io.ClassPathResource;
import com.lcx.springtest.bean.User;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * <p>bean定义值解析测试</p>
 * @author lichenxiang
 * @version BeanDefinitionValueResolverTest: BeanDefinitionValueResolverTest.java, v 0.1 2018年10月10日 下午5:32:32 lichenxiang Exp $
 *
 */
public class BeanDefinitionValueResolverTest {

    @Test
    public void testResolveRuntimeBeanReference() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("springtestv2.xml"));

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);

        RuntimeBeanReference reference = new RuntimeBeanReference("user");
        Object value = resolver.resolveValueIfNecessary(reference);

        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof User);
    }

    @Test
    public void testResolveTypedStringValue() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("springtestv2.xml"));

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);

        TypedStringValue stringValue = new TypedStringValue("15");
        Object value = resolver.resolveValueIfNecessary(stringValue);
        Assert.assertNotNull(value);
        Assert.assertEquals("15", value);

    }
}