package org.jbrockman_emu.java_examples;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.NoSuchElementException;

public class TestCircularBuffer {
	CircularBuffer b;
	
	void setup() {
		b = new CircularBuffer();
		b.add("A");
		b.add("B");
		b.add("C");
		b.add("D");
		b.add("E");
		print("setup");
	}
	
	void setupNull() {
		b = new CircularBuffer();
		print("setupNull");
	}
	
	void print(String s) {
		System.out.println(b + " " + s);
	}

//	@Test
	public void testNext() {
		System.out.println("---- testNext ----");
		setup();
		for (int i = 0;  i < 8;  i++) {
			System.out.println(b.next());
			print("next");
		}
		setupNull();
		try {
			b.next();
		} catch (NoSuchElementException e) {
			System.err.println("Caught NoSuchElementException: " + e.getMessage());
		} finally {
			print("next tried on empty buffer");
		}
	}
	
	@Test
	public void testPrevious() {
		System.out.println("---- testPrevious ----");
		setup();
		for (int i = 0;  i < 8;  i++) {
			System.out.println(b.previous());
			print("previous");
		}
		for (int i = 0;  i < 8;  i++) {
			System.out.println(b.next());
			print("next");
		}
	}

//	@Test
	public void testRemove() {
		System.out.println("---- testRemove ----");
		setup();
		b.remove();
		print("remove A");
		b.remove();
		print("remove B");
		b.next();
		b.remove();
		print("remove D");
		b.remove();
		print("remove E");
		assertFalse(b.empty());
		b.remove();
		print("remove C");
		assertTrue(b.empty());
	}
	
//	@Test
	public void testRemoveSingleton() {
		System.out.println("---- testRemoveSingleton ----");
		b = new CircularBuffer();
		b.add("A");
		print("singleton");
		b.next();
		print("next");
		b.remove();
		print("remove A");
	}

//	@Test
	public void testIterator() {
		System.out.println("---- testIterator ----");
		setup();
		for (String s : b) {
			System.out.print(s + " ");
		}
		System.out.println();
	}
	
//	@Test
	public void testAdd() {
		System.out.println("---- testAdd ----");
		setup();
		b.add("X");
		print("add X at penultimate");
		b.next();
		b.add("Y");
		print("add Y at head");
	}

}
