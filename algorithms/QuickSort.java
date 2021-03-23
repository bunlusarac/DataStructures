package algorithms;

/**
 * Quick sort implementation.
 * <p>Chooses a pivot, rearranges elements around
 * it and repeats this recursively until the array
 * is sorted.</p>
 * <p>Time complexity of this operation is O(n^2)
 * (quadratic) in worst case, O(nlogn) 
 * in best and average cases. Space complexity of this
 * operation is O(logn).</p>
 * @author Baris Unlusarac
 */
public class QuickSort{
	public static void sort(Number[] arr) {
		sort(arr, 0, arr.length - 1);
	}
	
	/**
	 * Sorts given array in ascending order.
	 * @param arr array of numeric values, must extend {@code java.lang.Number}
	 */
	private static void sort(Number[] arr, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex) {
			int pivotIndex = partition(arr, leftIndex, rightIndex);
			
			sort(arr, leftIndex, pivotIndex - 1);
			sort(arr, pivotIndex + 1, rightIndex);
		}
	}
	
	private static int partition(Number[] arr, int leftIndex, int rightIndex) {
		int pivotIndex = rightIndex;
		
		int lastEditedIndex = leftIndex - 1;
		for(int k = leftIndex; k < pivotIndex ; k++) {
			if(arr[k].doubleValue() < arr[pivotIndex].doubleValue()) {
				lastEditedIndex++;
				swap(arr, k, lastEditedIndex);
			}
		}
		
		pivotIndex = lastEditedIndex + 1;
		swap(arr, pivotIndex, rightIndex);
		return pivotIndex; //Pivot index
	}
	
	private static void swap(Number[] arr, int i, int j) {
		Number temp = arr[i];
			
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
