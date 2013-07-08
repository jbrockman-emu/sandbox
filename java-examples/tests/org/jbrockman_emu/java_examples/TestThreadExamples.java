package org.jbrockman_emu.java_examples;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestThreadExamples {
	
	ThreadExamples uut = new ThreadExamples();

	@Test
	public void testStartHelloRunnable() {
		uut.startHelloRunnable();
		assertTrue(true);
	}
	
	@Test
	public void testStartHelloThread() {
		uut.startHelloThread();
		assertTrue(true);
	}

}
