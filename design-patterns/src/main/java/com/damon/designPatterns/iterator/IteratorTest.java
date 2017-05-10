package com.damon.designPatterns.iterator;

/**
 * IteratorTest
 *
 * @author damon
 * @date 2017/5/5
 */
public class IteratorTest {

    public static void main(String[] args) {
        Aggregate aggregate = new ConcreteAggregate();
        aggregate.add("zzh");
        aggregate.add("jj");
        aggregate.add("qq");
        Iterator iterator = aggregate.iterator();
        while(iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }
}
