package datastructures.queues;

import java.nio.BufferOverflowException;
import java.util.NoSuchElementException;

/**
 * Queue implementation using fixed-size array.
 * </p>If the corresponding constructor argument regarding to 
 * capacity is left empty, {@code DEFAULT_CAPACITY} (which is 10)
 * will be assigned for capacity instead.</p>
 * @author Baris Unlusarac
 *
 * @param <T> type of elements to be stored in the queue
 */
public class StaticArrayQueue<T> {
	private static final int DEFAULT_CAPACITY = 10;
	
	private final int capacity;
	private int rearIndex;
	private int frontIndex;
	private Object[] data;
	
	/**
	 * Initialize queue with given capacity
	 * @param capacity capacity of the queue.
	 */
	public StaticArrayQueue(int capacity){
		this.capacity = capacity;
		this.rearIndex = 0;
		this.frontIndex = 0;
		this.data = new Object[capacity];
	}
	
	/**
	 * Initialize queue with default capacity ({@code DEFAULT_CAPACITY})
	 * which is 10.
	 */
	public StaticArrayQueue() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Returns size of the queue. (number of elements in the queue)
	 * @return size of the queue/number of elements
	 */
	public int size(){ return rearIndex - frontIndex; }
	
	/**
	 * Returns a boolean value indicating emptiness of the queue.
	 * @return true if queue is empty, false otherwise
	 */
	public boolean isEmpty() { return rearIndex == frontIndex; }
	
	/**
	 * Returns a boolean value indicating fullness of the queue.
	 * @return true if queue is full, false otherwise
	 */
	public boolean isFull() { return size() == capacity; }
	
	/**
	 * Returns a string representation of the queue, in which elements
	 * are spaced with an empty whitespace character.
	 * @return string representation of the queue
	 */
	@Override
	public String toString() {
		StringBuilder queueString = new StringBuilder();
		
		for(int i=0; i < size(); i++) {
			queueString.append(data[frontIndex+i]);
			queueString.append(' ');
		}
		
		return queueString.toString();
	}
	
	/**
	 * Insert an element at the rear of the queue.
	 * <p>Time complexity of this operation is O(1).</p>
	 * <p>Throws {@code BufferOverflowException} while inserting
	 * an element to a full queue.</p>
	 * @param data data to be enqueued
	 */
	public void enqueue(T data) {
		if(isFull())
			throw new BufferOverflowException();
		
		this.data[rearIndex] = data;
		
		++rearIndex;
	}
	
	/**
	 * Return the frontmost element.
	 * <p>Time complexity of this operation is O(1).</p> 
	 * <p>Throws {@code NoSuchElementException} if queue is empty.</p>
	 * @return Element at the front of the queue
	 */
	public T peek() {
		if(isEmpty())
			throw new NoSuchElementException("Queue is empty");
		
		@SuppressWarnings("unchecked")
		T data = (T) this.data[frontIndex];
		return data;
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
		++frontIndex;
		
		if(isEmpty())
			clear();

		return data;
	}
	
    /**
     * Delete all elements and clear the queue.
     */
	public void clear() {
		rearIndex = 0;
		frontIndex = 0;
	}
}
