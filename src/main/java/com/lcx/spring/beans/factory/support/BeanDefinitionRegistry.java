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

    public void registerBeanDefinition(String id, BeanDefinition bd);

    BeanDefinition getBeanDefinition(String beanId);
}