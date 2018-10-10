package com.lcx.spring.beans.factory.config;

/**
 * bean属性 ref的bean
 */
public class RuntimeBeanReference {

	private final String beanName;

	public RuntimeBeanReference(String beanName) {
		this.beanName = beanName;
	}

	public String getBeanName() {
		return this.beanName;
	}
}
