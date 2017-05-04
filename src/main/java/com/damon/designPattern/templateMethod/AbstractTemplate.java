package com.damon.designPattern.templateMethod;

/**
 * AbstractTemplate
 *
 * @author damon
 * @date 2017/5/4
 */
public abstract class AbstractTemplate {

    public void templateMethod() {
        abstractMethod();
        concreteMethod();
        doHookMethod();
    }

    protected abstract void abstractMethod();

    protected final void concreteMethod() {
        System.out.println("invoke concreteMethod");
    }

    protected void doHookMethod(){}
}
