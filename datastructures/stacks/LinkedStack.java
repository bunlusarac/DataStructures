package datastructures.stacks;

import java.util.NoSuchElementException;

import datastructures.Node;

/**
 * Stack implementation using linked {@code Node<T>}
 * objects to store elements.
 * @author Baris Unlusarac
 *
 * @param <T> type of elements to be stored in the list
 */
public class LinkedStack<T> {
	private Node<T> top;
	private int size;
	
	public LinkedStack(){
		this.top = null;
		this.size = 0;
	}
	
	/**
	 * Returns a string representation of the stack, in which elements
	 * are spaced with an empty whitespace character.
	 * @return string representation of the stack
	 */
	@Override
	public String toString() {
		StringBuilder stackString = new StringBuilder();
		Node<T> cursor = top;
		
		while(cursor != null) {
			stackString.append(cursor.data);
			stackString.append(' ');
			cursor = cursor.next;
		}
		
		return stackString.toString();
	}
	
	/**
	 * Returns size of the stack. (number of elements in the stack)
	 * @return size of the stack/number of elements
	 */
	public int size() { return size; }
	
	/**
	 * Returns a boolean value indicating emptiness of the stack.
	 * @return true if stack is empty, false otherwise
	 */
	public boolean isEmpty() { return top == null; }
	
	/**
	 * Insert an element at the top of the stack.
	 * <p>Time complexity of this operation is O(1).</p>
	 * @param data data to be inserted
	 */
	public void push(T data) {
		Node<T> newNode = new Node<>(data, top);
		top = newNode;
		++size;
	}
	
	/**
	 * Remove an element from top of the stack.
	 * <p>Time complexity of this operation is O(1).</p> 
	 * <p>Throws {@code NoSuchElementException} if queue is empty.</p>
	 * @return Element at the top of the stack
	 */
	public T pop() {
		//Get data (also checks for emptiness)
		T data = peek();
		top = top.next; 
		--size;
		
		return data;
	}
	
	/**
	 * Return the element at the top.
	 * <p>Time complexity of this operation is O(1).</p> 
	 * <p>Throws {@code NoSuchElementException} if stack is empty.</p>
	 * @return Element at the top of the stack
	 */
	public T peek() {
		if(isEmpty())
			throw new NoSuchElementException("Stack is empty");
		
		return top.data;
	}
	
    /**
     * Delete all elements and clear the stack.
     */
	public void clear() {
		top = null;
		size = 0;
	}
}
