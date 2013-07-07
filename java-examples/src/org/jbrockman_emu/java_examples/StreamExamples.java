package org.jbrockman_emu.java_examples;

import java.io.*;

public class StreamExamples {

	public void hello() {
		System.out.println("hello");
	}

	public void bufferedWriterExample() {
		String text = "Hello, again";
		try {
			File file = new File("example.txt");
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			output.write(text);
			output.close();
		} 
		catch ( IOException e ) {
			e.printStackTrace();
		}
	}
	
	public void printWriterExample() {
		try {
			PrintWriter writer = new PrintWriter("example.txt", "UTF-8");
			writer.println("The first line");
			writer.println("The second line");
			writer.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}
