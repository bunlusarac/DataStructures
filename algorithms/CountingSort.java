package algorithms;

/**
 * Counting sort implementation.
 * <p>Stores counts of each element and their
 * cumulative sums to place values in correct order.</p>
 * <p>Time complexity of this operation is O(maxElem + size)
 * for all cases. Space complexity of this operation is O(maxElem).
 * @author Baris Unlusarac
 */
public class CountingSort{
	/**
	 * 	
	/**
	 * Sorts given array in ascending order.
	 * @param arr array of numeric values, must be 
	 * array of primitive integers.
	 * @return sorted int array
	 */
	public static int[] sort(int[] arr) {
		int max = max(arr);
		int counts[] = new int[max + 1];
		int sorted[] = new int[arr.length];
		
		for(int data : arr)
			counts[data]++;
		
		for(int i=1; i < counts.length; i++) 
			counts[i] += counts[i-1];
			
		for(int i=0; i < arr.length; ++i) {
			sorted[(counts[arr[i]]) - 1] = arr[i];
			counts[arr[i]]--;
		}
		
		return sorted;
	}
	
	private static int max(int[] arr) {
		int max = arr[0];
		
		for(int data : arr) {
			if(data > max)
				max = data;
		}
		
		return max;
	}
}
