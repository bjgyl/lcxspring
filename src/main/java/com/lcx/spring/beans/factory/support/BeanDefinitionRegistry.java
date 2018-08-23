package com.lcx.spring.beans.factory.support;/**
 * Created by lichenxiang on 2018/8/23.
 */

import com.lcx.spring.beans.BeanDefinition;

/**
 * <p>  </p>
 *
 * @author lichenxiang
 * @version : BeanDefinitionRegistry.java, v 0.1 2018年08月23日 下午4:08:08 lichenxiang Exp $
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册beanDefinition
     * @param id beanId
     * @param bd bean定义
     */
    public void registerBeanDefinition(String id, BeanDefinition bd);

    /**
     * 获取bean定义
     * @param beanId beanId
     * @return bean定义
     */
    BeanDefinition getBeanDefinition(String beanId);
}