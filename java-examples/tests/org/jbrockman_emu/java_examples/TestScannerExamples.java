package org.jbrockman_emu.java_examples;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class TestScannerExamples {
	ScannerExamples uut = new ScannerExamples();

//	@Test
	public void testFish() {
		uut.fish();
	}
	
//	@Test
	public void testMatchFish() {
		uut.matchFish();
	}
	
//	@Test
	public void testMatchThreadletOp() {
		uut.matchThreadletOp();
	}
	
//	@Test
	public void testScanTraceFile() {
		uut.scanTraceFile();
	}
	
	@Test
	public void testScanStdin() {
		uut.scanStdin();
	}

}
