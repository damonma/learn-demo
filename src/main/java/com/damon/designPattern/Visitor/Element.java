package com.damon.designPattern.Visitor;

/**
 * Element
 *
 * @author damon
 * @date 2017/5/8
 */
public abstract class Element {

    public abstract void accept(IVisitor visitor);

    public abstract void doSomething();
}
