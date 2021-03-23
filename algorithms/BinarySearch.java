package algorithms;

import java.util.NoSuchElementException;

/**
 * Binary search implementation. 
 * <p>This operation must be applied on a sorted
 * array.</p>
 * <p>Time complexity of this operation is O(logn) in
 * worst & average cases, O(1) in best case. Space
 * complexity of this operation is O(1).</p>
 * @author Baris Unlusarac
 *
 */
public class BinarySearch {
	/**
	 * Recursively searches the array for given value
	 * and returns its index.
	 * <p>Throws {@code NoSuchElementException} if given
	 * value is not in the array.</p>
	 * @param arr array to be searched
	 * @param number value to be searched for, must extend
	 * {@code java.lang.Number}
	 * @return index of the founded value.
	 */
	public static int search(Number[] arr, Number number) {
		return search(arr, 0, arr.length - 1, number);
	}
	
	//Recursion method
	private static int search(Number[] arr, int low, int hi, Number number) {
		int midIndex = (low + hi)/2;
		
		if(low > hi) {
			throw new NoSuchElementException("Element not found in the array.");
		}
		if(arr[midIndex] == number) {
			return midIndex;
		}
		else if(number.doubleValue() < arr[midIndex].doubleValue()) {
			return search(arr, low, midIndex-1 , number);
		}
		else {
			return search(arr, midIndex+1, hi, number);
		}
	}
}
