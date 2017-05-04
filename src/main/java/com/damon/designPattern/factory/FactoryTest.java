package com.damon.designPattern.factory;

/**
 * FactoryTest
 *
 * @author damon
 * @date 2017/4/27
 */
public class FactoryTest {

    public static void main(String[] args) {
        IFactory factory = new ConcreteFactoryA();
        IProduct product = factory.produce();
        product.method();
    }
}
