package book_BST;

public class BSTRecursive {

	BSTNode root;
	int size;

	public BSTRecursive(BSTNode root, int size) {
		this.root = root;
		this.size = size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void makeEmpty() {
		root = null;
		size = 0;
	}

	public Iterator findMin() {
		return findMin(root);
	}

	public Iterator findMin(BSTNode n) {
		if (n == null)
			return null;
		if (n.left == null) {
			Iterator itr = new TreeIterator(n);
			return itr;
		}
		return findMin(n.left);
	}

	public Iterator findMax() {
		return findMax(root);
	}

	public Iterator findMax(BSTNode n) {
		if (n == null)
			return null;
		if (n.right == null) {
			Iterator itr = new TreeIterator(n);
			return itr;
		}
		return findMax(n.right);
	}

	public Iterator find(int v) {
		return find(v, root);
	}

	public Iterator find(int v, BSTNode n) {
		if (n == null)
			return null;
		if (v == n.data)
			return new TreeIterator(n);
		if (v < n.data)
			return find(v, n.left);
		else
			return find(v, n.right);
	}

	public BSTNode insert(int v) {
		return insert(v, root, null);
	}

	// return the node n after v was added into the tree
	public BSTNode insert(int v, BSTNode n, BSTNode parent) {
		if (n == null) {
			n = new BSTNode(v, null, null, parent);
			size++;
		} else if (v < n.data) {
			n.left = insert(v, n.left, n);
		} else if (v > n.data) {
			n.right = insert(v, n.right, n);
		}
		return n;
	}

	public BSTNode remove(int v) {
		return remove(v, root, null);
	}

	// return the node n after v was removed from the tree
	public BSTNode remove(int v, BSTNode n, BSTNode parent) {
		if (n == null)
			; // do nothing, there is nothing to be removed
		else if (v < n.data) {
			n.left = remove(v, n.left, n);
		} else if (v > n.data) {
			n.right = remove(v, n.right, n);
		} else {
			if (n.left == null && n.right == null) {
				n.parent = null; // disconnect from above
				n = null; // disconnect from below
				size--;
			} else if (n.left != null && n.right == null) {
				BSTNode n2 = n.left;
				n2.parent = parent;
				n.parent = null; // disconnect from above
				n.left = null; // disconnect from below
				n = n2; // change to the node below
						// to prepare for connection from parent
				size--;
			} else if (n.right != null && n.left == null) {
				BSTNode n2 = n.right;
				n2.parent = parent;
				n.parent = null; // disconnect from above
				n.right = null; // disconnect from below
				n = n2; // change to the node below
						// to prepare for connection from parent
				size--;
			} else {
				TreeIterator i = (TreeIterator) findMin(n.right);
				int minInRightSubtree = i.currentNode.data;
				n.data = minInRightSubtree;
				n.right = remove(minInRightSubtree, n.right, n);
			}
		}
		return n;
	}

	public static void main(String[] args) {
		BSTRecursive t2 = new BSTRecursive(null, 0);
		t2.insert(1);
		t2.insert(5);
		BTreePrinter.printNode(t2.root);

		BSTNode r = new BSTNode(7);
		BSTRecursive t = new BSTRecursive(r, 1);
		t.insert(3);
		t.insert(1);
		t.insert(11);
		t.insert(2);
		t.insert(5);
		t.insert(9);
		t.insert(6);

		t.insert(0);
		t.insert(8);
		t.insert(10);
		t.insert(12);
		t.insert(13);
		t.insert(14);
		t.insert(4);
		BTreePrinter.printNode(t.root);

		System.out.println("Now, remove 2.");
		t.remove(2);
		BTreePrinter.printNode(t.root);

		System.out.println("Now, remove 8.");
		t.remove(8);
		BTreePrinter.printNode(t.root);

		System.out.println("Now, remove 1.");
		t.remove(1);
		BTreePrinter.printNode(t.root);
		TreeIterator itr = (TreeIterator) t.find(0);
		if (itr.currentNode.parent.data == 3) {
			System.out.println("Parent of 0 is correct.");
		} else {
			System.out.println("Parent of 0 is NOT correct.");
		}

		System.out.println("Now, remove 12.");
		t.remove(12);
		BTreePrinter.printNode(t.root);
		itr = (TreeIterator) t.find(13);
		if (itr.currentNode.parent.data == 11) {
			System.out.println("Parent of 13 is correct.");
		} else {
			System.out.println("Parent of 13 is NOT correct.");
		}

		System.out.println("Now, remove 0 and 3.");
		t.remove(0);
		t.remove(3);
		BTreePrinter.printNode(t.root);
		itr = (TreeIterator) t.find(5);
		if (itr.currentNode.parent.data == 7) {
			System.out.println("Parent of 5 is correct.");
		} else {
			System.out.println("Parent of 5 is NOT correct.");
		}

		System.out.println("Now, remove 6, insert 1,2,3 and remove 5.");
		t.remove(6);
		t.insert(2);
		t.insert(1);
		t.insert(3);
		t.remove(5);
		BTreePrinter.printNode(t.root);
		itr = (TreeIterator) t.find(4);
		if (itr.currentNode.parent.data == 7) {
			System.out.println("Parent of 4 is correct.");
		} else {
			System.out.println("Parent of 4 is NOT correct.");
		}

		System.out.println("Reset the tree");
		r = new BSTNode(7);
		t = new BSTRecursive(r, 1);
		t.insert(3);
		t.insert(1);
		t.insert(11);
		t.insert(2);
		t.insert(5);
		t.insert(9);
		t.insert(6);

		t.insert(0);
		t.insert(8);
		t.insert(10);
		t.insert(12);
		t.insert(13);
		t.insert(14);
		t.insert(4);
		BTreePrinter.printNode(t.root);

		System.out.println("Now, remove 3.");
		t.remove(3);
		BTreePrinter.printNode(t.root);
		itr = (TreeIterator) t.find(4);
		if (itr.currentNode.parent.data == 7) {
			System.out.println("Parent of 4 is correct.");
		} else {
			System.out.println("Parent of 4 is NOT correct.");
		}
		itr = (TreeIterator) t.find(5);
		if (itr.currentNode.parent.data == 4) {
			System.out.println("Parent of 5 is correct.");
		} else {
			System.out.println("Parent of 5 is NOT correct.");
		}

		System.out.println("Now, remove 9.");
		t.remove(9);
		BTreePrinter.printNode(t.root);
		itr = (TreeIterator) t.find(10);
		if (itr.currentNode.parent.data == 11) {
			System.out.println("Parent of 10 is correct.");
		} else {
			System.out.println("Parent of 10 is NOT correct.");
		}
		itr = (TreeIterator) t.find(8);
		if (itr.currentNode.parent.data == 10) {
			System.out.println("Parent of 8 is correct.");
		} else {
			System.out.println("Parent of 8 is NOT correct.");
		}

		System.out.println("Now, remove 11.");
		t.remove(11);
		BTreePrinter.printNode(t.root);
		itr = (TreeIterator) t.find(12);
		if (itr.currentNode.parent.data == 7) {
			System.out.println("Parent of 12 is correct.");
		} else {
			System.out.println("Parent of 12 is NOT correct.");
		}
		itr = (TreeIterator) t.find(10);
		if (itr.currentNode.parent.data == 12) {
			System.out.println("Parent of 10 is correct.");
		} else {
			System.out.println("Parent of 10 is NOT correct.");
		}

		System.out.println("Now, remove 7.");
		t.remove(7);
		BTreePrinter.printNode(t.root);
		itr = (TreeIterator) t.find(4);
		if (itr.currentNode.parent.data == 8) {
			System.out.println("Parent of 4 is correct.");
		} else {
			System.out.println("Parent of 4 is NOT correct.");
		}
		itr = (TreeIterator) t.find(12);
		if (itr.currentNode.parent.data == 8) {
			System.out.println("Parent of 12 is correct.");
		} else {
			System.out.println("Parent of 12 is NOT correct.");
		}

	}

}
