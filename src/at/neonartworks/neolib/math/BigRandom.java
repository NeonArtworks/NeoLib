package at.neonartworks.neolib.math;

import static at.neonartworks.neolib.math.NeoMath.abs;
import static at.neonartworks.neolib.math.NeoMath.bigIntegerLenght;
import static at.neonartworks.neolib.math.NeoMath.longToBigInt;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * A random for BigDecimal / BigInteger <br>
 * <b>License is defined by </b> //TODO
 * 
 * @author Alexander Daum
 *
 */
public class BigRandom {
	public static final BigInteger MAX_LONG_VALUE = new BigInteger(String.valueOf(Long.MAX_VALUE));
	/**
	 * The random to be used, if this is ThreadSafe, random is a SecureRandom,
	 * else it is a "normal" random
	 */
	private Random random;
	/**
	 * The respective from and to values used in the next methods without
	 * additional parameters
	 */
	private BigDecimal from, to;

	/**
	 * Constructs a new BigRandom with a "normal" Random as random
	 */
	public BigRandom() {
		random = new Random();
	}

	/**
	 * Constructs a new BigRandom, if threadSafe is true, the Random is a
	 * SecureRandom, otherwise it is a "normal" random
	 * 
	 * @param threadSafe
	 */
	public BigRandom(boolean threadSafe) {
		if (threadSafe) {
			random = new SecureRandom();
		} else {
			random = new Random();
		}
	}

	/**
	 * if threadSafe is true the random is a new SecureRandom, otherwise a new
	 * Random. The random always gets constructed newly when this method is
	 * invoked
	 * 
	 * @param threadSafe
	 *            if this BigRandom should be threadSafe
	 * @return This Instance, to allow method chaining for convenience
	 */
	public BigRandom setThreadSafe(boolean threadSafe) {
		if (threadSafe) {
			random = new SecureRandom();
		} else {
			random = new Random();
		}
		return this;
	}

	/**
	 * Sets the seed of this Random.
	 * 
	 * @param seed
	 *            The new Seed of this Random
	 * @return This Instance, to allow method chaining for convenience
	 */
	public BigRandom setSeed(long seed) {
		random.setSeed(seed);
		return this;
	}

	/**
	 * Generates a random BigDecimal between DefaultFrom and DefaultTo, where it
	 * doen't matter which is the largest. Accuracy defines how many digits
	 * behind the comma are calculated. A negative Accuracy means, that
	 * 
	 * @param accuracy
	 * @return
	 */
	public BigDecimal nextBDecimal(int accuracy) {
		return nextBDecimal(this.from, this.to, accuracy);
	}

	/**
	 * Generates a random BigDecimal between from and to, where it doen't matter
	 * which is the largest. Accuracy defines how many digits behind the comma
	 * are calculated. A negative Accuracy means, that
	 * 
	 * @param accuracy
	 * @return
	 */
	public BigDecimal nextBDecimal(BigDecimal from, BigDecimal to, int accuracy) {
		BigInteger fromUnscaled = from.scaleByPowerOfTen(accuracy).toBigInteger();
		BigInteger toUnscaled = to.scaleByPowerOfTen(accuracy).toBigInteger();
		BigInteger unscaledRandom = nextBInt(fromUnscaled, toUnscaled, 0);
		BigDecimal scaledRandom = new BigDecimal(unscaledRandom, accuracy);
		return scaledRandom;
	}

	/**
	 * Generates a random BigInteger between from and to. It doen't matter which
	 * one is the largest. The accuracy is automatically computed, so that it
	 * needs aprox. 0.25 ms at max, if you need higher performance or higher
	 * accuracy use: <code>nextBInt(int accuracy)</code> <br>
	 * 
	 * @return A Random BigInteger between from and to
	 */
	public BigInteger nextBInt() {
		return nextBInt(this.from.toBigInteger(), this.to.toBigInteger());
	}

	/**
	 * Generates a random BigInteger between from and to. It doen't matter which
	 * one is the largest. The accuracy is automatically computed, so that it
	 * needs aprox. 25 ms at max, if you need higher performance or higher
	 * accuracy use: <code>nextBInt(int accuracy)</code> <br>
	 * 
	 * @return A Random BigInteger between from and to
	 */
	public BigInteger nextBIntAccurate() {
		return nextBIntAccurate(this.from.toBigInteger(), this.to.toBigInteger());
	}

