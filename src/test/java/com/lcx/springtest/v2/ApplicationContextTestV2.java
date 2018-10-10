package com.lcx.springtest.v2;
/**
 * @Created by:  IntelliJ Idea 2017.1
 * @Author: lcx
 * @Date: 2018/10/10
 * @Time: 下午5:25
 * @Title: ApplicationContextTestV2
 * @ProjectName: lcxspring
 * @Description: TODO
 */

import com.lcx.spring.context.ApplicationContext;
import com.lcx.spring.context.support.ClassPathXmlApplicationContext;
import com.lcx.springtest.bean.TestBeanV2;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 *
 * <p>  </p>
 * @author lichenxiang
 * @version ApplicationContextTestV2: ApplicationContextTestV2.java, v 0.1 2018年10月10日 下午5:25:25 lichenxiang Exp $
 *
 */
public class ApplicationContextTestV2 {

    @Test
    public void testGetBeanProperty() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("springtestv2.xml");
        TestBeanV2 testBeanV2 = (TestBeanV2)ctx.getBean("testBeanv2");
        //当前运行不过去 因为并没有注入
        assertNotNull(testBeanV2.getUser());
        assertNotNull(testBeanV2.getAge());

    }

}