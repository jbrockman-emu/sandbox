package com.emu.swt.snippets;

import org.eclipse.swt.widgets.*;

public class RepeatingTimerSnippet {

	public static void main(String[] args) {
		final Display display = new Display ();
		Shell shell = new Shell (display);
		shell.open ();
		
		/*
		 * Timers run only once. To run a timer repeatedly, queue 
		 * the same runnable again, using the same time interval. 
		 * This code fragment uses a timer to execute 
		 * a runnable every 2 seconds.
		 */
		System.out.println("Begin");
		display.timerExec(2000, new Runnable() {
		    public void run() {
		        System.out.println("Every 2 seconds.");
		        display.timerExec(2000, this);
		    }
		});
		
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}

}
