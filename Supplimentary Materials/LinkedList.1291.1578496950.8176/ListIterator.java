import java.util.NoSuchElementException;

public class ListIterator implements Iterator {
	ListNode currentNode; // interested position

	ListIterator(ListNode theNode) {
		currentNode = theNode;
	}

	@Override
	public boolean hasNext() {
		return currentNode.nextNode != null;
	}

	@Override
	public int next() throws Exception {
		// Throw exception if the next data
		// does not exist.
		if (!hasNext())
			throw new NoSuchElementException();
		currentNode = currentNode.nextNode;
		return currentNode.data;

	}

	@Override
	public void set(int value) {
		currentNode.data = value;
	}

}
