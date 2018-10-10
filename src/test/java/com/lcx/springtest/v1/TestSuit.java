package com.lcx.springtest.v1;/**
 * Created by lichenxiang on 2018/8/23.
 */

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
        ApplicationContextTestV1.class,
        BeanFactoryTest.class,
        ResourceTest.class
})
public class TestSuit {


}