package com.lcx.springtest;/**
 * Created by lichenxiang on 2018/6/27.
 */

import com.lcx.spring.beans.BeanDefinition;
import com.lcx.spring.beans.factory.BeanFactory;
import com.lcx.spring.beans.factory.support.DefaultBeanFactory;
import com.lcx.springtest.bean.TestBean;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * <p>  </p>
 *
 * @author lichenxiang
 * @version SpringTestV1: SpringTestV1.java, v 0.1 2018年06月27日 下午7:22:22 lichenxiang Exp $
 */
public class SpringTestV1 {

    @Test
    public void beanFactoryTestV1(){

        //获取一个 beanFactory
        BeanFactory beanFactory = new DefaultBeanFactory("springtestv1.xml");

        //通过 beanFactory 获取 beanDefation
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("testBean");

        //判断 bean是否存在 且正确
        assertEquals("com.lcx.springtest.bean.TestBean",beanDefinition.getBeanClassName());

        //获取 bean
        TestBean testBean = (TestBean)beanFactory.getBean("testBean");

        //判断 bean是否为空
        assertNotNull(testBean);

    }

    @Test
    public void testInvalidBean(){

        BeanFactory beanFactory = new DefaultBeanFactory("springtestv1.xml");

        try {
            beanFactory.getBean("noExitBean");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Assert.fail("expect BeanCreationException");
    }


    @Test
    public void testInvalidLoadXml(){

        try {
            BeanFactory beanFactory = new DefaultBeanFactory("noExit.xml");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Assert.fail("expect BeanCreationException");


    }
}