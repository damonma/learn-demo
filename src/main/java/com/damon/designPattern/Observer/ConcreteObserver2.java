package com.damon.designPattern.Observer;

/**
 * ConcreteObserver2
 *
 * @author damon
 * @date 2017/5/8
 */
public class ConcreteObserver2 implements Observer2 {

    private String observerState;

    public void update(Subject2 subject)
    {
        observerState = ((ConcreteSubject2)subject).getState();
        System.out.println("状态为: "+observerState);
    }
}
