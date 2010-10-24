package logic;

import java.util.Vector;

public class PrimeNumberCalculator {

	private PrimeNumberCalculator() {
		// intentionally left empty
	}

	public static boolean isPrime(int n) {
		if (n < 2) {
			return false;
		}

		if (n == 2) {
			return true;
		}

		for (int i = 2; i <= Math.sqrt(n) + 1; i++) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}

	public static int[] primes(int min, int max) {
		if (min > max) {
			int n = min;
			min = max;
			max = n;
		}

		Vector<Integer> primeNumbers = new Vector<Integer>();
		for (int n = min; n <= max; n++) {
			if (isPrime(n)) {
				primeNumbers.add(new Integer(n));
			}
		}

		int[] result = new int[primeNumbers.size()];
		for (int i = 0; i < primeNumbers.size(); i++) {
			result[i] = ((Integer) primeNumbers.get(i)).intValue();
		}
		return result;
	}

}
