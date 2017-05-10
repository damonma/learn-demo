package com.damon.designPatterns.bridge;

/**
 * Road
 *
 * @author damon
 * @date 2017/4/28
 */
public abstract class Road {

    protected Vehicle vehicle;

    public Road(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public abstract void driveOnRoad();
}
