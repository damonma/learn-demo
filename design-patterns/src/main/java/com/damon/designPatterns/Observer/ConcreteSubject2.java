package com.damon.designPatterns.Observer;

/**
 * ConcreteSubject2
 *
 * @author damon
 * @date 2017/5/8
 */
public class ConcreteSubject2 extends Subject2 {

    private String subjectState;

    public String getState(){
        return subjectState;
    }

    public void setState(String newState)
    {
        this.subjectState = newState;
        System.out.println("主题状态为： "+subjectState);
        this.notifyObservers();
    }
}
