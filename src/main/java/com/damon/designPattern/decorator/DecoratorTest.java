package com.damon.designPattern.decorator;

/**
 * DecoratorTest
 *
 * @author damon
 * @date 2017/4/28
 */
public class DecoratorTest {

    public static void main(String[] args) {
        Pancake pancake = new CoarsePancake();
        Condiment egg = new Egg(pancake);
        egg.sold();
        Condiment ham = new Ham(egg);
        ham.sold();
        Condiment lettuce = new Lettuce(ham);
        lettuce.sold();
    }
}
