package com.damon.designPatterns.decorator;

/**
 * Condiment
 *
 * @author damon
 * @date 2017/4/28
 */
public abstract class Condiment extends Pancake {

    public abstract String getName();

    public void sold() {
        System.out.println(getName() + ": " + getPrice());
    }
}
