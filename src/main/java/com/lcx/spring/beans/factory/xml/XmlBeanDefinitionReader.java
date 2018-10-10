package com.lcx.spring.beans.factory.xml;/**
 * Created by lichenxiang on 2018/8/23.
 */

import com.lcx.spring.beans.BeanDefinition;
import com.lcx.spring.beans.factory.BeanDefinitionStoreException;
import com.lcx.spring.beans.factory.BeanFactory;
import com.lcx.spring.beans.factory.support.BeanDefinitionRegistry;
import com.lcx.spring.beans.factory.support.GenericBeanDefinition;
import com.lcx.spring.core.io.Resource;
import com.lcx.spring.util.ClassUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * <p>xmlbean定义读取器</p>
 *
 * @author lichenxiang
 * @version : XmlBeanDefinitionReader.java, v 0.1 2018年08月23日 下午4:05:05 lichenxiang Exp $
 */
public class XmlBeanDefinitionReader {

    //bean的Id
    public static final String ID_ATTRIBUTE = "id";

    //bean的全路径类名
    public static final String CLASS_ATTRIBUTE = "class";

    //scope属性
    public static final String SCOPE_ATTRIBUTE = "scope";

    //bean定义的注册类
    public BeanDefinitionRegistry registry;

    //初始化xml读取器时 同时初始化bean定义注册类
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    //加载bean定义
    public void loadBeanDefinitions(Resource resource) {
        InputStream is = null;
        try{
            is = resource.getInputStream();

            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);

            Element root = doc.getRootElement(); //<beans>
            Iterator<Element> iter = root.elementIterator();
            while(iter.hasNext()){
                Element ele = (Element)iter.next();
                String id = ele.attributeValue(ID_ATTRIBUTE);
                String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition bd = new GenericBeanDefinition(id,beanClassName);

                //设置scope
                if (ele.attribute(SCOPE_ATTRIBUTE)!=null) {
                    bd.setScope(ele.attributeValue(SCOPE_ATTRIBUTE));
                }

                //将组装后的bean定义注册到容器中
                 this.registry.registerBeanDefinition(id, bd);
            }
        } catch (Exception e) {
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