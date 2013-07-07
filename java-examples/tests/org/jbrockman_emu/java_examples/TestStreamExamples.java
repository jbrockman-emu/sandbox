package org.jbrockman_emu.java_examples;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestStreamExamples {

//	@Test
	public void bufferedWriterExampleTest() {
		StreamExamples uut = new StreamExamples();
		uut.bufferedWriterExample();
		assertTrue(true);
	}
	
	@Test
	public void printWriterExampleTest() {
		StreamExamples uut = new StreamExamples();
		uut.printWriterExample();
		assertTrue(true);
	}

}
