package com.lcx.spring.context.support;/**
 * Created by lichenxiang on 2018/8/23.
 */

import com.lcx.spring.beans.factory.support.BeanDefinitionRegistry;
import com.lcx.spring.beans.factory.support.DefaultBeanFactory;
import com.lcx.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.lcx.spring.context.ApplicationContext;
import com.lcx.spring.core.io.ClassPathResource;
import com.lcx.spring.core.io.Resource;

/**
 * <p>  </p>
 *
 * @author lichenxiang
 * @version : ClassPathXmlApplicationContext.java, v 0.1 2018年08月23日 下午4:45:45 lichenxiang Exp $
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {

    DefaultBeanFactory factory = null;

    public ClassPathXmlApplicationContext(String configFile) {

        factory = new DefaultBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        Resource resource = new ClassPathResource(configFile);

        reader.loadBeanDefinitions(resource);

    }

    public Object getBean(String beanId) {

        return factory.getBean(beanId);
    }
}