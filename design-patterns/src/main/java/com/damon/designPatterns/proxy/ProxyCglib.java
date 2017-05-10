package com.damon.designPatterns.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * ProxyCglib
 *
 * @author damon
 * @date 2017/5/4
 */
public class ProxyCglib implements MethodInterceptor {

    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        // 加强器，创建动态代理
        Enhancer enhancer = new Enhancer();
        // 设置要动态代理类
        enhancer.setSuperclass(this.target.getClass());
        // 设置回调，代理类所有方法调用时都会回调Callback，而Callback需要实现intercept方法进行拦截
        enhancer.setCallback(this);
        Object object = enhancer.create();
        return object;
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("I'm proxy, I'm invoking");
        Object object = methodProxy.invokeSuper(obj, args);
        System.out.println(object);
        return object;
    }
}
