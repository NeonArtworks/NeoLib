package at.neonartworks.neolib.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import com.sun.javafx.iio.common.SmoothMinifier;

import at.neonartworks.neolib.util.ArrayUtil;

/**
 * A random for BigDecimal / BigInteger
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
	 * behind the comma are calculated. A negative Accuracy means, that there
	 * are 0 before the comma
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
	 * are calculated. A negative Accuracy means, that there are 0 before the
	 * comma
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
	 * one is the largest. The Accuracy is to the last digit
	 * 
	 * @return A Random BigInteger between from and to
	 */
	public BigInteger nextBInt() {
		return nextBInt(this.from.toBigInteger(), this.to.toBigInteger());
	}

	/**
	 * Generates a random BigInteger between from and to. It doen't matter which
	 * one is the largest. The Accuracy is to the last digit
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
	 * The real Calculation
	 * 
	 * @param difference
	 * @return
	 */
	private BigInteger nextBInt(BigInteger difference) {
		// XXX
		if (difference.equals(BigInteger.ZERO)) {
			return BigInteger.ZERO;
		}
		BigInteger offset = BigInteger.ZERO;
		// Anzahl an ganzen Bytes
		int bitLength = difference.bitLength();
		int lengthOfArray = bitLength / 8;
		int overflow = bitLength % 8;

		byte[] val = new byte[lengthOfArray + 1];
		random.nextBytes(val);
		int over = val[0];
		int mask = over >> 31;
		mask = mask & 0x00000080;
		over = over & 0x0000007F;
		over = over | mask;
		over = over >>> (8 - overflow);
		offset = new BigInteger(val);
		return offset.mod(difference);
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

	private byte smallerBytewise(byte b1, byte b2) {
		return (byte) ((b1 & b2) - 1);
	}

}
