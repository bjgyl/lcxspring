package com.lcx.spring.beans.factory.support;
/**
 * @Created by:  IntelliJ Idea 2017.1
 * @Author: lcx
 * @Date: 2018/10/10
 * @Time: 下午5:31
 * @Title: BeanDefinitionValueResolver
 * @ProjectName: lcxspring
 * @Description: TODO
 */

import com.lcx.spring.beans.factory.BeanFactory;
import com.lcx.spring.beans.factory.config.RuntimeBeanReference;
import com.lcx.spring.beans.factory.config.TypedStringValue;

/**
 *
 * <p>bean定义值解析</p>
 * @author lichenxiang
 * @version BeanDefinitionValueResolver: BeanDefinitionValueResolver.java, v 0.1 2018年10月10日 下午5:31:31 lichenxiang Exp $
 *
 */

public class BeanDefinitionValueResolver {
    private final BeanFactory beanFactory;

    public BeanDefinitionValueResolver(
            BeanFactory beanFactory) {

        this.beanFactory = beanFactory;
    }

    public Object resolveValueIfNecessary(Object value) {

        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference ref = (RuntimeBeanReference) value;
            String refName = ref.getBeanName();
            Object bean = this.beanFactory.getBean(refName);
            return bean;

        }else if (value instanceof TypedStringValue) {
            return ((TypedStringValue) value).getValue();
        } else{
            //TODO 还有很多其他类型
            throw new RuntimeException("the value " + value +" has not implemented");
        }
    }
}