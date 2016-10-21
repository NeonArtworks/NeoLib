package at.neonartworks.neolib.math;

import java.security.SecureRandom;
import java.util.Random;

import at.neonartworks.neolib.exceptions.OverflowException;

/**
 * A Class with some Number Methods, like round, random or pow. All this Methods
 * are Different from the ones found in math, mostly for convienience, because
 * they are easier to use <br>
 * 
 * <b>License is defined by </b> //TODO
 * 
 * @author Alexander Daum
 *
 */
public class NeoNumber {
	/**
	 * The respective from and to values used in the next methods without
	 * additional parameters
	 */
	private int from, to;
	/**
	 * The random to be used, if this is ThreadSafe, random is a SecureRandom,
	 * else it is a "normal" random
	 */
	private Random random;

	/**
	 * Constructs a new NeoNumber, the Random is a new Random with default seed
	 */
	public NeoNumber() {
		random = new Random();
	}

	/**
	 * Constructs a new NeoNumber, the Random is either a new SecureRandom or a
	 * new Random, depending on threadSafe option. Both are constructed with
	 * default seed
	 * 
	 * @param threadSafe
	 *            If this NeoNumber should use a secureRandom to be threadSafe
	 */
	public NeoNumber(boolean threadSafe) {
		if (threadSafe) {
			random = new SecureRandom();
		} else {
			random = new Random();
		}
	}

	/**
	 * makes this NeoNumber ThreadSafe or Not ThreadSafe
	 * 
	 * @param threadSafe
	 *            if the NeoNumber should be ThreadSafe, thus using a
	 *            SecureRandom
	 * @return This Instance, to allow method chaining for convenience
	 */
	public NeoNumber setThreadSafe(boolean threadSafe) {
		if (threadSafe) {
			random = new SecureRandom();
		} else {
			random = new Random();
		}
		return this;
	}

	/**
	 * Sets the seed for the Random of this NeoNumber
	 * 
	 * @param seed
	 *            the Seed for the Random
	 * @return This Instance, to allow method chaining for convenience
	 */
	public NeoNumber setSeed(long seed) {
		this.random.setSeed(seed);
		return this;
	}

	/**
	 * Generates a Random int between this from and to values. From and to may
	 * not be the same, but it doesn't matter which one is bigger
	 * 
	 * @return A random number between from and to
	 */
	public int nextInt() {
		if (from == to) {
			throw new IllegalArgumentException("From and To are the same Number");
		}
		int offset = random.nextInt(Math.abs(to - from + 1));
		return Math.min(from, to) + offset;
	}

	/**
	 * Generates a Random int between from and to values. From and to may not be
	 * the same, but it doesn't matter which one is bigger
	 * 
	 * @param from
	 *            one of the values, usually this is the smaller value, but it
	 *            also can be the bigger
	 * @param to
	 *            one of the values, usually this is the bigger value, but it
	 *            also can be the smaller
	 * 
	 * @return A random number between from and to
	 */
	public int nextInt(int from, int to) {
		if (from == to) {
			throw new IllegalArgumentException("From and To are the same Number");
		}
		int offset = random.nextInt(Math.abs(to - from + 1));
		return Math.min(from, to) + offset;
	}

	/**
	 * Generates a new Random float between this from and to values with
	 * accuracy of digits behind the comma. The from and to values are ints, so
	 * if you want to use float type bounds, use:
	 * <code>nextFloat(float from, float to, int accuracy)</code>
	 * 
	 * @param accuracy
	 *            The number of digits behind the comma
	 * @return A Random float value between from and to with accuracy digits
	 *         behind the comma
	 */
	public float nextFloat(int accuracy) {
		if (from == to) {
			throw new IllegalArgumentException("From and To are the same Number");
		}
		int offsetMax = Math.abs(from - to);
		float multOffset = random.nextFloat();
		float offset = offsetMax * multOffset;
		return (Math.min(from, to)) + offset;
	}

