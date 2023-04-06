
public class OpenAddressing {
	protected static int DEFAULT_SIZE = 11;
	protected static final int DELETED = -9999;
	protected static double MAXFACTOR = 0.5;
	protected int currentSize = 0;
	protected int[] array;

	protected static boolean isPrime(int n) {
		if (n == 2 || n == 3)
			return true;
		if (n == 1 || n % 2 == 0)
			return false;
		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;
		return true;
	}

	protected static int nextPrime(int n) {
		if (n % 2 == 0)
			n++;
		for (; !isPrime(n); n += 2) {
		}
		return n;
	}

	public OpenAddressing() {
		this(DEFAULT_SIZE);
	}

	public OpenAddressing(int size) {
		int nextPrimeSize = nextPrime(size);
		array = new int[nextPrimeSize];
	}
	
	
}
