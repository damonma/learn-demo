package com.damon.designPatterns.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject
 *
 * @author damon
 * @date 2017/5/8
 */
public abstract class Subject {

    private List<Observer> observers = new ArrayList<Observer>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String str) {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(str);
        }
    }
}
