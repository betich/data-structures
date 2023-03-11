import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class  TestRemoveBetween {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testremoveEmptyRange01() throws Exception {
		// list 1 -> 1

		CDLinkedList lTemp = new CDLinkedList();
		DListIterator dTemp = new DListIterator(lTemp.header);
		lTemp.insert(1, dTemp);
		ZoomaList l = new ZoomaList(lTemp);
		DListIterator d1 = new DListIterator(l.header);
		DListIterator d2 = new DListIterator(l.header);
		d1.next();
		d2.next();

		l.removeBetween(d1, d2, 0);
		assertEquals(1, l.size());
		assertEquals("1", l.printList());

	}

	@Test
	void testremoveEmptyRange02() throws Exception {
		// list 1,2 -> 1,2

		CDLinkedList lTemp = new CDLinkedList();
		DListIterator dTemp = new DListIterator(lTemp.header);
		lTemp.insert(2, dTemp);
		lTemp.insert(1, dTemp);
		ZoomaList l = new ZoomaList(lTemp);
		DListIterator d1 = new DListIterator(l.header);
		DListIterator d2 = new DListIterator(l.header);
		d1.next();
		d2.next();
		d2.next();

		l.removeBetween(d1, d2, 0);
		assertEquals(2, l.size());
		assertEquals("1 2", l.printList());

	}

	@Test
	void testremoveFrontGone() throws Exception {
		// list  1,2,3,4 -> 3,4
		//      ^    ^
        //      d1   d2
		CDLinkedList lTemp = new CDLinkedList();
		DListIterator dTemp = new DListIterator(lTemp.header);
		lTemp.insert(4, dTemp);
		lTemp.insert(3, dTemp);
		lTemp.insert(2, dTemp);
		lTemp.insert(1, dTemp);

		ZoomaList l = new ZoomaList(lTemp);
		DListIterator d1 = new DListIterator(l.header);
		DListIterator d2 = new DListIterator(l.header);

		for (int i = 1; i <= 3; i++) {
			d2.next();
		}
		l.removeBetween(d1, d2, 2);
		assertEquals(2, l.size());
		assertEquals("3 4", l.printList());

	}
	
	@Test
	void testremoveBackGone() throws Exception {
		// list 1,2,3,4 -> 1,2
		//     ^   ^ 
        //     d2  d1
		CDLinkedList lTemp = new CDLinkedList();
		DListIterator dTemp = new DListIterator(lTemp.header);
		lTemp.insert(4, dTemp);
		lTemp.insert(3, dTemp);
		lTemp.insert(2, dTemp);
		lTemp.insert(1, dTemp);

		ZoomaList l = new ZoomaList(lTemp);
		DListIterator d1 = new DListIterator(l.header);
		DListIterator d2 = new DListIterator(l.header);

		for (int i = 1; i <= 2; i++) {
			d1.next();
		}
		l.removeBetween(d1, d2, 3);
		assertEquals(1, l.size());
		assertEquals("1 2", l.printList());

	}
	
	@Test
	void testremoveOneData() throws Exception {
		// list 1,2,3,4,5 -> 1,2,4,5
		//        ^   ^ 
        //        d1  d2
		CDLinkedList lTemp = new CDLinkedList();
		DListIterator dTemp = new DListIterator(lTemp.header);
		lTemp.insert(5, dTemp);
		lTemp.insert(4, dTemp);
		lTemp.insert(3, dTemp);
		lTemp.insert(2, dTemp);
		lTemp.insert(1, dTemp);

		ZoomaList l = new ZoomaList(lTemp);
		DListIterator d1 = new DListIterator(l.header);
		DListIterator d2 = new DListIterator(l.header);

		for (int i = 1; i <= 2; i++) {
			d1.next();
		}
		for (int i = 1; i <= 4; i++) {
			d2.next();
		}
		
		l.removeBetween(d1, d2, 1);
		assertEquals(4, l.size());
		assertEquals("1 2 4 5", l.printList());

	}
	
	@Test
	void testremoveGeneral() throws Exception {
		// list 1,2,3,4,5,6,7 -> 1,2,6,7
		//        ^       ^ 
        //        d1      d2
		CDLinkedList lTemp = new CDLinkedList();
		DListIterator dTemp = new DListIterator(lTemp.header);
		lTemp.insert(7, dTemp);
		lTemp.insert(6, dTemp);
		lTemp.insert(5, dTemp);
		lTemp.insert(4, dTemp);
		lTemp.insert(3, dTemp);
		lTemp.insert(2, dTemp);
		lTemp.insert(1, dTemp);

		ZoomaList l = new ZoomaList(lTemp);
		DListIterator d1 = new DListIterator(l.header);
		DListIterator d2 = new DListIterator(l.header);

		for (int i = 1; i <= 2; i++) {
			d1.next();
		}
		for (int i = 1; i <= 6; i++) {
			d2.next();
		}
		
		l.removeBetween(d1, d2, 3);
		assertEquals(4, l.size());
		assertEquals("1 2 6 7", l.printList());

	}

}
