package com.damon.designPatterns.Visitor;

/**
 * ConcreteElement1
 *
 * @author damon
 * @date 2017/5/8
 */
public class ConcreteElement1 extends Element {

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public void doSomething() {
        System.out.println("Element1");
    }
}
