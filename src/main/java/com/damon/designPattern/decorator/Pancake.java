package com.damon.designPattern.decorator;

import java.math.BigDecimal;

/**
 * Pancake
 *
 * @author damon
 * @date 2017/4/28
 */
public abstract class Pancake {

    protected String name;

    public String getName() {
        return name;
    }

    public abstract BigDecimal getPrice();
}
