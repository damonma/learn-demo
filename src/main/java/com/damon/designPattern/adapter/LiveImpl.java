package com.damon.designPattern.adapter;

/**
 * LiveImpl
 *
 * @author damon
 * @date 2017/4/28
 */
public class LiveImpl extends LiveDefault {

    @Override
    public void eat() {
        System.out.println("好吃");
    }

    @Override
    public void sleep() {
        System.out.println("好困");
    }
}
