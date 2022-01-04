package com.damon.designPatterns.builder;

/**
 * BuilderTest
 *
 * @author damon
 * @date 2017/4/28
 */
public class BuilderTest {

    public static void main(String[] args) {
        Director director = new Director();
        Car car = director.constructCar(new ConcreteBuilder());
        System.out.println(car.getWheel());
        System.out.println(car.getSkeleton());
        System.out.println(car.getEngine());
    }
}
