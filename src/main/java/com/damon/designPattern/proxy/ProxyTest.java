package com.damon.designPattern.proxy;

/**
 * ProxyTest
 *
 * @author damon
 * @date 2017/5/4
 */
public class ProxyTest {

    public static void main(String[] args) {
//        Subject subject = new Proxy();
//        subject.operate();
//        Subject subject = (Subject) new ProxyHandler().newProxyInstance(new RealSubject());
//        subject.operate();
        ProxyCglib proxyCglib = new ProxyCglib();
        CglibRealSubject subject = (CglibRealSubject) proxyCglib.getInstance(new CglibRealSubject());
        subject.operate();
    }
}
