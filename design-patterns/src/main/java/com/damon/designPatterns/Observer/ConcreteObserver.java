package com.damon.designPatterns.Observer;

/**
 * ConcreteObserver
 *
 * @author damon
 * @date 2017/5/8
 */
public class ConcreteObserver implements Observer {

    private String state;

    public void update(String state) {
        this.state = state;
        System.out.println("state: " + state);
    }
}
