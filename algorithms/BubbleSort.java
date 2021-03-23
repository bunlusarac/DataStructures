package algorithms;

/**
 * Bubble sort implementation.
 * <p>Swaps adjacent elements and shifts maximum
 * values to right end of the array in each iteration</p>
 * <p>Time complexity of this operation is O(n^2)
 * (quadratic) in worst and average cases, O(n) 
 * (linear) in best case. Space complexity of this
 * operation is O(1).</p>
 * @author Baris Unlusarac
 */
public class BubbleSort{
	
	/**
	 * Sorts given array in ascending order.
	 * @param arr array of numeric values, must extend {@code java.lang.Number}
	 */
	public static void sort(Number[] arr) {
		for(int i=0; i < arr.length; ++i) {
			boolean sorted = true;
			for(int j=0; j < arr.length - 1 - i; ++j) {
				if(arr[j].doubleValue() > arr[j+1].doubleValue()) {
					swap(arr, j, j+1);
					sorted = false;
				}
			}
			
			if(sorted)
				break;
		}
	}
	
	private static void swap(Number[] arr, int i, int j) {
		Number temp = (Number) arr[i];
			
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
