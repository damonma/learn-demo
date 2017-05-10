package com.damon.designPatterns.abstractFactory;

/**
 * AbstractFactory
 * 抽象工厂
 *
 * @author damon
 * @date 2017/4/27
 */
public interface AbstractFactory {

    public AbstractProductA CreateProductA();

    public AbstractProductB CreateProductB();
}
