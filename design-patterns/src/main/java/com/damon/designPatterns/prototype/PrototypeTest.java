package com.damon.designPatterns.prototype;

/**
 * PrototypeTest
 *
 * @author damon
 * @date 2017/4/28
 */
public class PrototypeTest {

    public static void main(String[] args) {
        Prototype prototype1 = new Prototype();
        prototype1.setName("Prototype1");
        Prototype prototype2 = prototype1.clone();
        System.out.println(prototype2.getName());

        System.out.println(prototype1 == prototype2);

        System.out.println(prototype1.getClass() == prototype2.getClass());
        prototype2.setName("Prototype2");
        System.out.println(prototype2.getName());
    }
}
