package find;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

class FindTest {
	
	/**
	 * @pre | haystack != null
	 * 
	 * IntStream.range(a, b).anyMatch(i -> P(i))
	 * Er bestaat een i tussen a (inclusief) en b (exclusief) zodat P(i) waar is
	 * 
	 * Arrays.stream(haystack).anyMatch(e -> e == needle)
	 * Er bestaat een element e in haystack zodat e == needle
	 * 
	 * Arrays.stream(myArray).anyMatch(e -> P(e))
	 * Er bestaat een e in myArray zodat P(e) waar is
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
	 */
	int find(int[] haystack, int needle) {
		if (haystack == null) return -2;
		int index = 0;
		for (;;) {
			if (index == haystack.length)
				return -1;
			if (haystack[index] == needle)
				return index;
			//index++;
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
	 */
	int binarySearch(int[] haystack, int needle) {
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

	@Test
	void testFound() {
		int[] myHaystack = {10, 20, 30};
		int positie = find(myHaystack, 20);
		assertEquals(1, positie);
	}
	
	@Test
	void testNotFound() {
		int[] myHaystack = {10, 20, 30};
		int positie = find(myHaystack, 40);
		assertEquals(-1, positie);
	}
	
	@Test
	void testNull() {
		int positie = find(null, 20);
		assertEquals(-1, positie);
	}

}
