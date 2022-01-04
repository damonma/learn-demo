package com.damon.guava.test;

import com.google.common.base.Function;

/**
 * @author damon
 * @version 2017-11-15
 **/

public class Person {

	private Long id;
	private String name;
	private Integer age;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Persion [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	public static Function<Person, Comparable> nameFunction() {
		return new Function<Person, Comparable>() {
			@Override
			public Comparable apply(Person person) {
				return person.getAge();
			}
		};
	}
}
