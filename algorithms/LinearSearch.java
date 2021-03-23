package algorithms;

import java.util.NoSuchElementException;

/**
 * Linear search implementation. 
 * <p>Time complexity of this operation is O(logn). Space
 * complexity of this operation is O(1).</p>
 * @author Baris Unlusarac
 *
 */
public class LinearSearch {
	/**
	 * Searches the array for given value and returns its index.
	 * <p>Throws {@code NoSuchElementException} if given
	 * value is not in the array.</p>
	 * @param arr array to be searched
	 * @param number value to be searched for, must extend
	 * {@code java.lang.Number}
	 * @return index of the founded value.
	 */
	static int search(int[] arr, int number) {
		for(int i = 0; i < arr.length; ++i) {
			if(arr[i] == number)
				return i;
		}
		
		throw new NoSuchElementException("Element not found in the array.");
	}
}
