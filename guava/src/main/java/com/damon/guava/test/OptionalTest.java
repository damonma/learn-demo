package com.damon.guava.test;

import com.google.common.base.Optional;

/**
 * @author damon
 * @version 2017-10-17
 **/

public class OptionalTest {

	public static void main(String[] args) {
		OptionalTest ot = new OptionalTest();
		Integer val1 = null;
		Integer val2 = new Integer(10);
		Optional<Integer> a = Optional.fromNullable(val1);
		Optional<Integer> b = Optional.of(val2);

		System.out.println(ot.sum(a, b));
	}

	public Integer sum(Optional<Integer> a, Optional<Integer> b) {
		System.out.println("First parameter is " + a.isPresent());
		System.out.println("Second parameter is " + b.isPresent());
		Integer val1 = a.or(new Integer(0));
//		Integer val1 = a.orNull();
		Integer val2 = b.get();

		return val1 + val2;
	}
}
