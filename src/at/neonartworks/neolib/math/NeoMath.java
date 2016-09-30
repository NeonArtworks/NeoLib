package at.neonartworks.neolib.math;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NeoMath {
	/**
	 * returns the absolute value of the long, unlike the Math function, this
	 * return <code>Long.maxValue</code> for <code>Long.minValue</code>, where
	 * the Math function would return <code>Long.minValue</code>
	 * 
	 * @param a
	 * @return
	 */
	public static long absLong(long a) {
		if (a >= 0) {
			return a;
		}
		if (a == Long.MIN_VALUE) {
			return Long.MAX_VALUE;
		}
		return -a;
	}

	public static int bigIntegerLenght(BigInteger bigInt) {
		return bigInt.toString().length();
	}

	/**
	 * Converts an long to a BigInteger
	 * 
	 * @param value
	 *            The long value
	 * @return a BigInteger with the same value as the long value
	 */
	public static BigInteger longToBigInt(long value) {
		return new BigInteger(String.valueOf(value));

	}

	/**
	 * returns 10^pow as a BigInteger
	 * 
	 * @param pow
	 * @return
	 */
	public static BigInteger powerOfTen(int pow) {
		return BigInteger.TEN.pow(pow);
	}

	/**
	 * Converts an long to a BigDecimal with scale 0
	 * 
	 * @param value
	 *            The long value
	 * @return a BigDecimal with the same value as the long value and scale 0
	 */
	public static BigDecimal longToBigDecimal(long value) {
		return new BigDecimal(String.valueOf(value));
	}

	/**
	 * Generates a new String with from a Long, where all the digits in the
	 * front are 0
	 * 
	 * @param toFill
	 * @return
	 */
	public static String fillLongWith0(long toFill) {
		StringBuilder sb = new StringBuilder(String.valueOf(toFill));
		for(int i = sb.length(); i < 19; i++){
			sb.insert(0, "0");
		}
		return sb.toString();
	}
	/**
	 * Generates a new String with from a Long, where all the digits in the
	 * front are 0 to a total number of digits of length
	 * 
	 * @param toFill
	 * @return
	 */
	public static StringBuilder fillLongWith0(long toFill, int length, StringBuilder builder) {
		StringBuilder sb = builder;
		sb.insert(0, String.valueOf(toFill));
		for(int i = sb.length(); i < length; i++){
			sb.insert(0, "0");
		}
		return sb;
	}
}
