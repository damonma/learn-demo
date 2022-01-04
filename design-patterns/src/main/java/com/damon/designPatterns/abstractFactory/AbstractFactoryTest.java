package com.damon.designPatterns.abstractFactory;

/**
 * AbstractFactoryTest
 *
 * @author damon
 * @date 2017/4/27
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {
        AbstractFactory factory = new ConcreteFactory1();
        AbstractProductA productA = factory.CreateProductA();
        AbstractProductB productB = factory.CreateProductB();
        productA.method();
        productB.method();
    }
}
