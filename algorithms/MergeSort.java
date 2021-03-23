package algorithms;

/**
 * Mergesort implementation. 
 *<p>Time complexity of this operation is O(nlogn) in
 * all cases. Space complexity of this operation is O(n).<p>
 * @author Baris Unlusarac
 *
 */
public class MergeSort {
	/**
	 * Sorts given array in ascending order.
	 * @param arr array of numeric values, must extend {@code java.lang.Number}
	 */
	public static void sort(Number[] arr) {
		sort(arr, 0, arr.length - 1);
	}
	
	//Recursion function
	private static void sort(Number[] arr, int left, int right) {
		if(left < right) {
			int middle = left + (right - left)/2;
			
			sort(arr, left, middle); //Sort left subarray
			sort(arr, middle+1, right); //Sort right subarray
			
			merge(arr, left, middle, right); //Merge subarrays
		}
	}
	
	private static void merge(Number[] arr, int left, int middle, int right) {
		int leftSubarraySize = middle - left + 1;
		int rightSubarraySize = right - middle; 
		
		Number[] leftSubarray = new Number[leftSubarraySize];
		Number[] rightSubarray = new Number[rightSubarraySize];
		
		//Copy values into subarrays
		for(int i=0; i < leftSubarraySize; ++i) 
			leftSubarray[i] = arr[i + left];
		
		for(int i=0; i < rightSubarraySize; ++i)
			rightSubarray[i] = arr[i + middle + 1];
		
		int leftSubarrayIndex = 0, rightSubarrayIndex = 0, mergedSubarrayIndex = left;
		
		while(leftSubarrayIndex < leftSubarraySize && rightSubarrayIndex < rightSubarraySize) {
			double leftSubarrayElement = leftSubarray[leftSubarrayIndex].doubleValue();
			double rightSubarrayElement = rightSubarray[rightSubarrayIndex].doubleValue();
			
			if(leftSubarrayElement <= rightSubarrayElement) {
				arr[mergedSubarrayIndex] = leftSubarray[leftSubarrayIndex];
				leftSubarrayIndex++;
			}
			else {
				arr[mergedSubarrayIndex] = rightSubarray[rightSubarrayIndex];
				rightSubarrayIndex++;
			}
			
			mergedSubarrayIndex++;	
		}
		
		while(leftSubarrayIndex < leftSubarraySize) {
			arr[mergedSubarrayIndex] = leftSubarray[leftSubarrayIndex];
			leftSubarrayIndex++;
			mergedSubarrayIndex++;
		}
		
		while(rightSubarrayIndex < rightSubarraySize) {
			arr[mergedSubarrayIndex] = rightSubarray[rightSubarrayIndex];
			rightSubarrayIndex++;
			mergedSubarrayIndex++;
		}
	}
}
