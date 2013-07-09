package com.emu.swt.snippets;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class RepeatingTimerSnippet2 {

	static Display display = new Display ();
	
	static class RepeatingTimer implements Runnable {
		public void run() {
			System.out.println("Every 2 seconds.");
	        display.timerExec(2000, this);
		}
	}
	
	static RepeatingTimer repeatingTimer = new RepeatingTimer();
	
	public static void main(String[] args) {
		Shell shell = new Shell (display);
		shell.open();
		System.out.println("Begin");
		display.timerExec(2000, repeatingTimer);
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}

}
