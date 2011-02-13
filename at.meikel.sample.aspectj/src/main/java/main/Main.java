package main;

import logic.PrimeNumberCalculator;

public class Main {

	public static void main(String[] args) {
		for (int i : new int[] { 4711, 4712, 4713 }) {
			System.out.println(i + " is "
					+ (PrimeNumberCalculator.isPrime(i) ? "" : "not ")
					+ "a prime");
		}
	}
}
