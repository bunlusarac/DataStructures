package algorithms;

/**
 * Shell sort implementation.
 * <p>Sorts the array by swapping elements spaced 
 * by intervals that reduce in each iteration.</p>
 * <p>Time complexity of this operation is O(nlogn)
 * in best and average cases, less than or equal to
 * O(n^2) (quadratic) in the worst case. Space complexity 
 * of this operation is O(1).</p>
 * @author Baris Unlusarac
 */
public class ShellSort{
	/**
	 * Sorts given array in ascending order.
	 * @param arr array of numeric values, must extend {@code java.lang.Number}
	 */
	public static void sort(Number[] arr) {
		int interval = arr.length/2;

		while(interval > 0) {
			for(int i=0; i < arr.length - interval; i++) {
				for(int j=i; j >= 0 ; j -= interval) {
					if(arr[j].doubleValue() > arr[j+interval].doubleValue())
						swap(arr, j, j+interval);
				}	
			}
			
			interval /= 2;
		}

	}
	
	private static void swap(Number[] arr, int i, int j) {
		Number temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
