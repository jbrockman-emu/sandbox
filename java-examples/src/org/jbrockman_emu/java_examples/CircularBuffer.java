package org.jbrockman_emu.java_examples;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class CircularBuffer implements Iterable<String> {
	LinkedList<String> list = new LinkedList<String>();
	int nextIndex = 0;
	
	public CircularBuffer() {
		// default constructor
	}
	
	public String toString() {
		return(nextIndex + ":" + list.toString());
	}
	
	public void add(String s) {
		ListIterator<String> it = list.listIterator(nextIndex);
		it.add(s);
		nextIndex = it.nextIndex() % list.size();
	}
	
	public String next() {
		ListIterator<String> it = list.listIterator(nextIndex);
		String result = it.next();
		nextIndex = it.nextIndex() % list.size();
		return result;
	}
	
	public String previous() {
		if (nextIndex == 0) {
			nextIndex = list.size();
		}
		ListIterator<String> it = list.listIterator(nextIndex);
		String result = it.previous();
		nextIndex = it.nextIndex() % list.size();
		return result;
	}
	
	public void remove() {
		ListIterator<String> it = list.listIterator(nextIndex);
		it.next();
		it.remove();
		if (list.size() != 0) {
			nextIndex = it.nextIndex() % list.size();
		}
	}
	
	public Iterator<String> iterator() {
		return list.listIterator();
	}
	
	public boolean empty() {
		return list.size() == 0;
	}
}
