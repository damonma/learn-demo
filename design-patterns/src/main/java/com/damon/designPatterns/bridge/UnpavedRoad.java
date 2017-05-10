package com.damon.designPatterns.bridge;

/**
 * UnpavedRoad
 *
 * @author damon
 * @date 2017/4/28
 */
public class UnpavedRoad extends Road {

    public UnpavedRoad(Vehicle vehicle) {
        super(vehicle);
    }

    public void driveOnRoad() {
        super.vehicle.drive();
        System.out.println("drive on the unpaved road");
    }
}
