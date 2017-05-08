package com.damon.designPattern.strategy;

/**
 * Context
 *
 * @author damon
 * @date 2017/5/8
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void doOperate() {
        this.strategy.operate();
    }
}
