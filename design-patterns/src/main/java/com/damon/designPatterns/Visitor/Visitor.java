package com.damon.designPatterns.Visitor;

/**
 * Visitor
 *
 * @author damon
 * @date 2017/5/8
 */
public class Visitor implements IVisitor {

    public void visit(ConcreteElement1 element1) {
        element1.doSomething();
    }

    public void visit(ConcreteElement2 element2) {
        element2.doSomething();
    }
}
