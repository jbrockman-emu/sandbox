package org.jbrockman_emu.java_examples;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class TestProcessStream {
	
	ProcessStream uut = new ProcessStream();

	@Test
	public void testProcessStream() {
		String outString;
		
		try {
			uut.startProcess("pwd");
			outString = uut.readIn();
			System.out.println(outString);
//			uut.killProcess();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(true);
	}

	@Test
	public void testStartProcess() {
		fail("Not yet implemented");
	}

	@Test
	public void testKillProcess() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsRunning() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadIn() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteOut() {
		fail("Not yet implemented");
	}

}
