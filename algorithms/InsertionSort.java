package algorithms;

/**
 * Insertion sort implementation.
 * <p>Assumes left side of the array is sorted and 
 * inserts elements to left side by swapping adjacent 
 * elements adequately.</p>
 * <p>Time complexity of this operation is O(n^2) (quadratic) 
 * for worst and average cases, O(n) (linear) for best case. 
 * Space complexity of this operation is O(1).</p>
 * @author Baris Unlusarac
 */
public class InsertionSort{
	public static void sort(Number[] arr) {
		/**
		 * Sorts given array in ascending order.
		 * @param arr array of numeric values, must extend {@code java.lang.Number}
		 */
		for(int i=1; i < arr.length; ++i) {
			for(int j=i; j >= 1 && arr[j].doubleValue() < arr[j-1].doubleValue(); --j) {
				swap(arr, j, j-1);
			}
		}
	}
	
	private static void swap(Number[] arr, int i, int j) {
		Number temp = arr[i];			
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
