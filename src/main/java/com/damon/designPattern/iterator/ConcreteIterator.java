package com.damon.designPattern.iterator;

import java.util.Collections;
import java.util.List;

/**
 * ConcreteIterator
 *
 * @author damon
 * @date 2017/5/5
 */
public class ConcreteIterator implements Iterator {

    private List<Object> list = Collections.emptyList();

    private int current = 0;

    public ConcreteIterator(List<Object> list) {
         this.list = list;
    }

    public Object next() {
        Object object = null;
        if (this.hasNext()) {
            object = list.get(current++);
        }

        return object;
    }

    public boolean hasNext() {
        if (current == list.size()) {
            return false;
        }

        return true;
    }
}
