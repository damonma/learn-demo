package com.damon.designPatterns.proxy;

import java.lang.reflect.*;

/**
 * ProxyHandler
 *
 * @author damon
 * @date 2017/5/4
 */
public class ProxyHandler implements InvocationHandler {

    private Object target = null;

    public Object newProxyInstance(Object target) {
        this.target = target;
        Class<?> classType = target.getClass();
        return java.lang.reflect.Proxy.newProxyInstance(classType.getClassLoader(), classType.getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("I'm proxy, I'm invoking");
        method.invoke(target, args);
        System.out.println("proxy end");
        return null;
    }
}
