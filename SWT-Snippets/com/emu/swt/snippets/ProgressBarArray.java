package com.emu.swt.snippets;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class ProgressBarArray {
	
	public static void main (String [] args) {
		Display display = new Display ();
		final Shell shell = new Shell (display);
		shell.setLayout(new FillLayout());
		final Composite c = new Composite(shell, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		c.setLayout(layout);
		final ProgressBar[] barArray = new ProgressBar[10];
		for (int i = 0; i < 10; i++) {
			Label label = new Label(c, SWT.LEFT);
			label.setText(i+": ");
			barArray[i] = new ProgressBar(c, SWT.SMOOTH);
			barArray[i].setMinimum(0);
			barArray[i].setMaximum(100);
//			barArray[i].setState(SWT.PAUSED);
			barArray[i].setSelection(5*i);
		}
		shell.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch())
				display.sleep ();
		}
		display.dispose ();
	}
}
