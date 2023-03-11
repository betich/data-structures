import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class BSTTest {

	@Test
	void testAddEmptySubTree() { // 1 mark
		BST t0 = new BST();
		BST t1 = new BST();

		t0.addLeftSubTreeToMin(t1);
		assertEquals(null, t0.root);
		assertEquals(0, t0.size);

		t0.insert(9);
		t0.insert(7);
		t0.insert(11);
		t0.insert(8);
		t0.insert(10);
		t0.insert(12);

		t0.addLeftSubTreeToMin(t1);
		assertEquals(6, t0.size);
		assertEquals(9, t0.root.data);
		assertEquals(7, t0.root.left.data);
		assertEquals(11, t0.root.right.data);
		assertEquals(8, t0.root.left.right.data);
		assertEquals(10, t0.root.right.left.data);
		assertEquals(12, t0.root.right.right.data);

	}

	@Test
	void testAddToEmptyTree() { // 2 mark
		BST t0 = new BST();
		BST t1 = new BST();

		t1.insert(2);
		t1.insert(1);
		t1.insert(5);

		t0.addLeftSubTreeToMin(t1);
		assertEquals(3, t0.size);
		assertEquals(2, t0.root.data);
		assertEquals(1, t0.root.left.data);
		assertEquals(5, t0.root.right.data);

	}

	@Test
	void testAddSuccess() { // 3 mark
		BST t0 = new BST();
		BST t1 = new BST();

		t0.insert(9);
		t0.insert(7);
		t0.insert(11);
		t0.insert(8);
		t0.insert(10);
		t0.insert(12);

		t1.insert(3);
		t1.insert(1);
		t1.insert(6);

		t0.addLeftSubTreeToMin(t1);
		assertEquals(9, t0.size);
		assertEquals(9, t0.root.data);
		assertEquals(7, t0.root.left.data);
		assertEquals(11, t0.root.right.data);
		assertEquals(8, t0.root.left.right.data);
		assertEquals(10, t0.root.right.left.data);
		assertEquals(12, t0.root.right.right.data);

		assertEquals(3, t0.root.left.left.data);
		assertEquals(1, t0.root.left.left.left.data);
		assertEquals(6, t0.root.left.left.right.data);

	}

	@Test
	void testAddFail() { // 2 mark
		BST t0 = new BST();
		BST t1 = new BST();

		t0.insert(9);
		t0.insert(7);
		t0.insert(11);
		t0.insert(8);
		t0.insert(10);
		t0.insert(12);

		t1.insert(6);
		t1.insert(1);
		t1.insert(9);

		t0.addLeftSubTreeToMin(t1);
		assertEquals(6, t0.size);
		assertEquals(9, t0.root.data);
		assertEquals(7, t0.root.left.data);
		assertEquals(11, t0.root.right.data);
		assertEquals(8, t0.root.left.right.data);
		assertEquals(10, t0.root.right.left.data);
		assertEquals(12, t0.root.right.right.data);

	}
	
	@Test
	void testNoLoop() throws FileNotFoundException { // 2 mark
		File f = new File("src/BST.java");
		Scanner s = new Scanner(f);

		boolean foundLoop = false;
		String line = "";
		while (s.hasNextLine()) {
			line = s.nextLine();
			if (line.contains("addLeftSubTreeToMin")) {
				break;
			}
		}

		while (s.hasNextLine()) {
			if (line.contains("for") || line.contains("while")) {
				foundLoop = true;
				break;
			}
			line = s.nextLine();
		}
		s.close();
		assertFalse(foundLoop);

	}

}
