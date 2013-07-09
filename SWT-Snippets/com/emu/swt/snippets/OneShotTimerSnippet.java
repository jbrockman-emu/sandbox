package com.emu.swt.snippets;

import org.eclipse.swt.widgets.*;

public class OneShotTimerSnippet {

	public static void main(String[] args) {
		final Display display = new Display ();
		Shell shell = new Shell (display);
		shell.open ();
		
		// begin body
		System.out.println("Begin");
		display.timerExec(2000, new Runnable() {
		    public void run() {
		        System.out.println("Once, after 2 seconds.");
		    }
		});
		// end body
		
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}

}
