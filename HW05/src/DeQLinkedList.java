

public class DeQLinkedList extends QueueLinkedList implements DeQ {

	@Override
	public int removeLast() throws Exception {
		// TODO Auto-generated method stub
		if (isEmpty())
			throw new EmptyQueueException();
		DListIterator itr = new DListIterator(theList.header);
		itr.previous();
		int data = itr.previous();
		theList.remove(itr);
		return data;
	}
	
	@Override
	public void insertFirst(int data) throws Exception {
		DListIterator itr = new DListIterator(theList.header);
		theList.insert(data, itr);
	}
	
}
