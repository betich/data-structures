

public class QueueLinkedList implements MyQueue {
	CDLinkedList theList;

	public QueueLinkedList() {
		this(new CDLinkedList());
	}

	public QueueLinkedList(CDLinkedList theList) {
		this.theList = theList;
	}

	@Override
	public int front() throws Exception {
		if (isEmpty())
			throw new EmptyQueueException();
		return theList.findKth(0);
	}

	@Override
	public int back() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException();
		return theList.header.previousNode.data;
	}

	@Override
	public int removeFirst() throws Exception {
		if (isEmpty())
			throw new EmptyQueueException();
		DListIterator itr = new DListIterator(theList.header);
		int data = itr.next();
		theList.removeAt(itr);
		return data;
	}

	@Override
	public void insertLast(int data) throws Exception {
		DListIterator itr = new DListIterator(theList.header);
		itr.previous();
		theList.insert(data, itr);
	}

	public boolean isEmpty() {
		// use the method of linked list if possible.
		return theList.isEmpty();
	}

	public boolean isFull() {
		// use the method of linked list if possible.
		return theList.isFull();
	}

	public int size() {
		return theList.size();
	}

}
