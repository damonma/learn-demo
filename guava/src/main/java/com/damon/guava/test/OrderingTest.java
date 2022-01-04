package com.damon.guava.test;

import com.google.common.base.CaseFormat;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.*;

/**
 * @author damon
 * @version 2017-10-18
 **/

public class OrderingTest {

	public static void main(String[] args) {
		OrderingTest ot = new OrderingTest();
//		ot.sortPerson();
//		List<Integer> numbers = new ArrayList<Integer>();
//		numbers.add(Integer.valueOf(5));
//		numbers.add(Integer.valueOf(3));
//		numbers.add(Integer.valueOf(8));
//		numbers.add(Integer.valueOf(5));
//		numbers.add(Integer.valueOf(6));
//		numbers.add(Integer.valueOf(7));
//
//		Ordering ordering = Ordering.natural();
//		Collections.sort(numbers, ordering);
//		System.out.println(numbers);
//		System.out.println(ordering.isOrdered(numbers));
//		System.out.println(ordering.max(numbers));
//		System.out.println(ordering.min(numbers));
//		Collections.sort(numbers,ordering.reverse());
//		System.out.println(numbers);
//		numbers.add(null);
//		System.out.println(numbers);
//		Collections.sort(numbers, ordering.nullsFirst());
//		System.out.println(numbers);
//
//		List<String> strings = new ArrayList<String>();
//		strings.add("Java");
//		strings.add("Python");
//		strings.add("Php");
//		strings.add("C++");
//		strings.add("Go");
//		strings.add("Scala");
//		strings.add(null);
//		Collections.sort(strings, Ordering.usingToString().nullsFirst().reverse());
//		System.out.println(strings);

//		String test = "TEST_FIELD" + "_count";
//		System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, test));

//		Map<Integer, String> map = new LinkedHashMap();
//		map.put(111, "aaa");
//		map.put(333, "ccc");
//		map.put(444, "ddd");
//		map.put(222, "bbb");
//		for (Map.Entry<Integer, String> entry : map.entrySet()) {
//			System.out.println(entry.getKey() + "  " + entry.getValue());
//		}
//
//		List<String> list = Lists.newLinkedList(map.values());
//		for (String string : list) {
//			System.out.println(string);
//		}
//
//		for (TestEnum testEnum : TestEnum.values()) {
//			System.out.println(testEnum.name() + "  " + testEnum.getValue() + "  " + testEnum.ordinal());
//		}

		String test = "hello_World";
		System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, test));
	}

	private void sortPerson() {
		Ordering ordering = Ordering.natural();
		List<Person> personList = new ArrayList<Person>();
		Person p1 = new Person();
		p1.setId(111L);
		p1.setName("zzz");
		p1.setAge(25);
		personList.add(p1);

		Person p2 = new Person();
		p2.setId(222L);
		p2.setName("bbb");
		p2.setAge(23);
		personList.add(p2);

		Person p3 = new Person();
		p3.setId(333L);
		p3.setAge(27);
		personList.add(p3);

		Person p4 = new Person();
		p4.setId(444L);
		p4.setName("bbb");
//		p4.setAge(25);
		personList.add(p4);

		for (Person person : personList) {
			System.out.println(person.toString());
		}

		Ordering other = ordering.nullsFirst().reverse().onResultOf(Person.nameFunction());
		Collections.sort(personList, other);
		for (Person person : personList) {
			System.out.println(person.toString());
		}
	}
}

enum TestEnum {
	C(1),
	A(2),
	D(3),
	B(4);

	private int value;

	TestEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
