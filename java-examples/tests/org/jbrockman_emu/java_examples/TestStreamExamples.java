package org.jbrockman_emu.java_examples;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class TestStreamExamples {

private StreamExamples uut = new StreamExamples();

//	@Test
	public void bufferedWriterExampleTest() {
		uut.bufferedWriterExample();
		assertTrue(true);
	}
	
//	@Test
	public void printWriterExampleTest() {
		uut.printWriterExample();
		assertTrue(true);
	}
	
//	@Test
	public void copyBytesTest() {
		try {
			uut.copyBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(true);
	}
	
//	@Test
	public void copyCharactersTest() {
		try {
			uut.copyCharacters();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(true);
	}

//	@Test
	public void copyLinesTest() {
		try {
			uut.copyLines();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(true);
	}
	
//	@Test
	public void scanSumTest() {
		try {
			uut.scanSum();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(true);
	}
	
//	@Test
	public void dataStreamsTest() {
		try {
			uut.dataStreams();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		assertTrue(true);
	}
	
	@Test
	public void objectStreamsTest() {
		try {
			uut.objectStreams();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		assertTrue(true);
	}

}