	/**
	 * Generates a random BigInteger between DefaultFrom and DefaultTo, where
	 * all Decimal parts are lost. It doen't matter which one is the largest.
	 * The Random will be accurate to accuracy digits before the last digit.
	 * This means if accuracy is 1, the random will be generated in steps of 10,
	 * if it is 2 in steps of 100... <br>
	 * This Critically effects the performance, because a 10 times larger
	 * BigInteger will need aproximatly 10 times slower per extra digit accuracy
	 * added.
	 * 
	 * @param accuracy
	 *            how accurate the random is generated. Higher means more
	 *            accurate, Lower means faster
	 * @return A Random BigInteger between from and to
	 */
	public BigInteger nextBInt(int accuracy) {
		return nextBInt(this.from.toBigInteger(), this.to.toBigInteger(), accuracy);
	}

	/**
	 * Generates a random BigInteger between from and to. It doen't matter which
	 * one is the largest. The accuracy is automatically computed, so that it
	 * needs aprox. 0.25ms at max, if you need higher performance or higher
	 * accuracy use:
	 * <code>nextBInt(BigInteger from, BigInteger to, int accuracy)</code> <br>
	 * 
	 * @param from
	 *            The first limit
	 * @param to
	 *            The second limit
	 * @return A Random BigInteger between from and to
	 */
	public BigInteger nextBInt(BigInteger from, BigInteger to) {
		int accuracy, length;
		BigInteger min = from;
		BigInteger max = to;
		if (max.compareTo(min) < 0) {
			BigInteger tmp = max;
			max = min;
			min = tmp;
		}
		BigInteger difference = max.subtract(min);
		length = bigIntegerLenght(difference);
		accuracy = Math.max(length - 19, 3);
		return min.add(nextBInt(difference, accuracy - 3));
	}

	/**
	 * Generates a random BigInteger between from and to. It doen't matter which
	 * one is the largest. The accuracy is automatically computed, so that it
	 * needs aprox. 25ms at max, if you need higher performance or higher
	 * accuracy use:
	 * <code>nextBInt(BigInteger from, BigInteger to, int accuracy)</code> <br>
	 * 
	 * @param from
	 *            The first limit
	 * @param to
	 *            The second limit
	 * @return A Random BigInteger between from and to
	 */
	public BigInteger nextBIntAccurate(BigInteger from, BigInteger to) {
		int accuracy, length;
		BigInteger min = from;
		BigInteger max = to;
		if (max.compareTo(min) < 0) {
			BigInteger tmp = max;
			max = min;
			min = tmp;
		}
		BigInteger difference = max.subtract(min);
		length = bigIntegerLenght(difference);
		accuracy = Math.max(length - 19, 5);
		return min.add(nextBInt(difference, accuracy - 5));
	}

	/**
	 * Generates a random BigInteger between from and to. It doen't matter which
	 * one is the largest. The Random will be accurate to accuracy digits before
	 * the last digit. This means if accuracy is 1, the random will be generated
	 * in steps of 10, if it is 2 in steps of 100... <br>
	 * This Critically effects the performance, because a 10 times larger
	 * BigInteger will need aproximatly 10 times slower per extra digit accuracy
	 * added.
	 * 
	 * @param from
	 *            The first limit
	 * @param to
	 *            The second limit
	 * @param accuracy
	 *            how accurate the random is generated. Higher means more
	 *            accurate, Lower means faster
	 * @return A Random BigInteger between from and to
	 */
	public BigInteger nextBInt(BigInteger from, BigInteger to, int accuracy) {
		BigInteger min = from;
		BigInteger max = to;
		if (max.compareTo(min) < 0) {
			BigInteger tmp = max;
			max = min;
			min = tmp;
		}
		BigInteger difference = max.subtract(min);
		return min.add(nextBInt(difference, accuracy));
	}

