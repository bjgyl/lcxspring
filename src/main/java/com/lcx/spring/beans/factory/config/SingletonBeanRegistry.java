package com.lcx.spring.beans.factory.config;

public interface SingletonBeanRegistry {

	/**
	 * 注册单例bean
	 * @param beanName
	 * @param singletonObject
	 */
	void registerSingleton(String beanName, Object singletonObject);

	/**
	 * 获取单例bean
	 * @param beanName
	 * @return
	 */
	Object getSingleton(String beanName);
}
