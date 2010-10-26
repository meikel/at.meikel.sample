package logic;

import java.util.Arrays;

import junit.framework.TestCase;

public class PrimeNumberCalculatorTest extends TestCase {

	public void test1IsNoPrime() {
		assertFalse("1 is no prime.", PrimeNumberCalculator.isPrime(1));
	}

	public void test2IsAPrime() {
		assertTrue("2 is a prime.", PrimeNumberCalculator.isPrime(2));
	}

	public void testNegativeNumberIsNotAPrime() {
		assertFalse("-2 is not a prime.", PrimeNumberCalculator.isPrime(-2));
	}

	public void testPrimeNumbersBetween1And10() {
		int[] numbers = PrimeNumberCalculator.primes(1, 10);
		assertNotNull("Result for primes between 1 and 10 is not null.",
				numbers);
		assertEquals("4 prime numbers exist between 1 and 10", 4,
				numbers.length);
		assertEquals("1st prime number between 1 and 10 is 2.", 2, numbers[0]);
		assertEquals("2nd prime number between 1 and 10 is 3.", 3, numbers[1]);
		assertEquals("3rd prime number between 1 and 10 is 5.", 5, numbers[2]);
		assertEquals("4th prime number between 1 and 10 is 7.", 7, numbers[3]);
	}

	public void testPrimeNumbersBetween10And1() {
		int[] expected = PrimeNumberCalculator.primes(1, 10);
		int[] actual = PrimeNumberCalculator.primes(10, 1);
		assertTrue(
				"Result for primes between 10 and 1 equals result of primes between 1 and 10.",
				Arrays.equals(expected, actual));
	}

}
