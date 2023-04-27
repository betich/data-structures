
public class DListNode {
	int data;
	DListNode nextNode, previousNode;

	DListNode(int data) {
		this(data, null, null);
	}

	DListNode(int theElement, DListNode n, DListNode p) {
		data = theElement;
		nextNode = n;
		previousNode = p;
	}

}
