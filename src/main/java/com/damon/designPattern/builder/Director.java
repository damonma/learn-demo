package com.damon.designPattern.builder;

/**
 * Director
 *
 * @author damon
 * @date 2017/4/28
 */
public class Director {

    public Car constructCar(IBuilder builder) {
        builder.buildWheel();
        builder.buildSkeleton();
        builder.buildEngine();
        return builder.buildCar();
    }
}
