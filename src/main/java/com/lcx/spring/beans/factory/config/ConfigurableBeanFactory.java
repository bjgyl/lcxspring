package com.lcx.spring.beans.factory.config;


import com.lcx.spring.beans.factory.BeanFactory;

public interface ConfigurableBeanFactory extends BeanFactory {

	/**
	 * 设置beanClassLoader
	 * @param beanClassLoader
	 */
	void setBeanClassLoader(ClassLoader beanClassLoader);

	/**
	 * 获取beanClassLoader
	 * @return
	 */
	ClassLoader getBeanClassLoader();

}
