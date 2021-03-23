package datastructures.queues;

import java.util.NoSuchElementException;

/**
 * Dynamic array queue implementation using an array
 * that can grow during runtime. 
 * <p>The array that is used to store values will be expanded by
 * allocating a new array with size of the current array multiplied
 * with {@code EXTENSION_FACTOR} (which is 2) and copying 
 * values there once it gets full (when size = capacity). If the 
 * corresponding constructor argument regarding to initial capacity 
 * is left empty, {@code DEFAULT_INIT_CAPACITY} (which is 10) will 
 * be assigned for initial capacity instead. Expanded arrays won't
 * be shrinked to prevent overhead.</p>
 * @author Baris Unlusarac
 *
 * @param <T> type of elements to be stored in the queue
 */
public class DynamicArrayQueue<T> {
	private static final int DEFAULT_INIT_CAPACITY = 10;
	private static final int EXTENSION_FACTOR = 2;
	private final int initialCapacity;
	
	private int capacity;
	private int rearIndex;
	private int frontIndex;
	private Object[] data;
	
	/**
	 * Initialize queue with default initial capacity 
	 * ({@code DEFAULT_INIT_CAPACITY}) which is 10.
	 */
	public DynamicArrayQueue(){
		this(DEFAULT_INIT_CAPACITY);
	}
	
	/**
	 * Initialize queue with given initial capacity
	 * @param initialCapacity initial capacity of the queue.
	 */
	public DynamicArrayQueue(int initialCapacity){
		this.capacity = initialCapacity;
		this.initialCapacity = initialCapacity;
		this.data = new Object[capacity];
		this.rearIndex = 0;
		this.frontIndex = 0;
	}
	
	/**
	 * Returns a boolean value indicating emptiness of the queue.
	 * @return true if queue is empty, false otherwise
	 */
	public boolean isEmpty() { return rearIndex == frontIndex; }
	
	/**
	 * Returns a string representation of the queue, in which elements
	 * are spaced with an empty whitespace character.
	 * @return string representation of the queue
	 */
	@Override
	public String toString() { 
		StringBuilder queueString = new StringBuilder();
		
		for(int i=0; i < this.size(); ++i) {
			queueString.append(data[frontIndex + i]);
			queueString.append(' ');
		}
		
		return queueString.toString();
	}
	
	/**
	 * Returns size of the queue. (number of elements in the queue)
	 * @return size of the queue/number of elements
	 */
	public int size() { return rearIndex-frontIndex; }
	
	/**
	 * Insert an element at the rear of the queue.
	 * <p>Time complexity of this operation is O(n) in worst case 
     * (expanding), O(1) in best case.</p>
	 * @param data data to be enqueued
	 */
	public void enqueue(T data) {
		if(rearIndex == capacity - 1)
			extend();
			
		
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
		emptyCheck();

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
		
		return data;
	}
	
    /**
     * Delete all elements and clear the queue.
     * <p>This does not free memory allocated by the array immediately 
     * but removes the reference to the array, which will make it viable
     * for garbage collection so that it's memory will eventually get
     * freed.</p>
     */
	public void clear() {
		capacity = initialCapacity;
		data = new Object[capacity];
		rearIndex = 0;
		frontIndex = 0;
	}
	
	/**
	 * Trim the queue to an array with size of the queue, containing 
	 * no spaces.
	 * <p>Time complexity of this operation is O(n).</p>
	 * <p>Throws {@code NoSuchElementException} if queue is empty.</p>
	 */
	public void trimToSize() {
		emptyCheck();
		resize(this.size());
	}
	
	private void extend() {
		capacity *= EXTENSION_FACTOR;
		resize(capacity);
	}
	
	private void resize(int newsize) {
		Object[] newdata = new Object[newsize];
		
		for(int i=0; i < this.size(); ++i)
			newdata[i] = data[frontIndex+i];
		
		//Shift indices to correspond them with resized array
		rearIndex -= frontIndex;
		frontIndex = 0;
		
		data = newdata;
	}
	
	private void emptyCheck() {
        if(isEmpty())
            throw new NoSuchElementException("Queue is empty");
	}
}
