package com.damon.designPatterns.templateMethod;

/**
 * ConcreteTemplate
 *
 * @author damon
 * @date 2017/5/4
 */
public class ConcreteTemplate extends AbstractTemplate {

    protected void abstractMethod() {
        System.out.println("invoke abstractMethod");
    }

    protected void doHookMethod() {
        System.out.println("invoke doHookMethod");
    }
}
