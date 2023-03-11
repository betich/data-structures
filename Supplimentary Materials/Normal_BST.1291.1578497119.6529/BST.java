package book_BST;

public class BST {
	BSTNode root;
	int size;

	public BST(BSTNode root, int size) {
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
		BSTNode temp = root;
		if(temp == null)
			return null;
		while (temp.left != null) {
			temp = temp.left;
		}
		Iterator itr = new TreeIterator(temp);
		return itr;
	}

	public Iterator findMax() {
		BSTNode temp = root;
		if(temp == null)
			return null;
		while (temp.right != null) {
			temp = temp.right;
		}
		Iterator itr = new TreeIterator(temp);
		return itr;
	}

	public Iterator find(int v) {
		BSTNode temp = root;
		if (v == temp.data)
			return new TreeIterator(temp);

		while (temp != null && temp.data != v) {
			if (v < temp.data) {
				temp = temp.left;
			} else {
				temp = temp.right;
			}
		}
		if (temp == null) // not found
			return null;
		return new TreeIterator(temp);
	}

	public Iterator insert(int v) {
		BSTNode parent = null;
		BSTNode temp = root;

		// This first part is almost the same as find,
		// but it has an extra pointer called parent.
		while (temp != null && temp.data != v) {
			if (v < temp.data) {
				parent = temp;
				temp = temp.left;
				
			} else {
				parent = temp;
				temp = temp.right;
				
			}
		}
	

		if (temp == null) { 
			BSTNode n = new BSTNode(v, null, null, parent);
			if(parent == null){
				root = n;
			} else if (v < parent.data) {
				parent.left = n;
			} else {
				parent.right = n;
			}
			size++;
			return new TreeIterator(n);
		} else {
			// we do nothing since
			// we don't want to add duplicated data.
			return null;
		}

	}

	public void remove(int v) {
		BSTNode parent = null;
		BSTNode n = root;

		TreeIterator i = (TreeIterator) find(v);
		if (i == null) { // not found, we can not remove it
			return;
		}

		n = i.currentNode;
		parent = n.parent;

		// otherwise, we remove the value.
		size--;
		if (n.left == null && n.right == null) {// both subtrees are empty
			if (parent == null) { 
				root = null;
			} else if (parent.left == n) {
				parent.left = null;
				n.parent = null;
			} else {
				parent.right = null;
				n.parent = null;
			}
		} else if (n.left == null && n.right != null) {// only right child
			if (parent == null) {
				root = n.right;
				root.parent = null;
				n.right = null;
			} else if (parent.right == n) {
				BSTNode q = n.right;
				q.parent = parent;
				parent.right = q;
				n.parent = null;
				n.right = null;
			} else {// parent.left == n
				BSTNode q = n.right;
				q.parent = parent;
				parent.left = q;
				n.parent = null;
				n.right = null;
			}
		} else if (n.right == null && n.left != null) {
			if (parent == null) {
				root = n.left;
				root.parent = null;
				n.left = null;
			} else if (parent.right == n) {
				BSTNode q = n.left;
				q.parent = parent;
				parent.right = q;
				n.parent = null;
				n.left = null;
			} else {
				BSTNode q = n.left;
				q.parent = parent;
				parent.left = q;
				n.parent = null;
				n.left = null;
			}

		} else {// n has two subtrees
			BSTNode q = n.right;
			TreeIterator itr = findMin(q);
			BSTNode minInSubtree = itr.currentNode;

			n.data = minInSubtree.data;

			BSTNode parentOfMin = minInSubtree.parent;
			if (parentOfMin.left == minInSubtree) {
				parentOfMin.left = minInSubtree.right;

			} else { // parentOfMin.right == minInSubtree
				parentOfMin.right = minInSubtree.right;

			}

			if (minInSubtree.right != null) {
				minInSubtree.right.parent = parentOfMin;
				minInSubtree.right = null;
			}
			minInSubtree.parent = null;

		}
	}

	private TreeIterator findMin(BSTNode n) {
		BSTNode temp = n;
		while (temp.left != null) {
			temp = temp.left;
		}
		TreeIterator itr = new TreeIterator(temp);
		return itr;
	}

	public static void main(String[] args) {
		BSTNode r = new BSTNode(7);
		BST t = new BST(r, 1);
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
		t = new BST(r, 1);
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
