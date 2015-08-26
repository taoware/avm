package com.irengine.tdd.functional;

public class User {
	public enum SEX {
		MALE, FEMALE
	}
	private final int age;
	private final String name;
	private final SEX sex;

	public User(int age, String name, SEX sex) {
		this.age = age;
		this.name = name;
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public SEX getSex() {
		return sex;
	}
	
	public String getName() {
		return name;
	}

	public boolean isMale() {
		return sex.equals(SEX.MALE);
	}
}
