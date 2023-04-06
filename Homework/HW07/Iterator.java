public interface Iterator {

	public boolean hasNext();

	public boolean hasPrevious();

	// Move iterator to the next position,
	// then returns the value at that position.
	//throw exception if it cannot go to that position.
	public int next() throws Exception; 
	                    
	// Return the value at current position,
	// then move the iterator back one position.
	//throw exception if it cannot go to that position.
	public int previous() throws Exception;
	                        
	public void set(int value);

}
