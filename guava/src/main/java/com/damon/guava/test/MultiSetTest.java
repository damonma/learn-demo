package com.damon.guava.test;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Iterator;
import java.util.Set;

/**
 * @author damon
 * @version 2017-10-31
 **/

public class MultiSetTest {

	public static void main(String[] args) {
		Multiset<String> multiset = HashMultiset.create();
		multiset.add("a");
		multiset.add("b");
		multiset.add("c");
		multiset.add("d");
		multiset.add("a");
		multiset.add("b");
		multiset.add("c");
		multiset.add("b");
		multiset.add("b");
		multiset.add("b");

		System.out.println("count of b : " + multiset.count("b"));
		System.out.println("size of set : " + multiset.size());

		System.out.println("elementSet start : ");
		Set<String> set = multiset.elementSet();
		for (String s : set) {
			System.out.println(s);
		}

		System.out.println("iterator start : ");
		Iterator<String> iterator = multiset.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		System.out.println("entrySet start : ");
		for (Multiset.Entry<String> entry : multiset.entrySet()) {
			System.out.println(entry.getElement() + "  " + entry.getCount());
		}

		multiset.remove("b");
		System.out.println("count of b : " + multiset.count("b"));

		multiset.remove("b", 2);
		System.out.println("count of b : " + multiset.count("b"));
	}
}
