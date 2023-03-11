
public class ZoomaList extends CDLinkedList {
	int score = 0;

	public ZoomaList() {
		super();
	}

	public ZoomaList(CDLinkedList l) {
		header = l.header;
		size = l.size;
	}

	public void insert(int value, Iterator p) throws Exception {
//		System.out.println("=== Begin Insert ===");
		super.insert(value, p);

		// <- leftIterator (insertedNode) rightIterator ->
		DListNode insertedNode = ((DListIterator) p).currentNode.nextNode;
		DListIterator leftIterator = new DListIterator(insertedNode.previousNode);
		DListIterator rightIterator = new DListIterator(insertedNode.nextNode);

		// detect collision
		int foundNodes = 1;
		int checkValue = value;

		while (true) {
			while (leftIterator.currentNode.data == checkValue) {
				foundNodes++;
				leftIterator.previous();
			}

			while (rightIterator.currentNode.data == checkValue) {
				foundNodes++;
				rightIterator.next();
			}

			if (foundNodes < 3) break;
			else {
//				System.out.println("Removing From: " + super.printList());
//				System.out.println("Start, End, Inc: " + leftIterator.currentNode.data + ", " + rightIterator.currentNode.data + ", " + foundNodes);

				this.removeBetween(leftIterator, rightIterator, foundNodes);
				score += foundNodes;

				if (leftIterator.currentNode.data == rightIterator.currentNode.data) {
					checkValue = leftIterator.currentNode.data;
					foundNodes = 0;
				} else break;
			}
		}

//		System.out.println("=== End Insert ===");
	}

	
	public void removeBetween(DListIterator left, DListIterator right, int inc) {
//		System.out.println("size: " + size);
		if (left.currentNode.equals(right.currentNode) || left.currentNode.nextNode.equals(right.currentNode)) {
			return;
		}

		DListNode leftNode = left.currentNode;
		DListNode rightNode = right.currentNode;

		left.currentNode.nextNode = rightNode;
		right.currentNode.previousNode = leftNode;

//		System.out.println("inc: " + inc);

		size -= inc;
	}

}
