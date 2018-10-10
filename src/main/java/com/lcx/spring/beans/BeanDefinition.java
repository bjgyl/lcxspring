package com.lcx.spring.beans;/**
 * Created by lichenxiang on 2018/6/27.
 */

import java.util.List;

/**
 * <p>bean定义</p>
 *
 * @author lichenxiang
 * @version : BeanDefinition.java, v 0.1 2018年06月27日 下午7:40:40 lichenxiang Exp $
 */
public interface BeanDefinition {

    /**
     * scope:单例标识
     */
    public static final String SCOPE_SINGLETON = "singleton";

    /**
     * scope:多例标识
     */
    public static final String SCOPE_PROTOTYPE = "prototype";

    /**
     * scope:默认标识
     */
    public static final String SCOPE_DEFAULT = "";

    /**
     * 是否是单例
     * @return
     */
    public boolean isSingleton();

    /**
     * 是否是多例
     * @return
     */
    public boolean isPrototype();

    /**
     * 获取scope
     * @return
     */
    String getScope();

    /**
     * 设置scope
     * @param scope
     */
    void setScope(String scope);

    /**
     * 获取className
     * @return
     */
    String getBeanClassName();

    /**
     * 属性列表
     * @return
     */
    public List<PropertyValue> getPropertyValues();
}