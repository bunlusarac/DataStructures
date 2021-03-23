package datastructures.lists;

import java.nio.BufferOverflowException;
import java.util.Iterator;
import datastructures.ArrayListIterator;

/**
 * Static array implementation with fixed capacity.
 * @author Baris Unlusarac
 *
 * @param <T> type of elements to be stored in the list
 */
public class StaticArrayList<T> implements Iterable<T>{
	private int capacity;
	private int size;
	private Object[] data;
	
	/**
	 * Initialize list with given capacity.
	 * @param initialCapacity initial capacity of the list.
	 */
	public StaticArrayList(int capacity){
		this.capacity = capacity;
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
	 * Returns a boolean value indicating fullness of the list.
	 * @return true if list is empty, false otherwise
	 */
	public boolean isFull() { return size == capacity;}  
	
    /**
     * Adds an element to end of the list.
     * <p>Time complexity of this operation is O(1).</p>
     * <p>Throws {@code IndexOutOfBoundsException} if given index is out of bounds.</p>
     * <p>Throws {@code BufferOverflowException} if the array is full.</p>
     * @param data data of the new element
     */
	public void add(T data) {
		add(size, data);
	}
	
    /**
     * Adds an element to given index.
     * <p>Time complexity of this operation is O(n) in worst case 
     * (shifting), O(1) in best case.</p>
     * <p>Throws {@code IndexOutOfBoundsException} if given index is out of bounds.</p>
     * <p>Throws {@code BufferOverflowException} if the array is full.</p>
     * @param index index of the new element
     * @param data data of the new element
     */
	public void add(int index, T data) {
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		
		if(isFull())
			throw new BufferOverflowException();
		
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
		data = new Object[capacity];
		size = 0;
	}
	
    /**
     * Get element at given index. This is used by {@code BTree<T>} to access 
     * and should null values and should not be used in normal cases.
     * <p>Time complexity of this operation is O(1).</p>
     * <p>Throws {@code IndexOutOfBoundsException} if given index is out of bounds.</p>
     * @param index index to get data from
     * @return data at given index
     */
	public T _get(int index) {
		@SuppressWarnings("unchecked")
		T data = (T) this.data[index];
		return data;
	}
	
	private void boundCheck(int index) {
		if(index < 0 || index > size-1)
			throw new IndexOutOfBoundsException();
	}
}
