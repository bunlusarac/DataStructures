package algorithms;

import datastructures.lists.DynamicArrayList;

/**
 * Bucketsort implementation. 
 * <p>Separates floating point values in ranged buckets, 
 * sorts each bucket using HeapSort and merges all buckets.</p>
 * @author Baris Unlusarac
 */
public class BucketSort {
	
	/**
	 * Sorts given array in ascending order.
	 * @param arr array of numeric values, must extend {@code java.lang.Number}
	 */
	public static void sort(double[] arr) {
		@SuppressWarnings("unchecked")
		DynamicArrayList<Double>[] buckets = new DynamicArrayList[arr.length];
		
		for(int i=0; i < arr.length; ++i)
			buckets[i] = new DynamicArrayList<Double>();
		
		for(int i=0; i < arr.length; ++i) {
			int bucketIndex = (int) (arr[i] * arr.length);
			buckets[bucketIndex].add(arr[i]);
		}
		
		int lastPosIndex = 0;
		
		for(DynamicArrayList<Double> bucket: buckets) {
			new HeapSort<Double>().sort(bucket);
			
			int i;
			for(i = 0; i < bucket.size(); ++i) {
				arr[lastPosIndex++] = bucket.get(i);
			}

		}
		
	}
}