	/**
	 * Generates a new Random float between this from and to values with
	 * accuracy of digits behind the comma.
	 * 
	 * @param accuracy
	 *            The number of digits behind the comma
	 * @return A Random float value between from and to with accuracy digits
	 *         behind the comma
	 */
	public float nextFloat(float from, float to, int accuracy) {
		if (from == to) {
			throw new IllegalArgumentException("From and To are the same Number");
		}
		float offsetMax = Math.abs(from - to);
		float multOffset = random.nextFloat();
		float offset = offsetMax * multOffset;
		float result = (Math.min(from, to)) + offset;
		return round(result, accuracy);
	}

	/**
	 * Generates a new Random double between this from and to values with
	 * accuracy of digits behind the comma. The from and to values are ints, so
	 * if you want to use double type bounds, use:
	 * <code>nextDouble(double from, double to, int accuracy)</code>
	 * 
	 * @param accuracy
	 *            The number of digits behind the comma
	 * @return A Random double value between from and to with accuracy digits
	 *         behind the comma
	 */
	public double nextDouble(int accuracy) {
		if (from == to) {
			throw new IllegalArgumentException("From and To are the same Number");
		}
		int offsetMax = Math.abs(from - to);
		double multOffset = random.nextFloat();
		double offset = offsetMax * multOffset;
		return (Math.min(from, to)) + offset;
	}

	/**
	 * Generates a new Random double between this from and to values with
	 * accuracy of digits behind the comma.
	 * 
	 * @param accuracy
	 *            The number of digits behind the comma
	 * @return A Random double value between from and to with accuracy digits
	 *         behind the comma
	 */
	public double nextDouble(double from, double to, int accuracy) {
		if (from == to) {
			throw new IllegalArgumentException("From and To are the same Number");
		}
		double offsetMax = Math.abs(from - to);
		double multOffset = random.nextFloat();
		double offset = offsetMax * multOffset;
		return (Math.min(from, to)) + offset;
	}

	/**
	 * Rounds the float number to specified digits behind the comma
	 * 
	 * @param number
	 *            the float to be rounded
	 * @param digits
	 *            the number of digits behind the comma
	 * @return The rounded Float
	 */
	public float round(float number, int digits) {
		double bigNum = number * digits;
		bigNum = Math.round(bigNum);
		return (float) (bigNum / digits);
	}

	/**
	 * Rounds a double number to specified ammount of digits behind the comma
	 * 
	 * @param number
	 *            the double to be rounded
	 * @param digits
	 *            the number of digits behind the comma
	 * @return The rounded double
	 */
	public double round(double number, int digits) {
		double bigNum = number * digits;
		bigNum = Math.round(bigNum);
		return bigNum / digits;
	}

	/**
	 * Calculates a^b with 2 int values with a max result value of
	 * <code>Long.maxValue</code>
	 * 
	 * @param a
	 *            The first number
	 * @param b
	 *            The
	 * @return
	 */
	public long pow(int a, int b) {
		long bigValue = 1;
		for (int i = 0; i < b; i++) {
			bigValue *= a;
			if (bigValue >= Long.MAX_VALUE / a && (i + 1) < b) {
				throw new OverflowException("Result Long would Overflow");
			}
		}
		return bigValue;
	}

	/**
	 * 
	 * @return the defaultFrom value
	 */
	public int getDefaultFrom() {
		return from;
	}

	/**
	 * Sets the defaultFrom value, used in the next methods without additional
	 * parameters, useful if you have to create multiple Radnom numbers in the
	 * same Range. <br>
	 * you should also set DefaultTo
	 * 
	 * @param from
	 *            the defaultFrom value
	 */
	public void setDefaultFrom(int from) {
		this.from = from;
	}

	/**
	 * 
	 * @return the defaultTo value
	 */
	public int getDefaultTo() {
		return to;
	}

	/**
	 * Sets the defaultFrom value, used in the next methods without additional
	 * parameters, useful if you have to create multiple Radnom numbers in the
	 * same Range <br>
	 * you should also set defaultFrom
	 * 
	 * @param to
	 *            the defaultTo value
	 */
	public void setDefaultTo(int to) {
		this.to = to;
	}

}
