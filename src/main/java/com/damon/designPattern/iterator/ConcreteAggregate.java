package com.damon.designPattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * ConcreteAggregate
 *
 * @author damon
 * @date 2017/5/5
 */
public class ConcreteAggregate implements Aggregate {

    private List<Object> list = new ArrayList<Object>();

    public void add(Object object) {
        list.add(object);
    }

    public void remove(Object object) {
        list.remove(object);
    }

    public Iterator iterator() {
        return new ConcreteIterator(list);
    }
}
