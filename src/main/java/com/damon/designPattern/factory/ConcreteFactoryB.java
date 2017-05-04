package com.damon.designPattern.factory;

/**
 * ConcreteFactoryB
 *
 * @author damon
 * @date 2017/4/27
 */
public class ConcreteFactoryB implements IFactory {

    public IProduct produce() {
        return new ProductB();
    }
}
