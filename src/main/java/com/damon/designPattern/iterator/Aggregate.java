package com.damon.designPattern.iterator;

/**
 * Aggregate
 *
 * @author damon
 * @date 2017/5/5
 */
public interface Aggregate {

    public void add(Object object);

    public void remove(Object object);

    public Iterator iterator();
}
