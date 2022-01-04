package com.damon.designPatterns.builder;

/**
 * ConcreteBuilder
 *
 * @author damon
 * @date 2017/4/28
 */
public class ConcreteBuilder implements IBuilder {

    Car car;

    public ConcreteBuilder() {
        car = new Car();
    }

    public void buildWheel() {
        car.setWheel("Wheel");
    }

    public void buildSkeleton() {
        car.setSkeleton("Skeleton");
    }

    public void buildEngine() {
        car.setEngine("Engine");
    }

    public Car buildCar() {
        return this.car;
    }
}
