package com.lcx.springtest.v2;
/**
 * @Created by:  IntelliJ Idea 2017.1
 * @Author: lcx
 * @Date: 2018/10/10
 * @Time: 下午5:08
 * @Title: BeanDefinitionTest
 * @ProjectName: lcxspring
 * @Description: TODO
 */

import com.lcx.spring.beans.BeanDefinition;
import com.lcx.spring.beans.PropertyValue;
import com.lcx.spring.beans.factory.config.RuntimeBeanReference;
import com.lcx.spring.beans.factory.config.TypedStringValue;
import com.lcx.spring.beans.factory.support.DefaultBeanFactory;
import com.lcx.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.lcx.spring.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 *
 * <p>  </p>
 * @author lichenxiang
 * @version BeanDefinitionTest: BeanDefinitionTest.java, v 0.1 2018年10月10日 下午5:08:08 lichenxiang Exp $
 *
 */
public class BeanDefinitionTest {

    @Test
    public void testGetBeanDefinition() {

        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinitions(new ClassPathResource("springtestv2.xml"));

        BeanDefinition bd = factory.getBeanDefinition("testBeanv2");

        List<PropertyValue> pvs = bd.getPropertyValues();

        Assert.assertTrue(pvs.size() == 2);
        {
            PropertyValue pv = this.getPropertyValue("user", pvs);

            Assert.assertNotNull(pv);

            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }

        {
            PropertyValue pv = this.getPropertyValue("age", pvs);

            Assert.assertNotNull(pv);

            Assert.assertTrue(pv.getValue() instanceof TypedStringValue);
        }

    }

    private PropertyValue getPropertyValue(String name,List<PropertyValue> pvs){
        for(PropertyValue pv : pvs){
            if(pv.getName().equals(name)){
                return pv;
            }
        }
        return null;
    }


}