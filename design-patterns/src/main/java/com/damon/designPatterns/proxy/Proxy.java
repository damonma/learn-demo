package com.damon.designPatterns.proxy;

/**
 * Proxy
 *
 * @author damon
 * @date 2017/5/4
 */
public class Proxy implements Subject {

    private Subject subject = null;

    public void operate() {
        if (null == subject) {
            subject = new RealSubject();
        }
        System.out.println("I'm Proxy, I'm invoking ");
        subject.operate();
    }
}
