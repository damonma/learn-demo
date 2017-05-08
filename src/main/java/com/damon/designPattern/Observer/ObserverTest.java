package com.damon.designPattern.Observer;

/**
 * ObserverTest
 *
 * @author damon
 * @date 2017/5/8
 */
public class ObserverTest {

    public static void main(String[] args) {
//        ConcreteSubject subject = new ConcreteSubject();
//        Observer observer = new ConcreteObserver();
//        subject.attach(observer);
//        subject.setState("new State");
        ConcreteSubject2 subject = new ConcreteSubject2();
        Observer2 observer = new ConcreteObserver2();
        subject.attach(observer);
        subject.setState("new State");
    }
}
