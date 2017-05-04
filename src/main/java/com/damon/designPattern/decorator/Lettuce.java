package com.damon.designPattern.decorator;

import java.math.BigDecimal;

/**
 * Lettuce
 *
 * @author damon
 * @date 2017/4/28
 */
public class Lettuce extends Condiment {

    private Pancake pancake;

    public Lettuce(Pancake pancake) {
        this.pancake = pancake;
    }

    public String getName() {
        return pancake.getName() + ", add lettuce";
    }

    public BigDecimal getPrice() {
        return pancake.getPrice().add(new BigDecimal(1));
    }
}
