package com.irengine.tdd.functional;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.*;
import org.junit.Test;

import com.irengine.tdd.functional.User.SEX;

public class MainTest {

	/*
	 * method reference
	 */
	@Test
	public void testMethodReference() {
		File[] ns = new File(".").listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});
				
		File[] ms = new File(".").listFiles(File::isDirectory);

		assertArrayEquals(ns, ms);
	}
	
	/*
	 * lambda
	 */
	@Test
	public void testLambda() {
		File[] ns = new File(".").listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.getAbsolutePath().endsWith("csv");
			}
		});
		
		File[] ls = new File(".") .listFiles(pathname -> pathname.getAbsolutePath().endsWith("csv"));
		
		assertArrayEquals(ns, ls);
	}
	
	/*
	 * predicate
	 * imperative
	 */
	public List<User> adultsI(List<User> allUsers) {
		List<User> adultUsers = new ArrayList<>();
		for (User user : allUsers) {
			if (user.getAge() >= 18) {
				adultUsers.add(user);
			}
		}
		return adultUsers;
	}
	
	public List<User> malesI(List<User> allUsers) {
		List<User> maleUsers = new ArrayList<>();
		for (User user : allUsers) {
			if (SEX.MALE.equals(user.getSex())) {
				maleUsers.add(user);
			}
		}
		return maleUsers;
	}

	/*
	 * functional
	 */
	public List<User> filterUsers(List<User> allUsers, Predicate<User> predicate) {
		List<User> result = new ArrayList<>();
		for (User user : allUsers) {
			if (predicate.test(user)) {
				result.add(user);
			}
		}
		return result;
	}
	
	public List<User> adultsF(List<User> allUsers) {
		return filterUsers(allUsers, user -> user.getAge() >= 18);
	}

	public List<User> malesF(List<User> allUsers) {
		return filterUsers(allUsers, User::isMale);
	}
	
	@Test
	public void testPredicate() {
		
	}
}
