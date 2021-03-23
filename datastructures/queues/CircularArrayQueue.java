package datastructures.queues;

import java.nio.BufferOverflowException;
import java.util.NoSuchElementException;

/**
 * Circular queue implementation using fixed-size array.
 * <p>Instead of {@code StaticArrayQueue<T>}, {@code CircularArrayQueue<T>}
 * will try to use all empty space of array whenever it can by enqueuing new 
 * elements at the beginning of array if there's empty space there.</p>
 * </p>If the corresponding constructor argument regarding to 
 * capacity is left empty, {@code DEFAULT_CAPACITY} (which is 10)
 * will be assigned for capacity instead.</p>
 * @author Baris Unlusarac
 *
 * @param <T> type of elements to be stored in the queue
 */
public class CircularArrayQueue<T> {
	private static final int DEFAULT_CAPACITY = 10;
	
	private final int capacity;
	private int rearIndex;
	private int frontIndex;
	private Object[] data;
	private boolean isEmpty, isFull;
	
	/**
	 * Initialize queue with default capacity ({@code DEFAULT_CAPACITY})
	 * which is 10.
	 */
	public CircularArrayQueue() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Initialize queue with given capacity
	 * @param capacity capacity of the queue.
	 */
	public CircularArrayQueue(int capacity) {
		this.capacity = capacity;
		this.data = new Object[capacity];
		this.rearIndex = 0;
		this.frontIndex = 0;
		this.isEmpty = true;
		this.isFull = false;
	}
	
	/**
	 * Returns a string representation of the queue, in which elements
	 * are spaced with an empty whitespace character.
	 * @return string representation of the queue
	 */
	@Override
	public String toString() {
		StringBuilder queueString = new StringBuilder();
		
		for(int i=0; i < size(); ++i) {
			if(i + frontIndex < capacity)
				queueString.append(data[frontIndex+i]);
			else
				queueString.append(data[i-(capacity-frontIndex)]);
			
			queueString.append(' ');
		}
		
		return queueString.toString();
	}
	
	/**
	 * Returns a boolean value indicating emptiness of the queue.
	 * @return true if queue is empty, false otherwise
	 */
	public boolean isEmpty() { return isEmpty; }
	
	/**
	 * Returns a boolean value indicating fullness of the queue.
	 * @return true if queue is full, false otherwise
	 */
	public boolean isFull() { return isFull; }
	
	/**
	 * Returns size of the queue. (number of elements in the queue)
	 * @return size of the queue/number of elements
	 */
	public int size() {
		if(frontIndex >= rearIndex)
			return capacity - (frontIndex - rearIndex);
		else
			return rearIndex - frontIndex;
	}
	
	/**
	 * Insert an element at the rear of the queue.
	 * <p>Time complexity of this operation is O(1).</p>
	 * @param data data to be enqueued
	 */
	public void enqueue(T data) {
		//Is full?
		if(isFull)
			throw new BufferOverflowException();
		
		//Then set the data
		this.data[rearIndex] = data;
		
		//And move rearIndex to the adjacent position
		if(rearIndex + 1 == capacity) {
			rearIndex = 0;
		}
		else {
			++rearIndex;
		}
		
		//Emptiness/fullness update
		isEmpty = false;
		
		if(rearIndex == frontIndex)
			isFull = true;
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
		
		//And move frontIndex to the adjacent position
		if(frontIndex + 1 == capacity) {
			frontIndex = 0;
		}
		else {
			++frontIndex;
		}
		
		//Emptiness/fullness update
		isFull = false;
		
		if(rearIndex == frontIndex)
			isEmpty = true;
		
		return data;
	}
	
	/**
	 * Return the frontmost element.
	 * <p>Time complexity of this operation is O(1).</p> 
	 * <p>Throws {@code NoSuchElementException} if queue is empty.</p>
	 * @return Element at the front of the queue
	 */
	public T peek() {
		if(isEmpty)
			throw new NoSuchElementException("Queue is empty");
		
		@SuppressWarnings("unchecked")
		T data = (T) this.data[frontIndex];
		return data;
	}
	
    /**
     * Delete all elements and clear the queue.
     */
	public void clear() {
		rearIndex = 0;
		frontIndex = 0;
		isEmpty = true;
		isFull = false;
	}
}
