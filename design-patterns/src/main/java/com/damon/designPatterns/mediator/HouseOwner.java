package com.damon.designPatterns.mediator;

/**
 * HouseOwner
 *
 * @author damon
 * @date 2017/5/8
 */
public class HouseOwner extends Person {

    HouseOwner(String name, Mediator mediator) {
        super(name, mediator);
    }

    public void contact(String message) {
        mediator.contact(message, this);
    }

    public void getMessage(String message) {
        System.out.println("HouseOwner : " + name + ", Get message : " + message);
    }
}
