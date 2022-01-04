package com.damon.designPatterns.factory;

/**
 * ConcreteFactoryA
 *
 * @author damon
 * @date 2017/4/27
 */
public class ConcreteFactoryA implements IFactory {

    public IProduct produce() {
        return new ProductA();
    }
}
