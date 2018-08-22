package com.lcx.spring.beans.factory;/**
 * Created by lichenxiang on 2018/6/27.
 */

import com.lcx.spring.beans.BeanDefinition;

/**
 * <p>  </p>
 *
 * @author lichenxiang
 * @version : BeanFactory.java, v 0.1 2018年06月27日 下午7:38:38 lichenxiang Exp $
 */
public interface BeanFactory {

    BeanDefinition getBeanDefinition(String beanId);

    Object getBean(String beanId);
}