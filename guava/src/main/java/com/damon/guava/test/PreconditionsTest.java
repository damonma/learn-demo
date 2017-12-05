package com.damon.guava.test;

import com.google.common.base.Preconditions;

/**
 * @author damon
 * @version 2017-10-17
 **/

public class PreconditionsTest {

	public static void main(String[] args) {
		PreconditionsTest pt = new PreconditionsTest();
		try {
			System.out.println(pt.sqrt(-3.0));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println(pt.sum(null, 3));
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println(pt.getValue(6));
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
	}

	public double sqrt(double d) throws IllegalArgumentException {
		Preconditions.checkArgument(d > 0.0, "Negative value %s", d);
		return Math.sqrt(d);
	}

	public int sum(Integer a, Integer b) {
		a = Preconditions.checkNotNull(a, "First parameter is null");
		b = Preconditions.checkNotNull(b, "Second parameter is null");
		return a + b;
	}

	public int getValue(int i) {
		int[] data = {1, 2, 3, 4, 5};
		Preconditions.checkElementIndex(i, data.length, "Invalid index");
		return 0;
	}
}
