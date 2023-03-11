
import java.util.NoSuchElementException;

public class DListIterator implements Iterator {
	DListNode currentNode; // interested position

	DListIterator(DListNode theNode) {
		currentNode = theNode;
	}

	@Override
	public boolean hasNext() { // always true for circular list.
		return currentNode.nextNode != null;
	}
	
	public boolean hasPrevious() { // always true for circular list.
		return currentNode.previousNode != null;
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

	public int previous() throws Exception{
		if (!hasPrevious())
			throw new NoSuchElementException();
		int data = currentNode.data;
		currentNode = currentNode.previousNode;
		return data;
	}
	
	
	@Override
	public void set(int value) {
		currentNode.data = value;
	}

}
