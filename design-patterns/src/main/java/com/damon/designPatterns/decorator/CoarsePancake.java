package com.damon.designPatterns.decorator;

import java.math.BigDecimal;

/**
 * CoarsePancake
 *
 * @author damon
 * @date 2017/4/28
 */
public class CoarsePancake extends Pancake {

    public CoarsePancake() {
        this.name = "CoarsePancake";
    }

    public BigDecimal getPrice() {
        return new BigDecimal(5);
    }
}
