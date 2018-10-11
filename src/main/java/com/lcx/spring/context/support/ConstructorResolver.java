package com.lcx.spring.context.support;

import com.lcx.spring.beans.BeanDefinition;
import com.lcx.spring.beans.ConstructorArgument;
import com.lcx.spring.beans.SimpleTypeConverter;
import com.lcx.spring.beans.factory.BeanCreationException;
import com.lcx.spring.beans.factory.config.ConfigurableBeanFactory;
import com.lcx.spring.beans.factory.support.BeanDefinitionValueResolver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.lang.reflect.Constructor;
import java.util.List;


public class ConstructorResolver {

	protected final Log logger = LogFactory.getLog(getClass());

	private final ConfigurableBeanFactory beanFactory;
	
	public ConstructorResolver(ConfigurableBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	/**
	 * 注入构造
	 * @param bd
	 * @return
	 */
	public Object autowireConstructor(final BeanDefinition bd) {
		
		Constructor<?> constructorToUse = null;
		Object[] argsToUse = null;
	
		Class<?> beanClass = null;
		try {
			//获取当前bean的class
			beanClass = this.beanFactory.getBeanClassLoader().loadClass(bd.getBeanClassName());
			
		} catch (ClassNotFoundException e) {
			throw new BeanCreationException( bd.getID(), "Instantiation of bean failed, can't resolve class", e);
		}	
		
		//获取bean的构造函数 数组
		Constructor<?>[] candidates = beanClass.getConstructors();
		
		//生成bean定义值解析器
		BeanDefinitionValueResolver valueResolver =
				new BeanDefinitionValueResolver(this.beanFactory);

		//获取bean定义中的构造列表
		ConstructorArgument cargs = bd.getConstructorArgument();

		//生成类型转换器
		SimpleTypeConverter typeConverter = new SimpleTypeConverter();

		//遍历构造函数数组
		for(int i=0; i<candidates.length;i++){

			//获取当前循环的构造函数 参数 数组
			Class<?>[] parameterTypes = candidates[i].getParameterTypes();

			//如果当前的构造函数 参数数组的个数  =！ bean定义中的构造参数个数 跳出本次循环
			if(parameterTypes.length != cargs.getArgumentCount()){
				continue;
			}

			//创建一个空的 待使用的空数组
			argsToUse = new Object[parameterTypes.length];

			//进行值匹配
			boolean result = this.valuesMatchTypes(parameterTypes, 
					cargs.getArgumentValues(), 
					argsToUse, 
					valueResolver, 
					typeConverter);

			//值是否匹配
			if(result){
				//获取当前构造器
				constructorToUse = candidates[i];
				break;
			}
			
		}
		
		
		//找不到一个合适的构造函数
		if(constructorToUse == null){
			throw new BeanCreationException( bd.getID(), "can't find a apporiate constructor");
		}
		
		
		try {
			//构造器 和 构造器参数 创建新对象
			return constructorToUse.newInstance(argsToUse);
		} catch (Exception e) {
			throw new BeanCreationException( bd.getID(), "can't find a create instance using "+constructorToUse);
		}		
		
		
	}

	/**
	 * 值匹配
	 * @param parameterTypes	类定义中的 某个构造的参数列表
	 * @param valueHolders		bean定义中 构造的参数列表
	 * @param argsToUse			待匹配的空数组
	 * @param valueResolver		值解析器
	 * @param typeConverter		类型转换器
	 * @return	是否匹配
	 */
	private boolean valuesMatchTypes(Class<?>[] parameterTypes,
			List<ConstructorArgument.ValueHolder> valueHolders,
			Object[] argsToUse,
			BeanDefinitionValueResolver valueResolver,
			SimpleTypeConverter typeConverter ){
		
		
		for(int i=0;i<parameterTypes.length;i++){
			ConstructorArgument.ValueHolder valueHolder 
				= valueHolders.get(i);
			//获取参数的值，可能是TypedStringValue, 也可能是RuntimeBeanReference
			Object originalValue = valueHolder.getValue();
			
			try{
				//获得真正的值
				Object resolvedValue = valueResolver.resolveValueIfNecessary( originalValue);
				//如果参数类型是 int, 但是值是字符串,例如"3",还需要转型
				//如果转型失败，则抛出异常。说明这个构造函数不可用
				Object convertedValue = typeConverter.convertIfNecessary(resolvedValue, parameterTypes[i]);
				//转型成功，记录下来
				argsToUse[i] = convertedValue;
			}catch(Exception e){
				logger.error(e);
				return false;
			}				
		}
		return true;
	}
	

}
