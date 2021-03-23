package datastructures.lists;

import java.util.NoSuchElementException;
import java.util.Iterator;

import datastructures.ArrayListIterator;



/**
 * Dynamic array implementation that can grow during runtime.
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
 * @param <T> type of elements to be stored in the list
 */
public class DynamicArrayList<T> implements Iterable<T>{
	private static final int DEFAULT_INIT_CAPACITY = 10;
	private static final int EXTENSION_FACTOR = 2;
	private final int initialCapacity;
	
	private int capacity;
	private int size;
	private Object[] data;
	
	/**
	 * Initialize list with default initial capacity 
	 * ({@code DEFAULT_INITAL_CAPACITY}) which is 10.
	 */
	public DynamicArrayList(){
		this(DEFAULT_INIT_CAPACITY);
	}
	
	/**
	 * Initialize list with given capacity.
	 * @param initialCapacity initial capacity of the list.
	 */
	public DynamicArrayList(int initialCapacity){
		this.capacity = initialCapacity;
		this.initialCapacity = initialCapacity;
		this.data = new Object[capacity];
		this.size = 0;
	}
	
	/**
	 * Returns a string representation of the list, in which elements
	 * are spaced with an empty whitespace character.
	 * @return string representation of the list
	 */
	@Override
	public String toString() {
		StringBuilder listString = new StringBuilder();
		
		for(int i=0; i < size; ++i) {
			listString.append(data[i]);
			listString.append(' ');
		}
		
		return listString.toString();
	}
	
	/**
	 * Returns a boolean value indicating emptiness of the list.
	 * @return true if list is empty, false otherwise
	 */
	public boolean isEmpty() { return size == 0;} 
	
    /**
     * Adds an element to end of the list.
     * <p>Time complexity of this operation is O(n) in worst case 
     * (expanding), O(1) in best case.</p>
     * @param data data of the new element
     */
	public void add(T data) {
		add(size, data);
	}
	
    /**
     * Adds an element to given index.
     * <p>Time complexity of this operation is O(n) in worst case 
     * (shifting/expanding), O(1) in best case.</p>
     * <p>Throws {@code IndexOutOfBoundsException} if given index is out of bounds.</p>
     * @param index index of the new element
     * @param data data of the new element
     */
	public void add(int index, T data) {
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		
		if(size + 1 == capacity)
			extend();
		
		for(int i=0; i < (size - index); ++i)
			this.data[size-i] = this.data[size-(i+1)];
		
		this.data[index] = data;
		++size;
	}
	
    /**
     * Delete an element at given index.
     * <p>Time complexity of this operation is O(n) in worst case 
     * (shifting), O(1) in best case.</p>
     * <p>Throws {@code IndexOutOfBoundsException} if given index is out of bounds.</p>
     * @param index index of the element to be deleted. 
     */
	public void remove(int index) { 
		boundCheck(index);
		
		for(int i=0; i < (size - (index + 1)); ++i)
			data[index+i] = data[index+i+1];
		
		--size;
	}
	
	/**
	 * Trim the list to an array with size of the list.
	 * <p>Time complexity of this operation is O(n).</p>
	 * <p>Throws {@code NoSuchElementException} if list is empty.</p>
	 */
	public void trimToSize() {
        emptyCheck();
		resize(size);
	}
	
    /**
     * Get element at given index
     * <p>Time complexity of this operation is O(1).</p>
     * <p>Throws {@code IndexOutOfBoundsException} if given index is out of bounds.</p>
     * @param index index to get data from
     * @return data at given index
     */
	public T get(int index) {
		boundCheck(index);
		
		@SuppressWarnings("unchecked")
		T data = (T) this.data[index];
		return data;
	}
	
    /**
     * Set element at given index
     * <p>Time complexity of this operation is O(1).</p>
     * <p>Throws {@code IndexOutOfBoundsException} if given index is out of bounds.</p>
     * @param index index of element whose data will be changed
     * @param data data to be assigned to the element
     */
	public void set(int index, T data) {
		boundCheck(index);
		this.data[index] = data;
	}
	
	/**
	 * Returns size of the list. (number of elements in the list)
	 * @return size of the list/number of elements
	 */
	public int size() { return size; }
	
	public Iterator<T> iterator(){
		return new ArrayListIterator<T>(data, size);
	}
	
    /**
     * Delete all elements and clear the list.
     * <p>This does not free memory allocated by the array immediately 
     * but removes the reference to the array, which will make it viable
     * for garbage collection so that it's memory will eventually get
     * freed.</p>
     */
	public void clear() {
        capacity = initialCapacity;
		data = new Object[capacity];
		size = 0;
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
	
	private void boundCheck(int index) {
		if(index < 0 || index > size-1)
			throw new IndexOutOfBoundsException();
	}
	
	private void emptyCheck() {
        if(isEmpty())
            throw new NoSuchElementException("List is empty");
	}
}
