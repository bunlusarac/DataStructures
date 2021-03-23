package datastructures.heaps;

import java.util.NoSuchElementException;
import datastructures.lists.DynamicArrayList;

/**
 * Binary maximum heap implementation using {@code DynamicArrayList<T>}
 * @author Baris Unlusarac
 *
 * @param <T> type of elements to be stored in the heap,
 * must be subclass of {@code java.lang.Number}.
 */
public class MaxHeap<T extends Number> {
	public DynamicArrayList<T> data;
	
	/**
	 * Initialize heap.
	 */
	public MaxHeap() {
		this.data = new DynamicArrayList<T>();
	}
	
	/**
	 * Initialize heap from a given array.
	 * @param array to build heap from
	 */
	public MaxHeap(DynamicArrayList<T> arr) {
		this.data = arr;
		heapify();
	}
	
	/**
	 * Returns size of the heap. (number of elements in the heap)
	 * @return size of the heap/number of elements
	 */
	public int size() { return this.data.size(); };
	
	/**
	 *Returns a string representation of the heap, in which
	 *elements are spaced with empty whitespace character.
	 */
	@Override
	public String toString() { return this.data.toString(); }
	
	/**
	 * Insert an element to the heap.
	 * @param data data to be inserted
	 */
	public void insert(T data) {
		this.data.add(data);
	
		if(this.size() != 1) {
			heapify();	
		}
	}
	
	/**
	 * Remove an element from the heap.
	 * <p>Throws {@code NoSuchElementException} if the value 
	 * is not found on the heap.</p>
	 * @param data data to be removed
	 */
	public void delete(T data) {
		int i;
		int lastElementIndex = this.data.size() - 1;
		
		for(i=0; i < this.data.size(); ++i) {
			if(this.data.get(i) == data)
				break;
		}
		
		if(i == lastElementIndex + 1)
			throw new NoSuchElementException("There is no such element in the tree");
		
		swap(i, lastElementIndex);
		this.data.remove(lastElementIndex);
		
		heapify();
	}
	
	/**
	 * Return the maximum element and remove it.
	 * <p>Throws {@code NoSuchElementException} if the heap
	 * is empty.</p>
	 * @return the maximum element of the heap
	 */
	public T extractMax() {
		T data = peek();
		delete(data);
		return data;
	}
	
	/**
	 * Return the maximum element.
	 * <p>Throws {@code NoSuchElementException} if the heap
	 * is empty.</p>
	 * @return the maximum element of the heap
	 */
	public T peek() { 
		if(data.size() == 0)
			throw new NoSuchElementException("Heap is empty");
		
		return data.get(0); 
	}

	public void heapify() {
		for(int i = Math.floorDiv(this.size(), 2) - 1; i >= 0; i--)
			heapify(i);
	}
	
	public void heapify(int i) {
		int maxIndex = i;
		int leftChildIndex = 2*i + 1;
		int rightChildIndex = 2*i + 2;
		int size = this.size();
	
		T currentValue;
		
		if(leftChildIndex < size) {
			currentValue = this.data.get(maxIndex);
			T leftChild = this.data.get(leftChildIndex);
			if(leftChild.doubleValue() > currentValue.doubleValue() && leftChildIndex < size) {
				maxIndex = leftChildIndex;
			}
		}
		
		if(rightChildIndex < size) {
			currentValue = this.data.get(maxIndex);
			T rightChild = this.data.get(rightChildIndex);
			if(rightChild.doubleValue() > currentValue.doubleValue() && rightChildIndex < size) {
				maxIndex = rightChildIndex;
			}
		}
		
		if(i != maxIndex) {
			swap(maxIndex, i);
			heapify(maxIndex);
		}
	}
	
	public void heapify(int i, int size) {
		int maxIndex = i;
		int leftChildIndex = 2*i + 1;
		int rightChildIndex = 2*i + 2;
	
		T currentValue;
		
		if(leftChildIndex < size) {
			currentValue = this.data.get(maxIndex);
			T leftChild = this.data.get(leftChildIndex);
			if(leftChild.doubleValue() > currentValue.doubleValue() && leftChildIndex < size) {
				maxIndex = leftChildIndex;
			}
		}
		
		if(rightChildIndex < size) {
			currentValue = this.data.get(maxIndex);
			T rightChild = this.data.get(rightChildIndex);
			if(rightChild.doubleValue() > currentValue.doubleValue() && rightChildIndex < size) {
				maxIndex = rightChildIndex;
			}
		}
		
		if(i != maxIndex) {
			swap(maxIndex, i);
			heapify(maxIndex, size);
		}
	}
	
	private void swap(int i, int j) {
		T temp = this.data.get(i);
		this.data.set(i, this.data.get(j));
		this.data.set(j, temp);
	}
}
