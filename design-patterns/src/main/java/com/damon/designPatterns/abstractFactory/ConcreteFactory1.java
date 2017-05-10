package com.damon.designPatterns.abstractFactory;

/**
 * ConcreteFactory1
 * 工厂1
 *
 * @author damon
 * @date 2017/4/27
 */
public class ConcreteFactory1 implements AbstractFactory {

    public AbstractProductA CreateProductA() {
        return new ProductA1();
    }

    public AbstractProductB CreateProductB() {
        return new ProductB1();
    }
}
