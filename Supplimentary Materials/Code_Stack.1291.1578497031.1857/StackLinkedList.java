
public class StackLinkedList implements MyStack {
	private CDLinkedList theList;
	
	public StackLinkedList(){ // create an empty stack
		theList = new CDLinkedList();
	}
	
	public StackLinkedList(CDLinkedList l) throws Exception {
		super();
		DListIterator iparam = new DListIterator(l.header);
		DListIterator ithis = new DListIterator(theList.header);
		while (iparam.hasNext()) {
			int v = iparam.next();
			if (iparam.currentNode == l.header)
				return;
			theList.insert(v, ithis);
			ithis.next();
		}	
	}
	
	public CDLinkedList getTheList() {
		return theList;
	}

	public void setTheList(CDLinkedList theList) {
		this.theList = theList;
	}

	public boolean isEmpty(){
		return theList.isEmpty();
	}
	
	public boolean isFull(){
		return false;
	}
	
	public void makeEmpty(){
		theList.makeEmpty();
	}
	
	public int top() throws MyStackException{
		if(isEmpty())
			throw new MyStackException();
		return theList.header.nextNode.data;
	}
	
	public void pop() throws MyStackException{
		if(isEmpty())
			throw new MyStackException();
		Iterator itr = new DListIterator(theList.header);
		theList.remove(itr);
	}
	
	public void push(int data) throws Exception{
		Iterator itr = new DListIterator(theList.header);
		theList.insert(data, itr);
	}

	
}
