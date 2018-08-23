package com.lcx.spring.beans.factory;/**
 * Created by lichenxiang on 2018/6/27.
 */

import com.lcx.spring.beans.BeanDefinition;

/**
 * <p>bean工厂</p>
 *
 * @author lichenxiang
 * @version : BeanFactory.java, v 0.1 2018年06月27日 下午7:38:38 lichenxiang Exp $
 */
public interface BeanFactory {

    /**
     * 获取bean
     * @param beanId beanId
     * @return
     */
    Object getBean(String beanId);

}