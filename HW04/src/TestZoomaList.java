import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestZoomaList {


	@Test
	void testInsertEmptyList() throws Exception {
		ZoomaList l = new ZoomaList();
		DListIterator d = new DListIterator(l.header);
		
		l.insert(5,d);
		
		assertEquals(1,l.size());
		assertEquals(5,l.header.nextNode.data);
		
	}
	
	@Test
	void testInsertOneElementList() throws Exception {
		ZoomaList l = new ZoomaList();
		DListIterator d = new DListIterator(l.header);
		
		l.insert(5,d);
		l.insert(5,d);
		assertEquals(2,l.size());
		assertEquals(5,l.header.nextNode.data);
		assertEquals(5,l.header.previousNode.data);
	}
	
	@Test
	void testInsertScoreOnce_01() throws Exception {
		ZoomaList l = new ZoomaList();
		DListIterator d = new DListIterator(l.header);
		l.insert(1,d); //list 4,3,5,5,2,1 -> 4,3,(5),5,5,2,1 -> 4,3,2,1
		l.insert(2,d);
		l.insert(5,d);
		l.insert(5,d);
		l.insert(3,d);
		l.insert(4,d);
		
		d.next();
		d.next();
		
		l.insert(5,d);
		assertEquals(4,l.size());
		assertEquals(3,l.score);
		assertEquals("4 3 2 1", l.printList()); 
	}
	
	@Test
	void testInsertScoreOnce_02() throws Exception {
		CDLinkedList l2 = new CDLinkedList();
		DListIterator d2 = new DListIterator(l2.header);
		l2.insert(1,d2); //list 4,3,5,5,5,1 -> 4,3,5,(5),5,5,1 -> 4,3,1
		l2.insert(5,d2);
		l2.insert(5,d2);
		l2.insert(5,d2);
		l2.insert(3,d2);
		l2.insert(4,d2);
		
		ZoomaList l = new ZoomaList(l2);
		DListIterator d = new DListIterator(l.header);
		
		d.next();
		d.next();
		d.next();
		l.insert(5,d);
		assertEquals(3,l.size());
		assertEquals(4,l.score);
		assertEquals("4 3 1", l.printList()); 
		
	}
	
	@Test
	void testInsertScoreFrontGone() throws Exception {
		CDLinkedList l2 = new CDLinkedList();
		DListIterator d2 = new DListIterator(l2.header);
		l2.insert(4,d2); //list 3,3,5,5,3,4,4,4 -> 3,3,5,5,(5),3,4,4,4 -> 4,4,4
		l2.insert(4,d2);
		l2.insert(4,d2);
		l2.insert(3,d2);
		l2.insert(5,d2);
		l2.insert(5,d2);
		l2.insert(3,d2);
		l2.insert(3,d2);
		
		ZoomaList l = new ZoomaList(l2);
		DListIterator d = new DListIterator(l.header);
		
		d.next();
		d.next();
		d.next();
		d.next();
		
		l.insert(5,d);
		assertEquals(3,l.size());
		assertEquals(6,l.score);
		assertEquals("4 4 4", l.printList());
		
	}
	
	@Test
	void testInsertScoreBackGone() throws Exception {
		CDLinkedList l2 = new CDLinkedList();
		DListIterator d2 = new DListIterator(l2.header);
		l2.insert(3,d2); //list 4,4,4,3,3,5,5,3 -> 4,4,4,3,3,(5),5,5,3 -> 4,4,4
		l2.insert(5,d2);
		l2.insert(5,d2);
		l2.insert(3,d2);
		l2.insert(3,d2);
		l2.insert(4,d2);
		l2.insert(4,d2);
		l2.insert(4,d2);
		
		ZoomaList l = new ZoomaList(l2);
		DListIterator d = new DListIterator(l.header);
		
		d.next();
		d.next();
		d.next();
		d.next();
		d.next();
		
		l.insert(5,d);
		assertEquals(3,l.size());
		assertEquals(6,l.score);
		assertEquals("4 4 4", l.printList());
		
	}
	
	@Test
	void testInsertScoreNoRemove() throws Exception {
		CDLinkedList l2 = new CDLinkedList();
		DListIterator d2 = new DListIterator(l2.header);
		l2.insert(3,d2); //list 4,4,4,3,3,5,5,3 -> 4,4,4,3,3,(4),5,5,3 
		l2.insert(5,d2);
		l2.insert(5,d2);
		l2.insert(3,d2);
		l2.insert(3,d2);
		l2.insert(4,d2);
		l2.insert(4,d2);
		l2.insert(4,d2);
		
		ZoomaList l = new ZoomaList(l2);
		DListIterator d = new DListIterator(l.header);
		
		d.next();
		d.next();
		d.next();
		d.next();
		d.next();
		
		l.insert(4,d);
		assertEquals(9,l.size());
		assertEquals(0,l.score);
		assertEquals("4 4 4 3 3 4 5 5 3", l.printList());
		
	}
	
	@Test
	void testInsertScoreGeneral() throws Exception {
		CDLinkedList l2 = new CDLinkedList();
		DListIterator d2 = new DListIterator(l2.header);
		l2.insert(2,d2); //list -> 1,6,6,4,3,3,5,5,3,3,4,4,6,6,2 
		l2.insert(6,d2); //     -> 1,6,6,4,3,3,5,(5),5,3,3,4,4,6,6,2
		l2.insert(6,d2); //     -> 1,2
		l2.insert(4,d2);
		l2.insert(4,d2);
		l2.insert(3,d2);
		l2.insert(3,d2);
		l2.insert(5,d2);
		l2.insert(5,d2);
		l2.insert(3,d2);
		l2.insert(3,d2);
		l2.insert(4,d2);
		l2.insert(6,d2);
		l2.insert(6,d2);
		l2.insert(1,d2);
		
		ZoomaList l = new ZoomaList(l2);
		DListIterator d = new DListIterator(l.header);
		
		for(int i =1;i<=7;i++)
			d.next();
		
		
		l.insert(5,d);
		assertEquals(2,l.size());
		assertEquals(14,l.score);
		assertEquals("1 2", l.printList());
		
	}

}
