package com.lagou.edu.factory;

/*
 一些声明信息
 Description: <br/>
 date: 2023/8/19 13:54<br/>
 @author SAg <br/>
 version 1.0
 since JDK 1.8
*/

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * InterfaceName: BeanFactory <br/>
 * Description: 工厂类，生产对象（使用反射技术）<br/>
 * date: 2023/8/19 13:54<br/>
 * <p>
 * XPath 使用路径表达式来选取 XML文档中的节点或节点集<br/>
 * nodename 选取此节点的所有子节点<br/>
 * / 从根节点选取<br/>
 * // 从匹配选择的当前节点<br/>
 * . 选取当前节点<br/>
 * .. 选取当前节点的父节点<br/>
 * 【@】 选取属性 <p>
 *
 * @author SAg <br/>
 */
public class BeanFactory {
    //任务1：读取解析xml，通过反射技术实力化对象并且存储待用（map集合）
    //任务2：对外提供获取实力对象的接口（根据ID获取）
    private static Map<String, Object> map = new HashMap<>(); // 存储对象

    static {
        // 任务1：读取xml，通过反射技术实力化对象并且存储待用（map集合）
        // 1.1 类加载器
        ClassLoader classLoader = BeanFactory.class.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("beans.xml");
        // 1.2 解析xml
        SAXReader saxReader = new SAXReader();
        try {
            Document read = saxReader.read(resourceAsStream);
            // 获取根节点
            Element rootElement = read.getRootElement();
            // 找到下级元素
            List<Element> list = rootElement.selectNodes("//bean");
            list.forEach(it -> {
                // 处理每一个bean，获取到该元素的id和class属性
                String id = it.attributeValue("id");  // accountDao
                String clazz = it.attributeValue("class"); // com.lagou.edu.dao.impl.JdbcAccountDaoImpl
                // 通过反射技术实力化对象
                Object o = null;
                Class<?> aClass = null;
                try {
                    aClass = Class.forName(clazz);
                    // 实例化对象
                    o = aClass.newInstance();
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                // 存储到map中待用
                map.put(id, o);
            });
            // 实例化完成之后完成对象的依赖关系，检查哪些对象需要传值进去，根据它的配置，我们传入对应的值
            // 有property子元素的bean就有传值需求
            List<Element> propertyList = rootElement.selectNodes("//property");
            //<property name="TransferService" ref="transferService"></property>
            propertyList.forEach(it -> {
                String name = it.attributeValue("name");
                String ref = it.attributeValue("ref");
                // 找到当前需要被处理依赖关系的bean
                Element parent = it.getParent();
                // 调用父元素对象的反射功能
                String id = parent.attributeValue("id");
                Object parentObject = map.get(id);
                // 遍历父对象中的所有方法，找到 set + name
                Method[] methods = parentObject.getClass().getMethods();
                for (int i = 0; i < methods.length; i++) {
                    Method method = methods[i];
                    if(method.getName().equalsIgnoreCase("set" + name)) {
                        try {
                            method.invoke(parentObject,map.get(ref));
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    // 把处理完的parentObject重新放入map中
                    map.put(id,parentObject);
                }
            });
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对外提供获取实例对象的接口（根据ID获取）
     *
     * @param id 名称
     * @return
     */
    public static Object getBean(String id) {
        return map.get(id);
    }
}
