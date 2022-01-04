package com.damon.guava.test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.*;

/**
 * @author damon
 * @version 2017-10-31
 **/

public class MultiMapTest {

	public static void main(String[] args) {
		Multimap<String,String> multimap = ArrayListMultimap.create();

		multimap.put("lower", "a");
		multimap.put("lower", "b");
		multimap.put("lower", "c");
		multimap.put("lower", "d");
		multimap.put("lower", "e");

		multimap.put("upper", "A");
		multimap.put("upper", "B");
		multimap.put("upper", "C");
		multimap.put("upper", "D");
		List<String> lower = (List<String>) multimap.get("lower");
		System.out.println(lower.toString());

		lower.add("f");
		System.out.println(lower.toString());

		List<String> upper = (List<String>) multimap.get("upper");
		System.out.println(upper.toString());
		upper.remove("D");
		System.out.println(upper.toString());

		Map<String, Collection<String>> map = multimap.asMap();
		for (Map.Entry<String, Collection<String>> entry : map.entrySet()) {
			String key = entry.getKey();
			Collection<String> collection = entry.getValue();
			System.out.println(key + " " + collection);
		}

		for (Map.Entry<String, String> entry : multimap.entries()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}

		Set<String> keySet = multimap.keySet();
		for (String key : keySet) {
			System.out.println(key);
		}

		multimap.remove("upper", "A");

		Collection<String> collection = multimap.values();
		System.out.println(collection);
	}

}
