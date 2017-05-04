package com.damon.designPattern.proxy;

/**
 * RealSubject
 *
 * @author damon
 * @date 2017/5/4
 */
public class RealSubject implements Subject {

    public void operate() {
        System.out.println("Real Subject");
    }
}
