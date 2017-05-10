package com.damon.designPatterns.flyweight;

/**
 * ConcreteFlyweight
 *
 * @author damon
 * @date 2017/5/4
 */
public class ConcreteFlyweight implements Flyweight {

    private String str;

    public ConcreteFlyweight(String str) {
        this.str = str;
    }

    @Override
    public void operation(String state) {
        System.out.println("inner state: " + str);
        System.out.println("out state: " + state);
    }
}
