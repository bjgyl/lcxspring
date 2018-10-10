package com.lcx.spring.beans;

public class PropertyValue {

	/**
	 * bean名称
	 */
	private final String name;

	/**
	 * 引用对象  RuntimeBeanReference 或 TypeStringValue
	 */
	private final Object value;

	private boolean converted = false;

	private Object convertedValue;
	
	public PropertyValue(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Object getValue() {
		return this.value;
	}
	public synchronized boolean isConverted() {
		return this.converted;
	}

	
	public synchronized void setConvertedValue(Object value) {
		this.converted = true;
		this.convertedValue = value;
	}
	
	public synchronized Object getConvertedValue() {
		return this.convertedValue;
	}

}