package org.jbrockman_emu.java_examples;

import java.io.*;
import java.util.*;
import java.math.BigDecimal;
import java.util.Calendar;

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
	
	public void copyBytes() throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream("xanadu.txt");
			out = new FileOutputStream("outagain.txt");
			int c;
			
			while ((c = in.read()) != -1) {
				out.write(c);
			}
		}
		finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}
	
	public void copyCharacters() throws IOException {
		FileReader inputStream = null;
		FileWriter outputStream = null;
		
		try {
			inputStream = new FileReader("xanadu.txt");
			outputStream = new FileWriter("characteroutput.txt");
			
			int c;
			while ((c = inputStream.read()) != -1) {
				outputStream.write(c);
			}
		}
		finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}
	
	public void copyLines() throws IOException {
		BufferedReader inputStream = null;
		PrintWriter outputStream = null;
		
		try {
			inputStream = new BufferedReader(new FileReader("xanadu.txt"));
			outputStream = new PrintWriter(new FileWriter("characteroutput.txt"));
			
			String line;
			while ((line = inputStream.readLine()) != null) {
				outputStream.println(line);
			}
		}
		finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
		
	}
	
	public void scanSum() throws IOException {
		Scanner s = null;
		double sum = 0;
		
		try {
			s = new Scanner(new BufferedReader(new FileReader("usnumbers.txt")));
			s.useLocale(Locale.US);
			
			while (s.hasNext()) {
				if (s.hasNextDouble()) {
					sum += s.nextDouble();
				}
				else {
					s.next();
				}
			}
		}
		finally {
			s.close();
		}
		
		System.out.println(sum);
	}
	
	public void dataStreams() throws IOException {
		DataOutputStream out = null;
		final String dataFile = "invoicedata";
		final double[] prices = { 19.99, 9.99, 15.99, 3.99, 4.99 };
		final int[] units = { 12, 8, 13, 29, 50 };
		final String[] descs = { "Java T-shirt",
				"Java Mug",
				"Duke Juggling Dolls",
				"Java Pin",
				"Java Key Chain" 
		};

		try {
			out = new DataOutputStream(new
					BufferedOutputStream(new 
							FileOutputStream(dataFile)));

			for (int i = 0; i < prices.length; i ++) {
				out.writeDouble(prices[i]);
				out.writeInt(units[i]);
				out.writeUTF(descs[i]);
			}
		} finally {
			out.close();
		}

		DataInputStream in = null;
		double total = 0.0;
		try {
			in = new DataInputStream(new
					BufferedInputStream(new 
							FileInputStream(dataFile)));

			double price;
			int unit;
			String desc;

			try {
				while (true) {
					price = in.readDouble();
					unit = in.readInt();
					desc = in.readUTF();
					System.out.format("You ordered %d units of %s at $%.2f%n",
							unit, desc, price);
					total += unit * price;
				}
			} catch (EOFException e) { }
			System.out.format("For a TOTAL of: $%.2f%n", total);
		}
		finally {
			in.close();
		}
	}
	
	public void objectStreams() throws IOException, ClassNotFoundException  {
		final String dataFile = "invoicedata";

		final BigDecimal[] prices = { 
				new BigDecimal("19.99"), 
				new BigDecimal("9.99"),
				new BigDecimal("15.99"),
				new BigDecimal("3.99"),
				new BigDecimal("4.99") 
		};
		final int[] units = { 12, 8, 13, 29, 50 };
		final String[] descs = { 
				"Java T-shirt",
				"Java Mug",
				"Duke Juggling Dolls",
				"Java Pin",
				"Java Key Chain" 
		};
		
		/*
		 * Write to "invoicedata"
		 */
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new
					BufferedOutputStream(new 
							FileOutputStream(dataFile)));

			out.writeObject(Calendar.getInstance());
			for (int i = 0; i < prices.length; i ++) {
				out.writeObject(prices[i]);
				out.writeInt(units[i]);
				out.writeUTF(descs[i]);
			}
		} 
		finally {
			out.close();
		}
		
		/*
		 * Read from "invoicedata"
		 */
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new
					BufferedInputStream(new 
							FileInputStream(dataFile)));

			Calendar date = null;
			BigDecimal price;
			int unit;
			String desc;
			BigDecimal total = new BigDecimal(0);

			date = (Calendar) in.readObject();

			System.out.format ("On %tA, %<tB %<te, %<tY:%n", date);

			try {
				while (true) {
					price = (BigDecimal) in.readObject();
					unit = in.readInt();
					desc = in.readUTF();
					System.out.format("You ordered %d units of %s at $%.2f%n",
							unit, desc, price);
					total = total.add(price.multiply(new BigDecimal(unit)));
				}
			} 
			catch (EOFException e) {}
			System.out.format("For a TOTAL of: $%.2f%n", total);
		} 
		finally {
			in.close();
		}
	}
	
}
