package com.damon.designPatterns.builder;

/**
 * Car
 *
 * @author damon
 * @date 2017/4/28
 */
public class Car {

    private String wheel;

    private String skeleton;

    private String engine;

    public String getWheel() {
        return wheel;
    }

    public void setWheel(String wheel) {
        this.wheel = wheel;
    }

    public String getSkeleton() {
        return skeleton;
    }

    public void setSkeleton(String skeleton) {
        this.skeleton = skeleton;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }
}
