package com.damon.designPatterns.Observer;

/**
 * ConcreteSubject
 *
 * @author damon
 * @date 2017/5/8
 */
public class ConcreteSubject extends Subject {

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        System.out.println("Subject: " + state);
        this.notifyObservers(state);
    }
}
