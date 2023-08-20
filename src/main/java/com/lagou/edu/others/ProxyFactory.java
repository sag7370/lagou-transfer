package com.lagou.edu.others;

/*
 一些声明信息
 Description: <br/>
 date: 2023/8/20 08:05<br/>
 @author SAg <br/>
 version 1.0
 since JDK 1.8
*/

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ClassName: ProxyFactory <br/>
 * Description: 代理工厂：生成动态代理对象<br/>
 * date: 2023/8/20 08:05<br/>
 *
 * @author SAg <br/>
 */
public class ProxyFactory {

    private ProxyFactory() {

    }

    private static ProxyFactory proxyFactory = new ProxyFactory();

    public static ProxyFactory getInstance() {
        return proxyFactory;
    }

    /**
     * Jdk动态代理 ； 要求委托对象必须实现接口
     *
     * @param obj
     * @return
     */
    public Object getJdkProxy(Object obj) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                (Object proxy, Method method, Object[] args) -> {
                    // 增强逻辑
                    System.out.println("增强逻辑");
                    // 调用原有业务逻辑
                    Object result = method.invoke(obj, args);
                    // 增强逻辑
                    System.out.println("增强逻辑");
                    return result;
                });
    }

    /**
     * cglib动态代理
     *
     * @param obj 委托对象
     * @return
     */
    public Object getCglibProxy(Object obj) {
        return Enhancer.create(obj.getClass(),
                (MethodInterceptor) (obj1, method, args, proxy) -> {
                    // 增强逻辑
                    System.out.println("增强逻辑");
                    // 调用原有业务逻辑
                    Object result = method.invoke(obj1, args);
                    // 增强逻辑
                    System.out.println("增强逻辑");
                    return result;
                }
        );
    }
}
