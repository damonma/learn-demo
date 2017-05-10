package com.damon.designPatterns.prototype;

/**
 * Prototype
 *
 * @author damon
 * @date 2017/4/28
 */
public class Prototype implements Cloneable {

    private String name;

    public Prototype clone() {
        try {
            return (Prototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
