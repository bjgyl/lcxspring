package com.lcx.spring.beans.factory.config;

/**
 * bean属性 value的值
 */
public class TypedStringValue {

	private String value;

	public TypedStringValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
