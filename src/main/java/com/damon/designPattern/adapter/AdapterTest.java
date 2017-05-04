package com.damon.designPattern.adapter;

/**
 * AdapterTest
 *
 * @author damon
 * @date 2017/4/28
 */
public class AdapterTest {

    public static void main(String[] args) {
        Target target = new Adapter();
        target.request();

        Target objectTarget = new ObjectAdapter(new Orignal());
        objectTarget.request();

        Live live = new LiveImpl();
        live.eat();
        live.sleep();
    }
}
