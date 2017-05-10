package com.damon.designPatterns.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject2
 *
 * @author damon
 * @date 2017/5/8
 */
public abstract class Subject2 {

    private List<Observer2> observer2s = new ArrayList<Observer2>();

    public void attach(Observer2 observer2) {
        observer2s.add(observer2);
    }

    public void detach(Observer2 observer2) {
        observer2s.remove(observer2);
    }

    public void notifyObservers() {
        for (int i = 0; i < observer2s.size(); i++) {
            observer2s.get(i).update(this);
        }
    }
}
