package com.lcx.springtest;
/**
 * @Created by:  IntelliJ Idea 2017.1
 * @Author: lcx
 * @Date: 2018/10/11
 * @Time: 上午10:16
 * @Title: AllTest
 * @ProjectName: lcxspring
 * @Description: TODO
 */

import com.lcx.springtest.v1.TestSuitV1;
import com.lcx.springtest.v2.TestSuitV2;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * <p>  </p>
 * @author lichenxiang
 * @version AllTest: AllTest.java, v 0.1 2018年10月11日 上午10:16:16 lichenxiang Exp $
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TestSuitV1.class,TestSuitV2.class})
public class AllTest {

}
