package at.neonartworks.neolib.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import static at.neonartworks.neolib.NeoMath.*;

/**
 * A random for BigDecimal / BigInteger <br>
 * <b>License is defined by </b> //TODO
 * 
 * @author Alexander Daum
 *
 */
public class BigRandom {
	public static final BigInteger MAX_LONG_VALUE = new BigInteger(String.valueOf(Long.MAX_VALUE));
	private static final long LENGTH_LONG = 1000000000000000000L;
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
		BigInteger unscaledRandom = nextBInt(fromUnscaled, toUnscaled);
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
	 * one is the largest. The Random is accurate to the last digit, with aprox.
	 * evenly distributed chances
	 * 
	 * @param from
	 *            The first limit
	 * @param to
	 *            The second limit
	 * @return A Random BigInteger between from and to
	 */
	public BigInteger nextBInt(BigInteger from, BigInteger to) {
		BigInteger min = from;
		BigInteger max = to;
		if (max.compareTo(min) < 0) {
			BigInteger tmp = max;
			max = min;
			min = tmp;
		}
		BigInteger difference = max.subtract(min);
		return min.add(nextBInt(difference));
	}

	/**
	 * Generates the offset for difference and accuracy
	 * 
	 * @param difference
	 * @param accuracy
	 * @return
	 */
	private BigInteger nextBInt(BigInteger difference) {
		if (difference.equals(BigInteger.ZERO)) {
			throw new IllegalArgumentException("Difference of from and to is 0");
		}
		StringBuilder sb = new StringBuilder();
		BigInteger powofTen18 = powerOfTen(18);
		while (difference.compareTo(BigInteger.ZERO) > 0) {
			int length = bigIntegerLenght(difference);

			// Length of Long is 19, but 1st number is useless
			if (length > 18) {
				if (difference.compareTo(MAX_LONG_VALUE) <= 0) {
					// Difference is smaller than Long
					long lMax = difference.longValueExact();
					long rand = random.nextLong() % lMax;
					rand = absLong(rand);
					sb.insert(0, rand);
					// End Loop, because no more randomising is needed
					break;
				}
				long rand = random.nextLong();
				rand = rand % LENGTH_LONG;
				rand = absLong(rand);
				sb = NeoMath.fillLongWith0(rand, 18, sb);
				difference = difference.divide(powofTen18);
			} else {
				long lMax = difference.longValueExact();
				long rand = random.nextLong() % lMax;
				rand = absLong(rand);
				sb.insert(0, rand);
				// End Loop, because no more randomising is needed
				break;
			}
		}
		return new BigInteger(sb.toString());
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
