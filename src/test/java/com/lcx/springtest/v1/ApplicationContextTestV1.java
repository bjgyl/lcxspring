package com.lcx.springtest.v1;/**
 * Created by lichenxiang on 2018/8/23.
 */

import com.lcx.spring.context.ApplicationContext;
import com.lcx.spring.context.support.ClassPathXmlApplicationContext;
import com.lcx.spring.context.support.FileSystemXmlApplicationContext;
import com.lcx.springtest.bean.TestBean;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>  </p>
 *
 * @author lichenxiang
 * @version ApplicationContextTestV1: ApplicationContextTestV1.java, v 0.1 2018年08月23日 下午4:42:42 lichenxiang Exp $
 */
public class ApplicationContextTestV1 {

    @Test
    /**
     * 测试ClassPathXmlApplicationContext
     */
    public void getBeanFromClassPath(){

        //创建applicationContext
        ApplicationContext ctx = new ClassPathXmlApplicationContext("springtestv1.xml");
        //获取bean
        TestBean petStore = (TestBean)ctx.getBean("testBean");
        //验证bean状态
        Assert.assertNotNull(petStore);

    }


    @Test
    /**
     * 测试FileSystemXmlApplicationContext
     */
    public void getBeanFromSystemPath(){

        //创建applicationContext
		ApplicationContext ctx = new FileSystemXmlApplicationContext("/Users/lichenxiang/Downloads/lcxspring/src/test/resources/springtestv1.xml");
        //获取bean
        TestBean petStore = (TestBean)ctx.getBean("testBean");
        //验证bean状态
		Assert.assertNotNull(petStore);

    }


}