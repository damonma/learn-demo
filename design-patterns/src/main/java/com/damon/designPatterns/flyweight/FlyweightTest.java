package com.damon.designPatterns.flyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * FlyweightTest
 *
 * @author damon
 * @date 2017/5/4
 */
public class FlyweightTest {

    public static void main(String[] args) {
//        FlyweightFactory factory = new FlyweightFactory();
//        Flyweight f1 = factory.factory("a");
//        Flyweight f2 = factory.factory("b");
//        Flyweight f3 = factory.factory("a");
//
//        f1.operation("f1");
//        f2.operation("f2");
//        f3.operation("f3");
//
//        System.out.println(f1 == f3);
//        System.out.println(f1 == f2);
//        System.out.println(factory.getFlyweightSize());
        List<String> list = new ArrayList<String>(3);
        list.add("a");
        list.add("b");
        list.add("a");
        FlyweightCompositeFactory compositeFactory = new FlyweightCompositeFactory();
        Flyweight f1 = compositeFactory.factory(list);
        Flyweight f2 = compositeFactory.factory(list);
        f1.operation("f1");
        System.out.println(f1 == f2);

        Flyweight f3 = compositeFactory.factory("c");
        Flyweight f4 = compositeFactory.factory("c");
        System.out.println(f3 == f4);
    }

}
