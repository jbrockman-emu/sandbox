package org.jbrockman_emu.java_examples;

public class ThreadExamples {
	
	// Implement Runnable interface
	public class HelloRunnable implements Runnable {

	    public void run() {
	        System.out.println("Hello from a HelloRunnable!");
	    }
	}
	public void startHelloRunnable() {
		new Thread(new HelloRunnable()).start();
	}
	
	// Extend Thread class
	public class HelloThread extends Thread {
		public void run() {
			System.out.println("Hello from a HelloThread!");
		}
	}
	public void startHelloThread() {
		new HelloThread().start();
	}
	
}
