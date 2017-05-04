package com.damon.designPattern.bridge;

/**
 * CementRoad
 *
 * @author damon
 * @date 2017/4/28
 */
public class CementRoad extends Road {

    public CementRoad(Vehicle vehicle) {
        super(vehicle);
    }

    public void driveOnRoad() {
        super.vehicle.drive();
        System.out.println("drive on the cement road");
    }
}
