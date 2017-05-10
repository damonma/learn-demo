package com.damon.designPatterns.mediator;

/**
 * Tenant
 *
 * @author damon
 * @date 2017/5/8
 */
public class Tenant extends Person {

    Tenant(String name, Mediator mediator) {
        super(name, mediator);
    }

    public void contact(String message) {
        mediator.contact(message, this);
    }

    public void getMessage(String message) {
        System.out.println("Tenant : " + name + ", Get message : " + message);
    }
}
