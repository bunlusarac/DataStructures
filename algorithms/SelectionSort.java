package algorithms;

/**
 * Selection sort implementation.
 * <p>Sorts the array by repeatedly searching
 * for minimum from unsorted region and moving
 * it to the beginning.</p>
 * <p>Time complexity of this operation is O(n^2)
 * (quadratic) in all cases. Space complexity of this
 * operation is O(1).</p>
 * @author Baris Unlusarac
 */
public class SelectionSort{
	/**
	 * Sorts given array in ascending order.
	 * @param arr array of numeric values, must extend {@code java.lang.Number}
	 */
	public static void sort(Number[] arr) {
		for(int i=0; i < arr.length; ++i) {
			int minIndex = i;
			
			for(int j = i+1; j < arr.length; ++j) {
				if(arr[j].doubleValue() < arr[minIndex].doubleValue()) 
					minIndex = j;
			}
			
			swap(arr, minIndex, i);
		}
	}
	
	private static void swap(Number[] arr, int i, int j) {
		Number temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
