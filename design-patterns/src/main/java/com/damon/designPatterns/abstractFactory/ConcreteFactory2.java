package com.damon.designPatterns.abstractFactory;

/**
 * ConcreteFactory2
 * 工厂2
 *
 * @author damon
 * @date 2017/4/27
 */
public class ConcreteFactory2 implements AbstractFactory {

    public AbstractProductA CreateProductA() {
        return new ProductA2();
    }

    public AbstractProductB CreateProductB() {
        return new ProductB2();
    }
}
