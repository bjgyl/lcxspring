package com.lcx.spring.beans.factory.xml;/**
 * Created by lichenxiang on 2018/8/23.
 */

import com.lcx.spring.beans.BeanDefinition;
import com.lcx.spring.beans.ConstructorArgument;
import com.lcx.spring.beans.PropertyValue;
import com.lcx.spring.beans.factory.BeanDefinitionStoreException;
import com.lcx.spring.beans.factory.BeanFactory;
import com.lcx.spring.beans.factory.config.RuntimeBeanReference;
import com.lcx.spring.beans.factory.config.TypedStringValue;
import com.lcx.spring.beans.factory.support.BeanDefinitionRegistry;
import com.lcx.spring.beans.factory.support.GenericBeanDefinition;
import com.lcx.spring.core.io.Resource;
import com.lcx.spring.util.ClassUtils;
import com.lcx.spring.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    //propertity标签
    public static final String PROPERTY_ELEMENT = "property";

    //ref属性
    public static final String REF_ATTRIBUTE = "ref";

    //value属性
    public static final String VALUE_ATTRIBUTE = "value";

    //name属性
    public static final String NAME_ATTRIBUTE = "name";

    //构造标签
    public static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";

    //构造属性
    public static final String TYPE_ATTRIBUTE = "type";

    //bean定义的注册类
    public BeanDefinitionRegistry registry;

    protected final Log logger = LogFactory.getLog(getClass());

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

                //解析构造
                parseConstructorArgElements(ele,bd);

                //解析propertity
                parsePropertyElement(ele,bd);

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

    public void parsePropertyElement(Element beanElem, BeanDefinition bd) {
        Iterator iter= beanElem.elementIterator(PROPERTY_ELEMENT);
        while(iter.hasNext()){
            Element propElem = (Element)iter.next();
            String propertyName = propElem.attributeValue(NAME_ATTRIBUTE);
            if (!StringUtils.hasLength(propertyName)) {
                logger.warn("Tag 'property' must have a 'name' attribute");
                return;
            }


            Object val = parsePropertyValue(propElem, bd, propertyName);
            PropertyValue pv = new PropertyValue(propertyName, val);

            bd.getPropertyValues().add(pv);
        }

    }

    public void parseConstructorArgElements(Element beanEle, BeanDefinition bd) {
        Iterator iter = beanEle.elementIterator(CONSTRUCTOR_ARG_ELEMENT);
        while(iter.hasNext()){
            Element ele = (Element)iter.next();
            parseConstructorArgElement(ele, bd);
        }

    }
    public void parseConstructorArgElement(Element ele, BeanDefinition bd) {

        String typeAttr = ele.attributeValue(TYPE_ATTRIBUTE);
        String nameAttr = ele.attributeValue(NAME_ATTRIBUTE);
        Object value = parsePropertyValue(ele, bd, null);
        ConstructorArgument.ValueHolder valueHolder = new ConstructorArgument.ValueHolder(value);
        if (StringUtils.hasLength(typeAttr)) {
            valueHolder.setType(typeAttr);
        }
        if (StringUtils.hasLength(nameAttr)) {
            valueHolder.setName(nameAttr);
        }

        bd.getConstructorArgument().addArgumentValue(valueHolder);
    }



    public Object parsePropertyValue(Element ele, BeanDefinition bd, String propertyName) {
        String elementName = (propertyName != null) ?
                "<property> element for property '" + propertyName + "'" :
                "<constructor-arg> element";


        boolean hasRefAttribute = (ele.attribute(REF_ATTRIBUTE)!=null);
        boolean hasValueAttribute = (ele.attribute(VALUE_ATTRIBUTE) !=null);

        if (hasRefAttribute) {
            String refName = ele.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                logger.error(elementName + " contains empty 'ref' attribute");
            }
            RuntimeBeanReference ref = new RuntimeBeanReference(refName);
            return ref;
        }else if (hasValueAttribute) {
            TypedStringValue valueHolder = new TypedStringValue(ele.attributeValue(VALUE_ATTRIBUTE));

            return valueHolder;
        }
        else {

            throw new RuntimeException(elementName + " must specify a ref or value");
        }
    }



}