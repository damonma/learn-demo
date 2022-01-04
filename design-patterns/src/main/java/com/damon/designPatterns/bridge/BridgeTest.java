package com.damon.designPatterns.bridge;

/**
 * BridgeTest
 *
 * @author damon
 * @date 2017/4/28
 */
public class BridgeTest {

    public static void main(String[] args) {
        Vehicle vehicle = new Car();
        Road road = new CementRoad(vehicle);
        road.driveOnRoad();
    }
}
