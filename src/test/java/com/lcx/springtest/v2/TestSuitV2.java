package com.lcx.springtest.v2;/**
 * Created by lichenxiang on 2018/8/23.
 */

import com.lcx.springtest.v1.ApplicationContextTestV1;
import com.lcx.springtest.v1.BeanFactoryTest;
import com.lcx.springtest.v1.ResourceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * <p>  </p>
 *
 * @author lichenxiang
 * @version TestSuit: TestSuit.java, v 0.1 2018年08月23日 下午6:45:45 lichenxiang Exp $
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    ApplicationContextTestV2.class,
        BeanDefinitionTest.class,
        BeanDefinitionValueResolverTest.class,
        CustomBooleanEditorTest.class,
        CustomNumberEditorTest.class,
        TypeConverterTest.class
})
public class TestSuitV2 {


}