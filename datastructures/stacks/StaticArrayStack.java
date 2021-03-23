package datastructures.stacks;

import java.nio.BufferOverflowException;
import java.util.NoSuchElementException;

/**
 * Stack implementation using fixed-size array.
 * </p>If the corresponding constructor argument regarding to 
 * capacity is left empty, {@code DEFAULT_CAPACITY} (which is 10)
 * will be assigned for capacity instead.</p>
 * @author Baris Unlusarac
 *
 * @param <T> type of elements to be stored in the queue
 */
public class StaticArrayStack<T> {
	private static final int DEFAULT_CAPACITY = 10;
	
	private final int capacity;
	private int size;
	private Object[] data;
	
	/**
	 * Initialize stack with given capacity
	 * @param capacity capacity of the stack.
	 */
	public StaticArrayStack(int capacity){
		this.capacity = capacity;
		this.size = 0;
		this.data = new Object[capacity];
	}
	
	/**
	 * Initialize stack with default capacity ({@code DEFAULT_CAPACITY})
	 * which is 10.
	 */
	public StaticArrayStack(){
		this(DEFAULT_CAPACITY);
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
	public boolean isEmpty() { return size == 0; }
	
	/**
	 * Returns a boolean value indicating fullness of the stack.
	 * @return true if stack is full, false otherwise
	 */
	public boolean isFull() { return size == capacity; }
	
	/**
	 * Returns a string representation of the stack, in which elements
	 * are spaced with an empty whitespace character.
	 * @return string representation of the stack
	 */
	@Override
	public String toString() {
		StringBuilder stackString = new StringBuilder();
		
		for(int i=1; i <= size; ++i) { 
			stackString.append(data[size-i]);
			stackString.append(' ');
		}
	
		return stackString.toString();
	}
	
	/**
	 * Insert an element at the top of the stack.
	 * <p>Time complexity of this operation is O(1)</p>
     * <p>Throws {@code BufferOverflowException} while inserting
     * and element to a full stack.</p>
	 * @param data data to be inserted
	 */
	public void push(T data) {
		if(isFull())
			throw new BufferOverflowException();
		
		this.data[size] = data; 
		++size;
	}
	
	/**
	 * Return the element at the top.
	 * <p>Time complexity of this operation is O(1).</p> 
	 * <p>Throws {@code NoSuchElementException} if stack is empty.</p>
	 * @return Element at the top of the stack
	 */
	public T peek() {
		if(isEmpty())
			throw new NoSuchElementException("Queue is empty");
		
		@SuppressWarnings("unchecked")
		T data = (T) this.data[size-1];
		return data;
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
		--size;
		return data;
	}
	
    /**
     * Delete all elements and clear the stack.
     * <p>This does not free memory allocated by the array immediately 
     * but removes the reference to the array, which will make it viable
     * for garbage collection so that it's memory will eventually get
     * freed.</p>
     */
	public void clear() {
		data = new Object[capacity];
		size = 0;
	}
	
	
}
