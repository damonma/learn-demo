package com.damon.designPattern.composite;

import java.util.List;

/**
 * Leaf
 *
 * @author damon
 * @date 2017/4/28
 */
public class Leaf extends Component {

    public Leaf(String name) {
        super(name);
    }

    @Deprecated
    public void add(Component component) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void remove(Component component) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void operation(int depth) {
        String temp = "";
        for (int i = 0; i < depth; i++) {
            temp += "    ";
        }

        System.out.println(temp + this.name);
    }

    @Deprecated
    public List<Component> getChildren() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
