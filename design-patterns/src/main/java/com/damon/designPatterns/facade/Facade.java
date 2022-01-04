package com.damon.designPatterns.facade;

/**
 * Facade
 *
 * @author damon
 * @date 2017/5/3
 */
public class Facade {

    private Class1 class1 = new Class1();

    private Class2 class2 = new Class2();

    public void method1() {
        class1.method();
    }

    public void method2() {
        class2.method();
    }

    public void method() {
        class1.method();
        class2.method();
    }
}
