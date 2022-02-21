package find;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MyArrayOperations {
	
	// Make sure no constructor appears in the Javadoc
	private MyArrayOperations() {}

	/**
	 * @pre | haystack != null
	 * 
	 * @post Als `needle` niet in `haystack` voorkomt, is het resultaat -1. 
	 *     | IntStream.range(0, haystack.length).anyMatch(i -> haystack[i] == needle)
	 *     | || result == -1
	 * 
	 * @post
	 *     | Arrays.stream(haystack).anyMatch(e -> e == needle)
	 *     | || result == -1
	 *     
	 * @post
	 *     | result == -1 ||
	 *     | 0 <= result && result < haystack.length && haystack[result] == needle
	 * 
	 * @post The given array remains unchanged.
	 *     | Arrays.equals(haystack, old(haystack.clone()))
	 * 
	 * @inspects | haystack
	 */
	public static int find(int[] haystack, int needle) {
		if (haystack == null) return -2;
		int index = 0;
		for (;;) {
			if (index == haystack.length)
				return -1;
			if (haystack[index] == needle)
				return index;
			index++;
		}
	}
	
	/**
	 * Finds the given number `needle` in the given array `haystack` and returns its index,
	 * or -1 if the given number is not in the given array.
	 * The given array must be sorted in ascending order.
	 * 
	 * @pre
	 *     | haystack != null
	 * @pre The array `haystack` is sorted.
	 *     | IntStream.range(0, haystack.length - 1).allMatch(i ->
	 *     |     IntStream.range(i + 1, haystack.length).allMatch(j ->
	 *     |          haystack[i] <= haystack[j]
	 *     |     )
	 *     | )
	 * @pre
	 *     | IntStream.range(0, haystack.length - 1).allMatch(i ->
	 *     |     haystack[i] <= haystack[i + 1]
	 *     | )
	 *     
	 * @post
	 *     | Arrays.stream(haystack).anyMatch(e -> e == needle)
	 *     | || result == -1
	 *     
	 * @post
	 *     | result == -1 ||
	 *     | 0 <= result && result < haystack.length && haystack[result] == needle
	 *     
	 * @inspects | haystack
	 */
	public static int binarySearch(int[] haystack, int needle) {
		int start = 0;
		int end = haystack.length;
		while (start < end) {
			int middle = start + (end - start) / 2;
			if (needle < haystack[middle]) {
				end = middle;
			} else {
				start = middle;
			}
		}
		if (start < haystack.length && haystack[start] == needle)
			return start;
		else
			return -1;
	}
	
	/**
	 * Sorts the given array.
	 * 
	 * @pre The given array is not null.
	 *     | array != null
	 * 
	 * @post All elements that were in the array initially are still in the array, and equally many times.
	 *     | Arrays.stream(array).allMatch(e ->
	 *     |      Arrays.stream(array).filter(e1 -> e1 == e).count() ==
	 *     |      Arrays.stream(old(array.clone())).filter(e1 -> e1 == e).count()
	 *     | )
	 * 
	 * @post The array is in ascending order.
	 *     | IntStream.range(0, array.length - 1).allMatch(i ->
	 *     |     IntStream.range(i + 1, array.length).allMatch(j ->
	 *     |          array[i] <= array[j]
	 *     |     )
	 *     | )
	 * 
	 * @mutates | array
	 */
	public static void sort(int[] array) {
		
		// Bubble Sort
//		for (int n = array.length; 0 <= n; n--) {
//			for (int i = 0; i < n - 1; i++) {
//				if (array[i] > array[i + 1]) {
//					int x = array[i];
//					array[i] = array[i + 1];
//					array[i + 1] = x;
//				}
//			}
//		}
		
		// Evil Sort
//		for (int i = 0; i < array.length; i++)
//			array[i] = 0;
		
		// Insertion Sort
		for (int n = 1; n < array.length; n++)
			insert(array, n, array[n]);
	}
	
	/**
	 * Inserts the value `v` into the array segment from index 0 inclusive to index n exclusive.
	 * Shifts greater elements to the right by one position.
	 * If this segment was in ascending order initially, the segment from 0 to n inclusive will
	 * be in ascending order afterwards.
	 * The given array shall have a length greater than n. 
	 * 
	 * @pre | array != null
	 * @pre | 0 <= n
	 * @pre | n < array.length
	 * @pre The specified array segment is in ascending order.
	 *      | IntStream.range(0, n - 1).allMatch(i -> array[i] <= array[i + 1])
	 * 
	 * @post The array segment from 0 (inclusive) to n (inclusive) is in ascending order.
	 *      | IntStream.range(0, n).allMatch(i -> array[i] <= array[i + 1])
	 * @post All elements that were in the array segment initially, are still in the array segment, and equally many times,
	 *       except that `v` appears one time extra.
	 *     | Arrays.stream(array, 0, n + 1).allMatch(e ->
	 *     |      Arrays.stream(array, 0, n + 1).filter(e1 -> e1 == e).count() ==
	 *     |      Arrays.stream(old(array.clone()), 0, n).filter(e1 -> e1 == e).count() + (e == v ? 1 : 0)
	 *     | )
	 */
	public static void insert(int[] array, int n, int v) {
		for (int i = 0; ; i++) {
			if (i == n) {
				array[i] = v;
				break;
			}
			if (array[i] > v) {
				for (int j = n - 1; i <= j; j--)
					array[j + 1] = array[j];
				array[i] = v;
				break;
			}
		}
	}


}
