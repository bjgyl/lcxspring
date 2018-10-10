package com.lcx.springtest.v1;/**
 * Created by lichenxiang on 2018/8/23.
 */

import com.lcx.spring.context.ApplicationContext;
import com.lcx.spring.context.support.ClassPathXmlApplicationContext;
import com.lcx.springtest.bean.TestBean;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>  </p>
 *
 * @author lichenxiang
 * @version ApplicationContextTestV2: ApplicationContextTestV2.java, v 0.1 2018年08月23日 下午4:42:42 lichenxiang Exp $
 */
public class ApplicationContextTestV2 {

    @Test
    public void getBean(){

        //创建applicationContext
        ApplicationContext ctx = new ClassPathXmlApplicationContext("springtestv1.xml");
        //获取bean
        TestBean petStore = (TestBean)ctx.getBean("testBean");
        //验证bean状态
        Assert.assertNotNull(petStore);

    }

}