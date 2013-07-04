package com.emu.streamtest.views;


import java.io.IOException;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.SWT;

import com.emu.streamtest.*;


public class ProcessStreamTest extends ViewPart {


	private static final int TIMER_INTERVAL = 10;

	
	private ProcessStream pstream;
	
	private String programFile;
	
	private Text programText;
	private Text inText; // from pstream
	private Text outText; // to pstream
	
	private Label runLabel;


	public ProcessStreamTest() {
		pstream = new ProcessStream();
	}

	public void createPartControl(final Composite parent) {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		parent.setLayout(gridLayout);
		
		Button fileButton = new Button(parent, SWT.PUSH);
		fileButton.setText("Open program");
		GridData gridData = new GridData(GridData.FILL, GridData.BEGINNING, false, false);
		fileButton.setLayoutData(gridData);
		fileButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
		        FileDialog fd = new FileDialog(parent.getShell(), SWT.OPEN);
		        fd.setText("Open");
		        fd.setFilterPath("/home/");
		   //     String[] filterExt = { "" };
		   //     fd.setFilterExtensions(filterExt);
		        programFile = fd.open();
		        programText.setText(programFile);
		        startProcess();
			}
		});
		
		programFile = "";
		programText = new Text(parent, SWT.SINGLE | SWT.BORDER);
		gridData = new GridData(GridData.FILL, GridData.BEGINNING, true, false);
		programText.setLayoutData(gridData);
		programText.setEditable(false);
		programText.setText(programFile);
		
		runLabel = new Label(parent,SWT.NONE);
		gridData = new GridData(GridData.FILL, GridData.BEGINNING, false, false);
		runLabel.setLayoutData(gridData);
		runLabel.setText("Process is not running.");
		
		inText = new Text(parent, SWT.SINGLE | SWT.BORDER);
		gridData = new GridData(GridData.FILL, GridData.BEGINNING, true, false);
		gridData.horizontalSpan = 2;
		inText.setEditable(false);
		inText.setLayoutData(gridData);
		
		Button sendButton = new Button(parent, SWT.PUSH);
		sendButton.setText("Send");
		gridData = new GridData(GridData.FILL, GridData.BEGINNING, false, false);
		sendButton.setLayoutData(gridData);
		sendButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {

					try {
						pstream.writeOut(outText.getText());
					} catch (Exception e) {
					    MessageBox messageDialog = new MessageBox(parent.getShell(), SWT.ERROR);
					    messageDialog.setText("");
					    messageDialog.setMessage("An error occured when writing to the output stream.");
					    messageDialog.open();
					}
			}
		});
		
		outText = new Text(parent, SWT.SINGLE | SWT.BORDER);
		gridData = new GridData(GridData.FILL, GridData.BEGINNING, true, false);
		outText.setLayoutData(gridData);
		outText.setEditable(true);
		
		
		Runnable runnable = new Runnable() {
			public void run() {
				update();				
				parent.getShell().getDisplay().timerExec(TIMER_INTERVAL, this);
			}
		};
		
		parent.getShell().getDisplay().timerExec(TIMER_INTERVAL, runnable);

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
			
			inText.setText(line);

		}
	}
	
	private void startProcess()
	{
		stopProcess();
		try {
			pstream.startProcess(programFile);
			runLabel.setText("Process is running.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void stopProcess()
	{
		runLabel.setText("Process is not running.");
		pstream.killProcess();
	}
	
	public void setFocus() {

	}
	
	public void dispose() {
		pstream.killProcess(); // required to kill any running process on exit!!
	}
}