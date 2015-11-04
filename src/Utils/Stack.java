package Utils;

import java.util.Iterator;

/**
 * 
 * <tt>Stack</tt> implementation with generic type.
 * Support typical stack operations: push, pop, peek.
 * This implementation also supports item iteration.
 */

public class Stack<Item> implements Iterable<Item> {
	
	private Node first = null;
	private int size;
	
	private class Node {
		private Item item;
		private Node nextNode;
	}
	
	public Stack() { this.size = 0;	}

	/**
	 * Return true if the stack is empty, false if otherwise
	 * @return
	 */
	public boolean isEmpty() {	return (first == null);	}
	
	/**
	 * Add a new item to the stack
	 * @param s
	 */
	public void push(Item s) {
		Node oldfirst = first;
		first = new Node();
		first.item = s;
		first.nextNode = oldfirst;
		size++;
	}
	
	/**
	 * Only "check-out" the item at the head stack 
	 * but not removing it
	 * @return
	 */
	public Item peek() {
		return first.item;
	}

	/**
	 * Remove and return the item at the head of the stack 
	 * @return
	 */
	public Item pop() {
		Item item = first.item;
		first = first.nextNode;
		size--;
		return item;
	}
	
	/**
	 * return the number of current items on the stack
	 * @return
	 */
	public int size() {return size;}
	
	
	/**
	 * return an iterator of all items on the stack 
	 */
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		
		private Node current = first;
		public boolean hasNext() { return current !=null; }
		
		public Item next() {
			Item item = current.item;
			current   = current.nextNode;
			return item;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
