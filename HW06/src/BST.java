
public class BST {
  BSTNode root;
  int size;

  public BST() {
    root = null;
    size = 0;
  }

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

  public Iterator find(int v) {
    BSTNode temp = root;

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
      if (parent == null) {
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
    BSTNode temp = root;

    TreeIterator i = (TreeIterator) find(v);
    if (i == null) { // not found, we can not remove it
      return;
    }

    temp = i.currentNode;
    parent = temp.parent;

    // otherwise, we remove the value.
    size--;
    if (temp.left == null && temp.right == null) {// both subtrees are empty
      if (parent == null) {
        root = null;
      } else if (parent.left == temp) {
        parent.left = null;
      } else {
        parent.right = null;
      }
    } else if (temp.left == null && temp.right != null) {// only right child
      if (parent == null) {
        root = temp.right;
        root.parent = null;
      } else if (parent.right == temp) {
        BSTNode n = temp.right;
        n.parent = parent;
        parent.right = n;
        // temp.right = null;
        // temp.parent = null;
      } else {// parent.left == temp
        BSTNode n = temp.right;
        n.parent = parent;
        parent.left = n;
      }
    } else if (temp.right == null && temp.left != null) {
      if (parent == null) {
        root = temp.left;
        root.parent = null;
      } else if (parent.right == temp) {
        BSTNode n = temp.left;
        n.parent = parent;
        parent.right = n;
      } else {
        BSTNode n = temp.left;
        n.parent = parent;
        parent.left = n;

      }

    } else {// temp has two subtrees
      BSTNode n = temp.right;
      TreeIterator itr = (TreeIterator) (findMin(n));
      BSTNode minInSubtree = itr.currentNode;

      temp.data = minInSubtree.data;

      BSTNode parentOfMin = minInSubtree.parent;
      if (parentOfMin.left == minInSubtree) {
        parentOfMin.left = minInSubtree.right;

      } else { // parentOfMin.right == minInSubtree
        parentOfMin.right = minInSubtree.right;

      }

      if (minInSubtree.right != null) {
        minInSubtree.right.parent = parentOfMin;
      }

    }
  }

  public Iterator findMin() {
    BSTNode temp = root;
    if (temp == null)
      return null;
    while (temp.left != null) {
      temp = temp.left;
    }
    Iterator itr = new TreeIterator(temp);
    return itr;
  }

  public Iterator findMin(BSTNode n) {
    BSTNode temp = n;
    if (temp == null)
      return null;
    while (temp.left != null) {
      temp = temp.left;
    }
    Iterator itr = new TreeIterator(temp);
    return itr;
  }

  public Iterator findMax() {
    BSTNode temp = root;
    if (temp == null)
      return null;
    while (temp.right != null) {
      temp = temp.right;
    }
    Iterator itr = new TreeIterator(temp);
    return itr;
  }

  public Iterator findMax(BSTNode n) {
    BSTNode temp = n;
    if (temp == null)
      return null;
    while (temp.right != null) {
      temp = temp.right;
    }
    Iterator itr = new TreeIterator(temp);
    return itr;
  }

  public void addLeftSubTreeToMin(BST subtree) {
    // This method tries to add an entire “subtree” as a left subtree of the left
    // most node in our tree, changing our tree.

    // if root is empty, replace root original tree with subtree completely
    if (this.root == null) {
      this.root = subtree.root;
      this.size = subtree.size;
      return;
    }

    // if subtree is empty, do nothing
    if (subtree.root == null) {
      return;
    }

    // * Check if after the addition of “subtree”, the tree will still be a BST
    BSTNode leftMostNode = ((TreeIterator) findMin()).currentNode;

    if (subtree.root.data >= leftMostNode.data) {
      return;
    }
    else if (subtree.root.left != null && subtree.root.left.data >= leftMostNode.data) {
      return;
    }
    else if (subtree.root.right != null && subtree.root.right.data >= leftMostNode.data) {
      return;
    }

    else {
      // assuming that the subtree always has 3 nodes or none
      TreeIterator rootFound = ((TreeIterator) find(subtree.root.data));
      TreeIterator leftFound = ((TreeIterator) find(subtree.root.left.data));
      TreeIterator rightFound = ((TreeIterator) find(subtree.root.right.data));

      if (rootFound != null || leftFound != null || rightFound != null) {
        return;
      } else {
        leftMostNode.left = subtree.root;
        subtree.root.parent = leftMostNode;
        size += subtree.size;
      }
    }
  }

  public static void main(String[] args) throws Exception {
    // Printing example.
    // You can print how the tree looks to help with debugging.

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

    BTreePrinter.printNode(t0.root);
    BTreePrinter.printNode(t1.root);

    t0.addLeftSubTreeToMin(t1);

    BTreePrinter.printNode(t0.root);
  }

}
