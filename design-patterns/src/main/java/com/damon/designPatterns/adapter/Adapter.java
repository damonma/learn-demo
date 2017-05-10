package com.damon.designPatterns.adapter;

/**
 * Adapter
 * 类适配器
 *
 * @author damon
 * @date 2017/4/28
 */
public class Adapter extends Orignal implements Target {

    public void request() {
        super.specificRequest();
    }
}
