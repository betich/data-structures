public class HashIterator implements Iterator {

    OpenAddressing h; // the associated hash table
    int currentPos;   // position in the table's array that is currently marked.


    //Create an iterator that marks the leftmost actual data in the hash table.
    //Assume actual data are not 0 and DELETED.
    //If there are no actual data in the table, set currentPos to -1.
    public HashIterator(OpenAddressing o) {
        h = o;
        int i = 0;
        for (; i < o.array.length; i++) {
            if (o.array[i] != 0 && o.array[i] != OpenAddressing.DELETED) {
                currentPos = i;
                break;
            }
        }
        if (i >= o.array.length) {
            currentPos = -1;
        }
    }

    @Override
    public boolean hasNext() {
        if (currentPos == -1 && currentPos == h.array.length-1) return false;

        int tempPosition = currentPos + 1;
        while (tempPosition < h.array.length) {
            if (h.array[tempPosition] != 0 && h.array[tempPosition] != DoubleHashing.DELETED) {
                return true;
            }
            tempPosition++;
        }

        return false;
    }

    @Override
    public boolean hasPrevious() {
        if (currentPos == -1 && currentPos == 0) return false;

        int tempPosition = currentPos - 1;
        while (tempPosition >= 0) {
            if (h.array[tempPosition] != 0 && h.array[tempPosition] != DoubleHashing.DELETED) {
                return true;
            }
            tempPosition--;
        }
        return false;
    }

    @Override
    public int next() throws Exception {
        if (hasNext()) {
            // lookup until you find a value
            int tempPos = currentPos + 1;
            while (tempPos < h.array.length) {
                if (h.array[tempPos] != 0 && h.array[tempPos] != DoubleHashing.DELETED) {
                    currentPos = tempPos;
                    return h.array[currentPos];
                }

                tempPos++;
            }

        }
        throw new Exception();
    }

    @Override
    public int previous() throws Exception {
        if (hasPrevious()) {
            // lookup until you find a value
            int storedData = h.array[currentPos];
            int tempPos = currentPos - 1;
            while (tempPos >= 0) {
                System.out.println(h.array.length);
                System.out.println(tempPos);
                System.out.println(h.array[tempPos]);
                System.out.println("===");

                if (h.array[tempPos] != 0 && h.array[tempPos] != DoubleHashing.DELETED) {
                    currentPos = tempPos;
                    return storedData;
                }

                tempPos--;
            }

        }
        throw new Exception();
    }

    @Override
    public void set(int value) {
        // does not do anything,
        //because it will break hash table definition

        return;
    }

}
