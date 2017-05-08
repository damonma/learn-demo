package com.damon.designPattern.Visitor;

/**
 * IVisitor
 *
 * @author damon
 * @date 2017/5/8
 */
public interface IVisitor {

    public void visit(ConcreteElement1 e1);

    public void visit(ConcreteElement2 e2);
}
