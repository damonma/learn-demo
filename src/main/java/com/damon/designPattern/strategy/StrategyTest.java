package com.damon.designPattern.strategy;

/**
 * StrategyTest
 *
 * @author damon
 * @date 2017/5/8
 */
public class StrategyTest {

    public static void main(String[] args) {
        Context context = null;
        context = new Context(new ConcreteStrategyA());
        context.doOperate();
        context = new Context(new ConcreteStrategyB());
        context.doOperate();
        context = new Context(new ConcreteStrategyC());
        context.doOperate();
    }
}
