import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBankQueue {

	@BeforeEach
	void setUp() throws Exception {
	}

	private DeQ initializeQueue(int option, int first, int last) throws Exception {
		DeQ a;
		if (option == 1) {
			a = new DeQArray();
		} else {
			a = new DeQLinkedList();
		}
		for (int i = first; i <= last; i++) {
			a.insertLast(i);
		}
		return a;
	}

	@Test
	void testDistribute01() throws Exception {
		DeQ[] counters = new DeQ[4];
		counters[0] = initializeQueue(1, 1, 6);
		counters[1] = initializeQueue(2, 7, 13);
		counters[2] = initializeQueue(1, 14, 19);
		counters[3] = initializeQueue(2, 20, 26);

		DeQ special = new DeQArray();
		BankQueue s = new BankQueue(counters, special);
		s.distribute();
		assertEquals(5, counters[0].size());
		for (int i = 1; i <= 5; i++) {
			int x = counters[0].removeFirst();
			assertEquals(i, x);
		}
		assertEquals(5, counters[1].size());
		for (int i = 7; i <= 11; i++) {
			int x = counters[1].removeFirst();
			assertEquals(i, x);
		}
		assertEquals(5, counters[2].size());
		for (int i = 14; i <= 18; i++) {
			int x = counters[2].removeFirst();
			assertEquals(i, x);
		}
		assertEquals(6, counters[3].size());
		for (int i = 20; i <= 24; i++) {
			int x = counters[3].removeFirst();
			assertEquals(i, x);
		}
		int x = counters[3].removeFirst();
		assertEquals(26, x);

		assertEquals(5, special.size());
		assertEquals(6, special.removeFirst());
		assertEquals(12, special.removeFirst());
		assertEquals(13, special.removeFirst());
		assertEquals(19, special.removeFirst());
		assertEquals(25, special.removeFirst());
	}
	
	@Test
	void testDistribute02() throws Exception {
		DeQ[] counters = new DeQ[3];
		counters[0] = initializeQueue(1, 1, 4);
		counters[1] = initializeQueue(2, 5, 8);
		counters[2] = initializeQueue(1, 9, 13);

		DeQ special = new DeQLinkedList();
		BankQueue s = new BankQueue(counters, special);
		s.distribute();
		assertEquals(3, counters[0].size());
		for (int i = 1; i <= 3; i++) {
			int x = counters[0].removeFirst();
			assertEquals(i, x);
		}
		assertEquals(3, counters[1].size());
		for (int i = 5; i <= 7; i++) {
			int x = counters[1].removeFirst();
			assertEquals(i, x);
		}
		assertEquals(4, counters[2].size());
		for (int i = 9; i <= 11; i++) {
			int x = counters[2].removeFirst();
			assertEquals(i, x);
		}
		int x = counters[2].removeFirst();
		assertEquals(13, x);

		assertEquals(3, special.size());
		assertEquals(4, special.removeFirst());
		assertEquals(8, special.removeFirst());
		assertEquals(12, special.removeFirst());
	}
	
	@Test
	void testDistribute03() throws Exception {
		DeQ[] counters = new DeQ[5];
		counters[0] = initializeQueue(2, 1, 2);
		counters[1] = initializeQueue(1, 3, 4);
		counters[2] = initializeQueue(2, 5, 6);
		counters[3] = initializeQueue(1, 7, 8);
		counters[4] = initializeQueue(2, 9, 10);
		
		DeQ special = new DeQArray();
		BankQueue s = new BankQueue(counters, special);
		s.distribute();
		assertEquals(2, counters[0].size());
		for (int i = 1; i <= 2; i++) {
			int x = counters[0].removeFirst();
			assertEquals(i, x);
		}
		assertEquals(2, counters[1].size());
		for (int i = 3; i <= 4; i++) {
			int x = counters[1].removeFirst();
			assertEquals(i, x);
		}
		assertEquals(2, counters[2].size());
		for (int i = 5; i <= 6; i++) {
			int x = counters[2].removeFirst();
			assertEquals(i, x);
		}
		assertEquals(2, counters[3].size());
		for (int i = 7; i <= 8; i++) {
			int x = counters[3].removeFirst();
			assertEquals(i, x);
		}
		
		assertEquals(1, counters[4].size());
		assertEquals(9,counters[4].removeFirst());
			
		assertEquals(1, special.size());
		assertEquals(10, special.removeFirst());
	}
	
	@Test
	void testDistribute04() throws Exception {
		DeQ[] counters = new DeQ[6];
		counters[0] = initializeQueue(2, 1, 4);
		counters[1] = initializeQueue(1, 5, 8);
		counters[2] = initializeQueue(2, 9, 12);
		counters[3] = initializeQueue(1, 13, 16);
		counters[4] = initializeQueue(2, 17, 20);
		counters[5] = initializeQueue(1, 21, 23);
		
		DeQ special = new DeQLinkedList();
		BankQueue s = new BankQueue(counters, special);
		s.distribute();

		assertEquals(3, counters[0].size());
		for (int i = 1; i <= 3; i++) {
			int x = counters[0].removeFirst();
			assertEquals(i, x);
		}
		assertEquals(3, counters[1].size());
		for (int i = 5; i <= 7; i++) {
			int x = counters[1].removeFirst();
			assertEquals(i, x);
		}
		assertEquals(3, counters[2].size());
		for (int i = 9; i <= 11; i++) {
			int x = counters[2].removeFirst();
			assertEquals(i, x);
		}
		assertEquals(4, counters[3].size());
		for (int i = 13; i <= 16; i++) {
			int x = counters[3].removeFirst();
			assertEquals(i, x);
		}
		assertEquals(4, counters[4].size());
		for (int i = 17; i <= 20; i++) {
			int x = counters[4].removeFirst();
			assertEquals(i, x);
		}
		assertEquals(3, counters[5].size());
		for (int i = 21; i <= 23; i++) {
			int x = counters[5].removeFirst();
			assertEquals(i, x);
		}
		
		assertEquals(3, special.size());
		assertEquals(4, special.removeFirst());
		assertEquals(8, special.removeFirst());
		assertEquals(12, special.removeFirst());
	}

}
