import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestIsHeap {

	Heap h;
	@BeforeEach
	void setUp() throws Exception {
		h = new Heap();
	}

	@Test
	void testEmpty() {
		assertTrue(Heap.isAHeap(h));
	}
	
	@Test
	void testOneData(){
		h.add(7);
		assertTrue(Heap.isAHeap(h));
	}
	
	@Test
	void testTwoData() {
		h.add(7);
		h.add(2);
		assertTrue(Heap.isAHeap(h));
		
		h.mData[0] =10;
		assertFalse(Heap.isAHeap(h));
		
	}
	
	@Test
	void testGeneralHeap() {
		h.add(7);
		h.add(2);
		h.add(8);
		h.add(1);
		h.add(3);
		h.add(6);
		h.add(5);
		h.add(2);
		h.add(4);
		assertTrue(Heap.isAHeap(h));
	}
	
	@Test
	void testGeneralNonHeap() {
		h.add(7);
		h.add(2);
		h.add(8);
		h.add(1);
		h.add(3);
		h.add(6);
		h.add(5);
		h.add(2);
		h.add(4);
		h.mData[0] = 1;
		h.mData[1] = 5;
		h.mData[2] = 10;
		h.mData[3] = 11;
		h.mData[4] = 12;
		h.mData[5] = 15;
		h.mData[6] = 17;
		h.mData[7] = 4;
		assertFalse(Heap.isAHeap(h));
		
	}
	
	@Test
	void testGeneralHeap2() {
		h.add(4);
		h.add(9);
		h.add(1);
		h.add(0);
		h.add(8);
		h.add(7);
		h.add(5);
		h.add(2);
		h.add(3);
		h.add(6);
		assertTrue(Heap.isAHeap(h));
	}
	
	@Test
	void testGeneralNonHeap2() {
		h.add(4);
		h.add(10);
		h.add(1);
		h.add(0);
		h.add(8);
		h.add(7);
		h.add(5);
		h.add(-1);
		h.add(3);
		h.add(6);
		h.mData[9] = 2;  
		assertFalse(Heap.isAHeap(h));
	}
	
	
	
	

}
