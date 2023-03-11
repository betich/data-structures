class ListNode {
	int data;
	ListNode nextNode;

	// Constructors
	ListNode(int theElement) {
		this(theElement, null);
	}

	ListNode(int theElement, ListNode n) {
		data = theElement;
		nextNode = n;
	}

}
