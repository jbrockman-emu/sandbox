package org.jbrockman_emu.java_examples;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestProcessStream {
	
	Display display = new Display ();
	final Shell shell = new Shell (display);
	ProcessStream pstream = new ProcessStream();
	String programFile = "hello_test.exe";
	static final int TIMER_INTERVAL = 10;	

	@Test
	public void testProcessStream() {
		shell.open ();
		startProcess();
		Runnable runnable = new Runnable() {
			public void run() {
				update();	
				display.timerExec(TIMER_INTERVAL, this);
			}
		};
		display.timerExec(TIMER_INTERVAL, runnable);
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose ();
		assertTrue(true);
	}
	
	private void update()
	{
		if (pstream.isRunning() == true) {
			String line = pstream.readIn();

			if (line == null) { // return if no output available
				return;
			}
			
			if (line.trim().equals("--EOF--")){
				stopProcess();
				return;
			}
			System.out.println(line);
		}
	}
	
	private void startProcess()
	{
		stopProcess();
		try {
			pstream.startProcess(programFile);
			System.out.println("Process is running.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void stopProcess()
	{
		System.out.println("Process is not running");
		pstream.killProcess();
	}
	
//	public void setFocus() {
//
//	}
	
	public void dispose() {
		pstream.killProcess(); // required to kill any running process on exit!!
	}

}
