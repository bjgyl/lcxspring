package com.lcx.spring.beans.factory;


import com.lcx.spring.beans.BeansException;

/**
 * bean定义读取异常
 */
public class BeanDefinitionStoreException extends BeansException {

	public BeanDefinitionStoreException(String msg, Throwable cause) {
		super(msg, cause);
		
	}
	
}
