package com.irengine.tdd.mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class MockitoFeatureTest {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testVerifyBehavior() {

		// mock creation
		List mockedList = mock(List.class);

		// using mock object
		mockedList.add("one");
		mockedList.clear();

		// verification
		verify(mockedList).add("one");
		verify(mockedList).clear();
		
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testStubbingWhenSetObjectShouldReturnObject() {
		
		//You can mock concrete classes, not only interfaces
		LinkedList mockedList = mock(LinkedList.class);

		//stubbing
		when(mockedList.get(0)).thenReturn("first");

		//following prints "first"
		assertEquals("first", mockedList.get(0));
		 
	}
	
	@SuppressWarnings("rawtypes")
	@Test(expected = RuntimeException.class)
	public void testStubbingWhenSetExceptionShouldThrowException() {
		
		//You can mock concrete classes, not only interfaces
		LinkedList mockedList = mock(LinkedList.class);

		//stubbing
		when(mockedList.get(0)).thenThrow(new RuntimeException());
		
		System.out.println(mockedList.get(0));
		
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testStubbingWhenNotSetShouldReturnNull() {
		
		//You can mock concrete classes, not only interfaces
		LinkedList mockedList = mock(LinkedList.class);

		//stubbing
		when(mockedList.get(0)).thenReturn("first");
		when(mockedList.get(1)).thenThrow(new RuntimeException());

		//following prints "null" because get(999) was not stubbed
		assertNull(mockedList.get(999));
		 
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testBuiltInMatcher() {

		//You can mock concrete classes, not only interfaces
		LinkedList mockedList = mock(LinkedList.class);

		// stubbing using built-in anyInt() argument matcher
		when(mockedList.get(anyInt())).thenReturn("element");

		// following prints "element"
		assertEquals("element", mockedList.get(999));

		// you can also verify using an argument matcher
		verify(mockedList).get(anyInt());
		
	}
	
	@Test
	public void testCustomMatcher() {
		
		// TODO: not implement
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testVerifyNumberOfInvocations() {
		
		//You can mock concrete classes, not only interfaces
		LinkedList mockedList = mock(LinkedList.class);
		
		// using mock
		mockedList.add("once");

		mockedList.add("twice");
		mockedList.add("twice");

		mockedList.add("three times");
		mockedList.add("three times");
		mockedList.add("three times");

		// following two verifications work exactly the same - times(1) is used
		// by default
		verify(mockedList).add("once");
		verify(mockedList, times(1)).add("once");

		// exact number of invocations verification
		verify(mockedList, times(2)).add("twice");
		verify(mockedList, times(3)).add("three times");

		// verification using never(). never() is an alias to times(0)
		verify(mockedList, never()).add("never happened");

		// verification using atLeast()/atMost()
		verify(mockedList, atLeastOnce()).add("three times");
		verify(mockedList, atLeast(2)).add("three times");
		verify(mockedList, atMost(5)).add("three times");
		 
	}
	
	@Test
	public void testMockAnnotation() {
		
		// TODO: not implement
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testSpy() {
		
		List list = new LinkedList();
		List spy = spy(list);

		// optionally, you can stub out some methods:
		when(spy.size()).thenReturn(100);

		// using the spy calls *real* methods
		spy.add("one");
		spy.add("two");

		// prints "one" - the first element of a list
		assertEquals("one", spy.get(0));

		// size() method was stubbed - 100 is printed
		assertEquals(100, spy.size());

		// optionally, you can verify
		verify(spy).add("one");
		verify(spy).add("two");
		   
	}
	
}
