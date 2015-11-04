package Utils;

import java.util.Iterator;


/*Adding Iteration API for traditional stack implementation*/
public class Stack<Item> implements Iterable<Item> {
	
	private Node first = null;
	private int size;
	
	private class Node {
		private Item item;
		private Node nextNode;
	}
	
	public Stack() {
		this.size = 0;
	}

	public boolean isEmpty() {
		return (first == null);
	}
	
	public void push(Item s) {
		Node oldfirst = first;
		first = new Node();
		first.item = s;
		first.nextNode = oldfirst;
		size++;
	}
	
	public Item peek() {
		return first.item;
	}
	
	public Item pop() {
		Item item = first.item;
		first = first.nextNode;
		size--;
		return item;
	}
	
	/**
	 * return the number of current item in the stack
	 * @return
	 */
	public int size() {return size;}
	
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
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
