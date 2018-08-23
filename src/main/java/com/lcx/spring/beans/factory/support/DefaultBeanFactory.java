package com.lcx.spring.beans.factory.support;/**
 * Created by lichenxiang on 2018/6/27.
 */

import com.lcx.spring.beans.BeanDefinition;
import com.lcx.spring.beans.factory.BeanCreationException;
import com.lcx.spring.beans.factory.BeanDefinitionStoreException;
import com.lcx.spring.beans.factory.BeanFactory;
import com.lcx.spring.util.ClassUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>  </p>
 *
 * @author lichenxiang
 * @version : DefaultBeanFactory.java, v 0.1 2018年06月27日 下午7:39:39 lichenxiang Exp $
 */
public class DefaultBeanFactory implements BeanFactory {

    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(64);


    public DefaultBeanFactory(String config) {

        loadBeanDefinition(config);

    }

    private void loadBeanDefinition(String config) {
        InputStream is = null;
        try{
            ClassLoader cl = ClassUtils.getDefaultClassLoader();
            is = cl.getResourceAsStream(config);

            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);

            Element root = doc.getRootElement(); //<beans>
            Iterator<Element> iter = root.elementIterator();
            while(iter.hasNext()){
                Element ele = (Element)iter.next();
                String id = ele.attributeValue(ID_ATTRIBUTE);
                String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition bd = new GenericBeanDefinition(id,beanClassName);
                this.beanDefinitionMap.put(id, bd);
            }
        } catch (DocumentException e) {
           throw new BeanDefinitionStoreException("Don't load xml,before check retry",e);
        }finally{
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public BeanDefinition getBeanDefinition(String beanId) {
        return this.beanDefinitionMap.get(beanId);
    }

    public Object getBean(String beanId) {
        BeanDefinition bd = this.getBeanDefinition(beanId);
        if(bd == null){
            throw new BeanCreationException("Bean Definition does not exist");
        }
        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        String beanClassName = bd.getBeanClassName();
        try {
            Class<?> clz = cl.loadClass(beanClassName);
            return clz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for "+ beanClassName +" failed",e);
        }
    }
}