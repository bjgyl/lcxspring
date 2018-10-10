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
public class ClassPathXmlApplicationContext extends AbstractApplicationContext implements ApplicationContext {


    public ClassPathXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    protected Resource getResourceByPath(String path) {

        return new ClassPathResource(path,this.getBeanClassLoader());
    }
}