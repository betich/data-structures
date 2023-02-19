package main;

import static org.junit.jupiter.api.Assertions.*;

import main.CDLinkedList;
import main.DListIterator;
import main.DListNode;
import main.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CDLinkedListTest {
	CDLinkedList l1 = new CDLinkedList();
	DListIterator[] itrs = new DListIterator[4];
	DListIterator itr = new DListIterator(l1.header);
	int[] a = new int[12];

	void setIterators(int p1, int p2, int p3, int p4) throws Exception {
		for (int i = 1; i <= 12; i++) {
			itr.next();
			if (i == p1) {
				itrs[0] = new DListIterator(itr.currentNode);
			} 
			if (i == p2) {
				itrs[1] = new DListIterator(itr.currentNode);
			} 
			if (i == p3) {
				itrs[2] = new DListIterator(itr.currentNode);
			} 
			if (i == p4) {
				itrs[3] = new DListIterator(itr.currentNode);
			}

		}
		itr = new DListIterator(l1.header);
	}
	
	void storeInArray() throws Exception {
		for (int i = 1; i <= 12; i++) {
			int v = itr.next();
			a[i-1] = v;
		}
		itr = new DListIterator(l1.header);
	}

	@BeforeEach
	void setUp() throws Exception {
		l1 = new CDLinkedList();
		itrs = new DListIterator[4];
		itr = new DListIterator(l1.header);
		for (int i = 1; i <= 12; i++) {
			l1.insert(i, itr);
			itr.next();
		}
		itr = new DListIterator(l1.header);
	}

	@Test
	void testSwapRangeNull() throws Exception {
		l1.swapRange(null, null, null, null);
		DListIterator itr = new DListIterator(l1.header);
		for (int i = 1; i <= 12; i++) {
			int v = itr.next();
			assertEquals(i, v);
		}
		
	}
	
	@Test
	void testSwapRangeNotDListIterator() throws Exception{
		setIterators(2,4,8,11);
		Iterator it = new Iterator() {
			
			@Override
			public void set(int value) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public int previous() throws Exception {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int next() throws Exception {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public boolean hasPrevious() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		l1.swapRange(itrs[0], itrs[1], it, itrs[3]);
		DListIterator itr = new DListIterator(l1.header);
		for (int i = 1; i <= 12; i++) {
			int v = itr.next();
			assertEquals(i, v);
		}
	}

	@Test
	void testSwapRangeGeneric() throws Exception {
		setIterators(2,4,8,11);
		l1.swapRange(itrs[0], itrs[1], itrs[2], itrs[3]);
		itr = new DListIterator(l1.header);
		
		storeInArray();
		assertEquals(1,a[0]);
		assertEquals(8,a[1]);
		assertEquals(9,a[2]);
		assertEquals(10,a[3]);
		assertEquals(11,a[4]);
		assertEquals(5,a[5]);
		assertEquals(6,a[6]);
		assertEquals(7,a[7]);
		assertEquals(2,a[8]);
		assertEquals(3,a[9]);
		assertEquals(4,a[10]);
		assertEquals(12,a[11]);
		
	}
	
	@Test
	void testSwapRangeOneDataBoth() throws Exception {
		setIterators(2,2,8,8);
		l1.swapRange(itrs[0], itrs[1], itrs[2], itrs[3]);
		
		storeInArray();
		assertEquals(1,a[0]);
		assertEquals(8,a[1]);
		assertEquals(3,a[2]);
		assertEquals(4,a[3]);
		assertEquals(5,a[4]);
		assertEquals(6,a[5]);
		assertEquals(7,a[6]);
		assertEquals(2,a[7]);
		assertEquals(9,a[8]);
		assertEquals(10,a[9]);
		assertEquals(11,a[10]);
		assertEquals(12,a[11]);
		
	}
	
	@Test
	void testSwapRangeOneData1stRange() throws Exception {
		setIterators(3,3,6,9);
		l1.swapRange(itrs[0], itrs[1], itrs[2], itrs[3]);
		
		storeInArray();
		assertEquals(1,a[0]);
		assertEquals(2,a[1]);
		assertEquals(6,a[2]);
		assertEquals(7,a[3]);
		assertEquals(8,a[4]);
		assertEquals(9,a[5]);
		assertEquals(4,a[6]);
		assertEquals(5,a[7]);
		assertEquals(3,a[8]);
		assertEquals(10,a[9]);
		assertEquals(11,a[10]);
		assertEquals(12,a[11]);
	}
	
	@Test
	void testSwapRangeOneData2NdRange() throws Exception {
		setIterators(2,6,10,10);
		l1.swapRange(itrs[0], itrs[1], itrs[2], itrs[3]);
		
		storeInArray();
		assertEquals(1,a[0]);
		assertEquals(10,a[1]);
		assertEquals(7,a[2]);
		assertEquals(8,a[3]);
		assertEquals(9,a[4]);
		assertEquals(2,a[5]);
		assertEquals(3,a[6]);
		assertEquals(4,a[7]);
		assertEquals(5,a[8]);
		assertEquals(6,a[9]);
		assertEquals(11,a[10]);
		assertEquals(12,a[11]);
	}
	
	@Test
	void testSwapRangeAdjacent() throws Exception {
		setIterators(1,4,5,7);
		l1.swapRange(itrs[0], itrs[1], itrs[2], itrs[3]);
		
		storeInArray();
		assertEquals(5,a[0]);
		assertEquals(6,a[1]);
		assertEquals(7,a[2]);
		assertEquals(1,a[3]);
		assertEquals(2,a[4]);
		assertEquals(3,a[5]);
		assertEquals(4,a[6]);
		assertEquals(8,a[7]);
		assertEquals(9,a[8]);
		assertEquals(10,a[9]);
		assertEquals(11,a[10]);
		assertEquals(12,a[11]);
	}
	
	@Test
	void testSwapRangeEndList() throws Exception {
		setIterators(4,7,9,12);
		l1.swapRange(itrs[0], itrs[1], itrs[2], itrs[3]);
		
		storeInArray();
		assertEquals(1,a[0]);
		assertEquals(2,a[1]);
		assertEquals(3,a[2]);
		assertEquals(9,a[3]);
		assertEquals(10,a[4]);
		assertEquals(11,a[5]);
		assertEquals(12,a[6]);
		assertEquals(8,a[7]);
		assertEquals(4,a[8]);
		assertEquals(5,a[9]);
		assertEquals(6,a[10]);
		assertEquals(7,a[11]);
		
		
	}
	

}
