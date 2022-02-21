package find;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FindTest {
	
	@Test
	void testInsert() {
		int[] myArray = {10, 20, 30, 25};
		MyArrayOperations.insert(myArray, 3, 17);
		assertArrayEquals(new int[] {10, 17, 20, 30}, myArray);
	}
	
	
	@Test
	void testInsertAtEnd() {
		int[] myArray = {10, 20, 30, 25};
		MyArrayOperations.insert(myArray, 3, 37);
		assertArrayEquals(new int[] {10, 20, 30, 37}, myArray);
	}


	@Test
	void testSort() {
		int[] myArray = {30, 20, 40, 10, 50, 35};
		MyArrayOperations.sort(myArray);
		assertArrayEquals(new int[] {10, 20, 30, 35, 40, 50}, myArray);
	}
	
	@Test
	void testFound() {
		int[] myHaystack = {10, 20, 30};
		int positie = MyArrayOperations.find(myHaystack, 20);
		assertEquals(1, positie);
	}
	
	@Test
	void testNotFound() {
		int[] myHaystack = {10, 20, 30};
		int positie = MyArrayOperations.find(myHaystack, 40);
		assertEquals(-1, positie);
	}
	
//	@Test
//	void testNull() {
//		int positie = MyArrayOperations.find(null, 20);
//		assertEquals(-1, positie);
//	}

}
