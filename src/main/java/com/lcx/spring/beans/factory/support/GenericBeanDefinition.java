package com.lcx.spring.beans.factory.support;/**
 * Created by lichenxiang on 2018/6/27.
 */

import com.lcx.spring.beans.BeanDefinition;

/**
 * <p>  </p>
 *
 * @author lichenxiang
 * @version : GenericBeanDefinition.java, v 0.1 2018年06月27日 下午7:53:53 lichenxiang Exp $
 */
public class GenericBeanDefinition implements BeanDefinition {

    private String id;

    private String beanClassName;

    public GenericBeanDefinition(String id, String beanClassName) {

        this.id = id;
        this.beanClassName = beanClassName;

    }

    public String getBeanClassName() {

        return this.beanClassName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }
}