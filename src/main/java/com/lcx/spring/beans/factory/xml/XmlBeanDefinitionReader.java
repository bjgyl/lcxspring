package com.lcx.spring.beans.factory.xml;/**
 * Created by lichenxiang on 2018/8/23.
 */

import com.lcx.spring.beans.BeanDefinition;
import com.lcx.spring.beans.factory.BeanDefinitionStoreException;
import com.lcx.spring.beans.factory.BeanFactory;
import com.lcx.spring.beans.factory.support.BeanDefinitionRegistry;
import com.lcx.spring.beans.factory.support.GenericBeanDefinition;
import com.lcx.spring.util.ClassUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * <p>  </p>
 *
 * @author lichenxiang
 * @version : XmlBeanDefinitionReader.java, v 0.1 2018年08月23日 下午4:05:05 lichenxiang Exp $
 */
public class XmlBeanDefinitionReader {

    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    public BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void loadBeanDefinitions(String config) {
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
                this.registry.registerBeanDefinition(id, bd);
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
}