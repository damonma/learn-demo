package com.damon.designPattern.Visitor;

/**
 * ConcreteElement2
 *
 * @author damon
 * @date 2017/5/8
 */
public class ConcreteElement2 extends Element {

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public void doSomething() {
        System.out.println("Element2");
    }
}
