import java.util.ArrayList;
import java.util.Arrays;

public class BankQueue { // must work for any implementation of DeQ
  DeQ[] counters;
  DeQ special;

  public BankQueue(DeQ[] counters, DeQ special) {
    super();
    this.counters = counters;
    this.special = special;
  }

  // Write this method
  public void distribute() throws Exception {
    // 1. Calculate needed queue length
    float peopleCount = Arrays.stream(counters).reduce(0, (curr, acc) -> curr + acc.size(), Integer::sum)
        + special.size(); // get peopleCount from summing up every person waiting in the queue (this can
                          // also be done using a for loop)
    float queueCount = counters.length + 1;

    int neededQueueLength = Math.round(peopleCount / queueCount);

    // 2. Maintain the first “needed queue length” number of data in its original
    // sequence.
    for (DeQ c : counters) {
      // move the "needed queue length" th element to the special queue
      DeQ temp = new DeQLinkedList();

      while (c.size() > neededQueueLength) {
        temp.insertFirst(c.removeLast());
      }

      // move the elements to the special queue
      while (!temp.isEmpty() && special.size() < neededQueueLength) {
        special.insertLast(temp.removeFirst());
      }

      // move the elements back to the original queue
      while (!temp.isEmpty()) {
        c.insertLast(temp.removeFirst());
      }

      // if the special queue is full, break
      if (special.size() == neededQueueLength)
        break;
    }

    // if special queue is empty, add the last person of the last queue to the
    // special queue
    if (special.isEmpty()) {
      DeQ lastQueue = counters[counters.length - 1];
      special.insertLast(lastQueue.removeLast());
    }
  }

}
