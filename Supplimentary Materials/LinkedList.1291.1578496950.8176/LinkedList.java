
public class LinkedList {
	ListNode header;
	static int HEADER_MARKER = -9999999;

	public LinkedList() {
		header = new ListNode(HEADER_MARKER);
	}

	public int find(int value) throws Exception {
		Iterator itr = new ListIterator(header);
		int index = -1;
		while (itr.hasNext()) {
			int v = itr.next();
			index++;
			if (v == value)
				return index; // return the position of value.
		}
		return -1; // return -1 if the value is not in the list.
	}

	public int findKth(int kthPosition) throws Exception {
		// Exit the method if the position is
		// less than the first possible
		// position, throwing exception in the process.
		if (kthPosition < 0)
			throw new Exception();

		Iterator itr = new ListIterator(header);
		int index = -1;
		while (itr.hasNext()) {
			int v = itr.next();
			index++;
			if (index == kthPosition)
				return v;
		}
		throw new Exception();
	}

	public void insert(int value, Iterator p) throws Exception {
		if (p == null || !(p instanceof ListIterator))
			throw new Exception();
		ListIterator p2 = (ListIterator) p;
		if (p2.currentNode == null)
			throw new Exception();
		ListNode n = new ListNode(value, p2.currentNode.nextNode);
		p2.currentNode.nextNode = n;
	}

	public Iterator findPrevious(int value) throws Exception {
		Iterator itr1 = new ListIterator(header);
		Iterator itr2 = new ListIterator(header);
		if (!itr2.hasNext())
			return null;
		int currentData = itr2.next();
		while (currentData != value && itr2.hasNext()) {
			currentData = itr2.next();
			itr1.next();
		}
		if (currentData == value)
			return itr1;
		return null;
	}

	public void remove(Iterator p) {
		if (p == null || !(p instanceof ListIterator))
			return;
		ListIterator p2 = (ListIterator) p;
		if (p2.currentNode == null || p2.currentNode.nextNode == null)
			return;
		p2.currentNode.nextNode = p2.currentNode.nextNode.nextNode;
	}

	public void remove(int value) throws Exception {
		Iterator p = findPrevious(value);
		if (p == null)
			return;
		remove(p);
	}

	public boolean isEmpty() {
		return header.nextNode == null;
	}

	public void makeEmpty() {
		header.nextNode = null;
	}

	public int head() throws Exception {
		if (isEmpty())
			throw new Exception();
		return header.nextNode.data;

	}

	public LinkedList tail() throws Exception {
		if (isEmpty())
			throw new Exception();

		// Now create a copy of the list
		// so that the original does not change.
		// Copy everything except the first data
		// to the new list.
		LinkedList list2 = new LinkedList();
		Iterator p1 = new ListIterator(header.nextNode);
		Iterator p2 = new ListIterator(list2.header);
		while (p1.hasNext()) {
			int data = p1.next();
			list2.insert(data, p2);
			p2.next();
		}
		return list2;
	}

	public void append(LinkedList list2) throws Exception {
		Iterator p1 = new ListIterator(header);
		Iterator p2 = new ListIterator(list2.header);

		// move iterator to the end of our list.
		while (p1.hasNext())
			p1.next();

		// then copy everything from list2 to our list.
		while (p2.hasNext()) {
			insert(p2.next(), p1);
			p1.next();
		}
	}

}
