package com.damon.guava.test;

import com.google.common.primitives.Bytes;

import java.util.List;

/**
 * @author damon
 * @version 2017-11-09
 **/

public class BytesTest {

	public static void main(String[] args) {
		byte[] byteArray = {1, 2, 3, 4, 5, 5, 6, 7};
		List<Byte> list = Bytes.asList(byteArray);
		System.out.println(list.toString());

		byte[] byteArray1 = Bytes.toArray(list);
		for (int i = 0; i < byteArray1.length; i++) {
			System.out.print(byteArray1[i] + ",");
		}

		System.out.println();
		byte data = 5;
		System.out.println("5 is in array? " + Bytes.contains(byteArray, data));

		System.out.println("Index of 5: " + Bytes.indexOf(byteArray, data));
		System.out.println("Last index of 5: " + Bytes.lastIndexOf(byteArray, data));
		System.out.println("Last index of 5: " + Bytes.lastIndexOf(byteArray, (byte) -5));

		byte[] byteArray2 = Bytes.concat(byteArray, byteArray1);
		for (int i = 0; i < byteArray2.length; i++) {
			System.out.print(byteArray2[i] + ",");
		}

		System.out.println();
		byte[] byteArray3 = Bytes.ensureCapacity(byteArray2, 15, 5);
		for (int i = 0; i < byteArray3.length; i++) {
			System.out.print(byteArray3[i] + ",");
		}
	}
}