	/**
	 * Generates the offset for difference and accuracy
	 * 
	 * @param difference
	 * @param accuracy
	 * @return
	 */
	private BigInteger nextBInt(BigInteger difference, int accuracy) {
		if (difference.equals(BigInteger.ZERO)) {
			throw new IllegalArgumentException("Difference of from and to is 0");
		}
		if (accuracy == 0) {
			return nextBIntExact(difference);
		}
		BigInteger pow10Acc = NeoMath.bigPowerOfTen(accuracy);
		BigInteger offset = BigInteger.ZERO;
		while (difference.compareTo(BigInteger.ZERO) > 0) {
			// long time = System.nanoTime();
			if (difference.compareTo(MAX_LONG_VALUE.multiply(pow10Acc)) >= 0) {
				difference = difference.subtract(MAX_LONG_VALUE.multiply(pow10Acc));
				BigInteger addOffset = longToBigInt(abs(random.nextLong())).multiply(pow10Acc);
				offset = offset.add(addOffset);
			} else {
				long diffLong = difference.divide(pow10Acc).longValue();
				difference = BigInteger.ZERO;
				long l = random.nextLong();
				l = abs(l);
				l = l % diffLong;
				offset = offset.add(longToBigInt(l).multiply(pow10Acc));
			}
			// long needed = System.nanoTime() - time;
			// System.out.println(needed);
		}
		return offset;
	}

	private BigInteger nextBIntExact(BigInteger difference) {
		if (difference.equals(BigInteger.ZERO)) {
			throw new IllegalArgumentException("Difference of from and to is 0");
		}
		BigInteger offset = BigInteger.ZERO;
		while (difference.compareTo(BigInteger.ZERO) > 0) {
			// long time = System.nanoTime();
			if (difference.compareTo(MAX_LONG_VALUE) >= 0) {
				difference = difference.subtract(MAX_LONG_VALUE);
				BigInteger addOffset = longToBigInt(abs(random.nextLong()));
				offset = offset.add(addOffset);
			} else {
				long diffLong = difference.longValue();
				difference = BigInteger.ZERO;
				long l = random.nextLong();
				l = abs(l);
				l = l % diffLong;
				offset = offset.add(longToBigInt(l));
			}
			// long needed = System.nanoTime() - time;
			// System.out.println(needed);
		}
		return offset;
	}

	/**
	 * @return The defaultFrom value
	 */
	public BigDecimal getDefaultFrom() {
		return from;
	}

	/**
	 * sets the new DefaultFrom value, used in all next methods, which don't
	 * have additional parameters. <br>
	 * you should also set DefaultTo
	 * 
	 * @param from
	 *            the new DefaultFrom value
	 */
	public BigRandom setDefaultFrom(BigDecimal from) {
		this.from = from;
		return this;
	}

	/**
	 * sets the new DefaultFrom value, used in all next methods, which don't
	 * have additional parameters. <br>
	 * you should also set DefaultTo
	 * 
	 * @param from
	 *            the new DefaultFrom value
	 */
	public BigRandom setDefaultFrom(BigInteger from) {
		this.from = new BigDecimal(from);
		return this;
	}

	/**
	 * sets the new DefaultFrom value, used in all next methods, which don't
	 * have additional parameters. <br>
	 * you should also set DefaultTo
	 * 
	 * @param from
	 *            the new DefaultFrom value
	 */
	public BigRandom setDefaultFrom(String from) {
		this.from = new BigDecimal(from);
		return this;
	}

	/**
	 * @return the DefaultToValue
	 */
	public BigDecimal getDefaultTo() {
		return to;
	}

	/**
	 * sets the new DefaultTo value, used in all next methods, which don't have
	 * additional parameters. <br>
	 * you should also set DefaultFrom
	 * 
	 * @param from
	 *            the new DefaultTo value
	 */
	public BigRandom setDefaultTo(BigDecimal to) {
		this.to = to;
		return this;
	}

	/**
	 * sets the new DefaultTo value, used in all next methods, which don't have
	 * additional parameters. <br>
	 * you should also set DefaultFrom
	 * 
	 * @param from
	 *            the new DefaultTo value
	 */
	public BigRandom setDefaultTo(BigInteger to) {
		this.to = new BigDecimal(to);
		return this;
	}

	/**
	 * sets the new DefaultTo value, used in all next methods, which don't have
	 * additional parameters. <br>
	 * you should also set DefaultFrom
	 * 
	 * @param from
	 *            the new DefaultTo value
	 */
	public BigRandom setDefaultTo(String to) {
		this.to = new BigDecimal(to);
		return this;
	}

}
