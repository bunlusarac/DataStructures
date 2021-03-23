package algorithms;

import datastructures.heaps.MaxHeap;
import datastructures.lists.DynamicArrayList;

/**
 * Heap sort implementation.
 * <p>Uses max heaps to contain elements, sorts by
 * swapping max element with last element, deleting
 * it and then heapifying the tree.</p>
 * <p>Time complexity of this operation is O(nlogn)
 * for all cases. Space complexity of this operation 
 * is O(1).</p>
 * @author Baris Unlusarac
 */

public class HeapSort<T extends Number>{
	/**
	 * Sorts given array in ascending order.
	 * @param arr array of numeric values, must extend {@code java.lang.Number}
	 */
	public void sort(DynamicArrayList<T> data) {
		MaxHeap<T> dataHeap = new MaxHeap<T>(data);
		
		for(int i=0; i < dataHeap.size() ; ++i) {
			swap(dataHeap, 0, dataHeap.size() - 1 - i);
			dataHeap.heapify(0, dataHeap.size() - 1 - i);
		}
	}
	
	private void swap(MaxHeap<T> dataHeap, int i, int j) {
		T temp = dataHeap.data.get(i);
		dataHeap.data.set(i, dataHeap.data.get(j));
		dataHeap.data.set(j, temp);
	}
}
