package com.damon.designPattern.decorator;

import java.math.BigDecimal;

/**
 * Egg
 *
 * @author damon
 * @date 2017/4/28
 */
public class Egg extends Condiment {

    private Pancake pancake;

    public Egg(Pancake pancake) {
        this.pancake = pancake;
    }

    public String getName() {
        return pancake.getName() + ", add egg";
    }

    public BigDecimal getPrice() {
        return pancake.getPrice().add(new BigDecimal(1.5));
    }
}
