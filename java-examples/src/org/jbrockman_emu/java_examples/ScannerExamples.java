package org.jbrockman_emu.java_examples;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class ScannerExamples {
	
	void fish() {
		String input = "1 fish 2 fish red fish blue fish";
		Scanner s = new Scanner(input);
		s.useDelimiter("\\s*fish\\s*");
		System.out.println(s.nextInt());
		System.out.println(s.nextInt());
		System.out.println(s.next());
		System.out.println(s.next());
		s.close();
	}
	
	void matchFish() {
		String input = "1 fish 2 fish red fish blue fish";
		Scanner s = new Scanner(input);
		s.findInLine("(\\d+) fish (\\d+) fish (\\w+) fish (\\w+)");
		MatchResult result = s.match();
		for (int i=1; i<=result.groupCount(); i++)
			System.out.println(result.group(i));
		s.close(); 
	}
	
	void matchThreadletOp() {
		String input = " (  op  7 33   )";
		Scanner s = new Scanner(input);
		s.findInLine("\\(\\s*op\\s+(\\d+)\\s+(\\d+)\\s*\\)");
		MatchResult result = s.match();
		for (int i=1; i<=result.groupCount(); i++)
			System.out.println(result.group(i));
		s.close(); 
	}
	
	void scanTraceFile() throws IOException {
		BufferedReader inputStream = null;
		String line = null;
		Scanner s = null;
		try {
			inputStream = new BufferedReader(new FileReader("trace.txt"));
			while ((line = inputStream.readLine()) != null) {
				s = new Scanner(line);
				s.findInLine("\\(\\s*op\\s+(\\d+)\\s+(\\d+)\\s*\\)");
				MatchResult result = s.match();
				System.out.println(result.group(1) + " " + result.group(2));
			}
		}
		finally {
			inputStream.close();
		}
	}
}
