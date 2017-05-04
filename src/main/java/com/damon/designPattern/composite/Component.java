package com.damon.designPattern.composite;

import java.util.List;

/**
 * Component
 *
 * @author damon
 * @date 2017/4/28
 */
public abstract class Component {

    protected String name;

    public Component(String name) {
        this.name = name;
    }

    protected abstract void add(Component component);

    protected abstract void remove(Component component);

    protected abstract void operation(int depth);

    protected abstract List<Component> getChildren();
}
