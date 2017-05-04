package com.damon.designPattern.composite;

/**
 * CompositeTest
 *
 * @author damon
 * @date 2017/4/28
 */
public class CompositeTest {

    public static void main(String[] args) {
        Composite root = new Composite("root");

        Composite branch1 = new Composite("branch1");
        Composite branch2 = new Composite("branch2");

        branch1.add(new Leaf("leaf1"));
        branch1.add(new Leaf("leaf2"));

        branch2.add(new Leaf("leaf1"));
        branch2.add(new Leaf("leaf2"));

        root.add(branch1);
        root.add(branch2);

        root.operation(0);
    }
}
