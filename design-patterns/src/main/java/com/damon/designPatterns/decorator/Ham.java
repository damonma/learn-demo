package com.damon.designPatterns.decorator;

import java.math.BigDecimal;

/**
 * Ham
 *
 * @author damon
 * @date 2017/4/28
 */
public class Ham extends Condiment {

    private Pancake pancake;

    public Ham(Pancake pancake) {
        this.pancake = pancake;
    }

    public String getName() {
        return pancake.getName() + ", add ham";
    }

    public BigDecimal getPrice() {
        return pancake.getPrice().add(new BigDecimal(2));
    }
}
