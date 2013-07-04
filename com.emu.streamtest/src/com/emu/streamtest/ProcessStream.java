package com.emu.streamtest;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class ProcessStream {

	private ProcessStreamThread pst;

	public ProcessStream()
	{
		pst = null;
	}

	public void startProcess(String command) throws IOException 
	{
		pst = new ProcessStreamThread(command);
		pst.start();
	}
	
	public void killProcess() 
	{
		if (pst != null) pst.kill();
		pst = null;
	}
	
	public boolean isRunning() 
	{
		return (pst != null);		
	}
	
	public String readIn() 
	{
		return pst.readInStream();
	}
	
	public void writeOut(String str) throws IOException 
	{
		pst.writeOutStream(str);
	}
	
	class ProcessStreamThread extends Thread {
		
		private Process 		process;
		private BufferedWriter 	out; 	// STDIN
		private BufferedReader 	in; 	// STDOUT
		private Queue <String> 	inq;
		
		public ProcessStreamThread(String command) throws IOException 
		{
			process = Runtime.getRuntime().exec(command);
			in = new BufferedReader(new InputStreamReader(process.getInputStream()));
		    out = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
		    inq = new LinkedList<String>();
		}

		public void run() {
			
			String line;
			
			while(true) {
				try {			
					if (in.ready()) {
						while((line = in.readLine()) != null) {
							inq.add( line.trim() ); // remove string from stream and add to queue
						}
					}
				} catch (IOException e) {
					// ignore
				}
			}
		}
		
		public void kill() {
			try {
				process.getInputStream().close();
				process.getOutputStream().close();
			} catch (IOException e) {
				// to do
			}
			process.destroy();
		}

		public String readInStream() {
			return inq.poll(); // pops head and returns it, returns null if empty
		}
		
		public void writeOutStream(String str) throws IOException {
			out.write(str+"\n");
			out.flush();
		}
		
	}
	
	
}
