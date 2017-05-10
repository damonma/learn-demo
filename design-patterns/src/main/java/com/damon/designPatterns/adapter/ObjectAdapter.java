package com.damon.designPatterns.adapter;

/**
 * ObjectAdapter
 *
 * @author damon
 * @date 2017/4/28
 */
public class ObjectAdapter implements Target {

    private Orignal orignal;

    public ObjectAdapter(Orignal orignal) {
        this.orignal = orignal;
    }

    public void request() {
        orignal.specificRequest();
    }
}
