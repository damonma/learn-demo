package com.damon.designPattern.mediator;

/**
 * Person
 *
 * @author damon
 * @date 2017/5/8
 */
public abstract class Person {

    protected String name;

    protected Mediator mediator;

    Person(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }
}
