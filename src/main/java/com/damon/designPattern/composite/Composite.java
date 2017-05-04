package com.damon.designPattern.composite;

import java.util.LinkedList;

/**
 * Composite
 *
 * @author damon
 * @date 2017/4/28
 */
public class Composite extends Component {

    private LinkedList<Component> children;

    public Composite(String name) {
        super(name);
        children = new LinkedList<Component>();
    }

    @Override
    public void add(Component component) {
        children.add(component);
    }

    @Override
    public void remove(Component component) {
        children.remove(component);
    }

    @Override
    public void operation(int depth) {
        String temp = "";
        for(int i = 0; i < depth; i++) {
            temp += "    ";
        }

        LinkedList<Component> children = this.getChildren();
        System.out.println(temp + this.name);

        for (Component component : children) {
            component.operation(depth + 1);
        }
    }

    @Override
    public LinkedList<Component> getChildren() {
        return children;
    }
}
