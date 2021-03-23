package datastructures.queues;

import java.util.NoSuchElementException;

import datastructures.Node;

/**
 * Queue implementation using linked {@code Node<T>} 
 * objects to store elements.
 * @author Baris Unlusarac
 * 
 * @param <T> type of elements to be stored in the list
 */
public class LinkedQueue<T>{
	private Node<T> rear, front;
	private int size;
	
	public LinkedQueue(){
		this.rear = null;
		this.front = null;
		this.size = 0;
	}
	
	/**
	 * Returns a string representation of the queue, in which elements
	 * are spaced with an empty whitespace character.
	 * @return string representation of the queue
	 */
	@Override
	public String toString() {
		Node<T> cursor = front;
		StringBuilder queueString = new StringBuilder();
		
		while(cursor != null) {
			queueString.append(cursor.data);
			queueString.append(' ');
			cursor = cursor.next;
		}
		
		return queueString.toString();
	}
	
	/**
	 * Returns a boolean value indicating emptiness of the queue.
	 * @return true if queue is empty, false otherwise
	 */
	public boolean isEmpty() { return front == null; }
	
	/**
	 * Returns size of the queue. (number of elements in the queue)
	 * @return size of the queue/number of elements
	 */
	public int size() { return size; }
	
	/**
	 * Insert an element at the rear of the queue.
	 * <p>Time complexity of this operation is O(1).</p>
	 * @param data data to be enqueued
	 */
	public void enqueue(T data) {
		Node<T> newNode = new Node<T>(data);
		
		if(isEmpty()) {
			rear = newNode;
			front = rear;
		}
		else {
			rear.next = newNode;
			rear = newNode;
		}
		
		++size;
	}
	
	/**
	 * Remove an element from front of the queue.
	 * <p>Time complexity of this operation is O(1).</p> 
	 * <p>Throws {@code NoSuchElementException} if queue is empty.</p>
	 * @return Element at the front of the queue
	 */
	public T dequeue() {
		//Get data (also checks for emptiness)
		T data = peek();
		front = front.next;
		--size;
		
		return data;
	}	
	
	/**
	 * Return the frontmost element.
	 * <p>Time complexity of this operation is O(1).</p> 
	 * <p>Throws {@code NoSuchElementException} if queue is empty.</p>
	 * @return Element at the front of the queue
	 */
	public T peek(){
		if(isEmpty())
			throw new NoSuchElementException("Queue is empty");
		
		return front.data;
	}
	
    /**
     * Delete all elements and clear the queue.
     */
	public void clear() {
		front = null;
		rear = null;
		size = 0;
	}
}
