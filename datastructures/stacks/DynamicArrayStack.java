package datastructures.stacks;

import java.util.NoSuchElementException;

/**
 * Dynamic array stack implementation using an array
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
 * @param <T> type of elements to be stored in the stack
 */
public class DynamicArrayStack<T> {
	private static final int DEFAULT_INIT_CAPACITY = 10;
	private static final int EXTENSION_FACTOR = 2;
	private final int initialCapacity;
	
	private int capacity;
	private int size;
	private Object[] data;
	
	/**
	 * Initialize stack with default initial capacity 
	 * ({@code DEFAULT_INIT_CAPACITY}) which is 10.
	 */
	public DynamicArrayStack(){
		this(DEFAULT_INIT_CAPACITY);
	}
	
	/**
	 * Initialize stack with given initial capacity
	 * @param initialCapacity initial capacity of the stack.
	 */
	public DynamicArrayStack(int initialCapacity){
		this.capacity = initialCapacity;
		this.initialCapacity = initialCapacity;
		this.data = new Object[capacity];
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
		
		for(int i=1; i <= size; ++i) { 
			stackString.append(data[size-i]);
			stackString.append(' ');
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
	public boolean isEmpty() { return size == 0; }
	
	/**
	 * Insert an element at the top of the stack.
	 * <p>Time complexity of this operation is O(n) in worst case 
     * (expanding), O(1) in best case.</p>
	 * @param data data to be inserted
	 */
	public void push(T data) {
		if(size + 1 == capacity)
			extend();
		
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
		emptyCheck();
		
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
     * but removes the reference to the stack, which will make it viable
     * for garbage collection so that it's memory will eventually get
     * freed.</p>
     */
	public void clear() {
		capacity = initialCapacity;
		data = new Object[capacity];
		size = 0;
	}
	
	/**
	 * Trim the stack to an array with size of the stack, containing 
	 * no spaces.
	 * <p>Time complexity of this operation is O(n).</p>
	 * <p>Throws {@code NoSuchElementException} if stack is empty.</p>
	 */
	public void trimToSize() {
        emptyCheck();
		resize(size);
	}
	
	private void extend() {
		capacity *= EXTENSION_FACTOR;
		resize(capacity);
	}
	
	private void resize(int newsize) {
		Object[] newdata = new Object[newsize];
		
		for(int i=0; i < size; ++i)
			newdata[i] = data[i];
		
		data = newdata;
	}
	
	private void emptyCheck() {
        if(isEmpty())
            throw new NoSuchElementException("Stack is empty");
	}
	
}
