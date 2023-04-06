import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestHash {

	DoubleHashing hTable;

	@BeforeEach
	void setUp() throws Exception {
		hTable = new DoubleHashing();

	}

	@Test
	void testNextAndHasNextEmptyTable() throws Exception { //1 point
		HashIterator i = new HashIterator(hTable);
		assertFalse(i.hasNext());
		Assertions.assertThrows(Exception.class, () -> {
			i.next();
		});
	}

	@Test
	void testPreviousAndHasPreviousEmptyTable() throws Exception { //1 point
		HashIterator i = new HashIterator(hTable);
		assertFalse(i.hasPrevious());
		Assertions.assertThrows(Exception.class, () -> {
			i.previous();
		});
	}

	@Test
	void testNextAndHasNext() throws Exception {  //4 points
		hTable.add(5);
		hTable.add(16);
		hTable.add(27);
		hTable.add(9);
		hTable.remove(16);
		hTable.add(20);
		hTable.add(16);

		HashIterator i = new HashIterator(hTable);
		assertEquals(16, hTable.array[i.currentPos]);

		assertTrue(i.hasNext());
		assertEquals(5, i.next());

		assertTrue(i.hasNext());
		assertEquals(27, i.next());

		assertTrue(i.hasNext());
		assertEquals(20, i.next());

		assertTrue(i.hasNext());
		assertEquals(9, i.next());

		assertFalse(i.hasNext());
		Assertions.assertThrows(Exception.class, () -> {
			i.next();
		});

	}

	@Test
	void testPreviousAndHasPrevious() throws Exception { //4 points
		hTable.add(5);
		hTable.add(16);
		hTable.add(27);
		hTable.add(9);
		hTable.remove(16);
		hTable.add(20);
		hTable.add(16);

		HashIterator i = new HashIterator(hTable);
		i.next();
		i.next();
		i.next();
		i.next();

		assertEquals(9, hTable.array[i.currentPos]);
		assertTrue(i.hasPrevious());
		assertEquals(9, i.previous());

		assertTrue(i.hasPrevious());
		assertEquals(20, i.previous());

		assertTrue(i.hasPrevious());
		assertEquals(27, i.previous());

		assertTrue(i.hasPrevious());
		assertEquals(5, i.previous());

		assertFalse(i.hasPrevious());
		Assertions.assertThrows(Exception.class, () -> {
			i.previous();
		});

	}

}
