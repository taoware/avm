package com.irengine.tdd.functional;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

import com.irengine.tdd.functional.User.SEX;

public class StreamTest {
	
	@Test
	public void testStreamFeatures() {
		List<User> allUsers = new ArrayList<>();
		
		Map<SEX, List<User>> ns = new HashMap<>();
		for (User user : allUsers) {
			if (user.getAge() >= 18) {
				List<User> currentUsers = ns.get(user.getSex());
				if (currentUsers == null) {
					currentUsers = new ArrayList<>();
					ns.put(user.getSex(), currentUsers);
				}
				currentUsers.add(user);
			}
		}
		
		Map<SEX, List<User>> ss = allUsers
				.stream()
				.filter(user -> user.getAge() >= 18)
				.collect(Collectors.groupingBy(User::getSex));

		assertTrue(ns.equals(ss));

		Map<SEX, List<User>> ps = allUsers
				.parallelStream()
				.filter(user -> user.getAge() >= 18)
				.collect(Collectors.groupingBy(User::getSex));

		assertTrue(ns.equals(ps));
	}

}
