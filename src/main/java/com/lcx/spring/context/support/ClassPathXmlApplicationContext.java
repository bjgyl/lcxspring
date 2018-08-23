package com.lcx.spring.context.support;/**
 * Created by lichenxiang on 2018/8/23.
 */

import com.lcx.spring.beans.factory.support.BeanDefinitionRegistry;
import com.lcx.spring.beans.factory.support.DefaultBeanFactory;
import com.lcx.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.lcx.spring.context.ApplicationContext;

/**
 * <p>  </p>
 *
 * @author lichenxiang
 * @version : ClassPathXmlApplicationContext.java, v 0.1 2018年08月23日 下午4:45:45 lichenxiang Exp $
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {

    DefaultBeanFactory factory = null;

    public ClassPathXmlApplicationContext(String config) {

        factory = new DefaultBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinitions(config);

    }

    public Object getBean(String beanId) {

        return factory.getBean(beanId);
    }
}