package at.neonartworks.neolib.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public class NeoMath {

	/**
	 * returns the absolute value of the long, unlike the Math function, this
	 * return <code>Long.maxValue</code> for <code>Long.minValue</code>, where
	 * the Math function would return <code>Long.minValue</code>
	 * 
	 * @param a
	 * @return
	 */
	public static long abs(long a) {
		if (a >= 0) {
			return a;
		}
		if (a == Long.MIN_VALUE) {
			return Long.MAX_VALUE;
		}
		return (long) -a;
	}

	/**
	 * returns the absolute value of the int, unlike the Math function, this
	 * return <code>Integer.maxValue</code> for <code>Integer.minValue</code>,
	 * where the Math function would return <code>Integer.minValue</code>
	 * 
	 * @param a
	 * @return
	 */
	public static int abs(int a) {
		if (a >= 0) {
			return a;
		}
		if (a == Integer.MIN_VALUE) {
			return Integer.MAX_VALUE;
		}
		return -a;
	}

	/**
	 * returns the absolute value of the short, unlike the Math function, this
	 * return <code>Short.maxValue</code> for <code>Short.minValue</code>, where
	 * the Math function would return <code>Short.minValue</code>
	 * 
	 * @param a
	 * @return
	 */
	public static short abs(short a) {
		if (a >= 0) {
			return a;
		}
		if (a == Short.MIN_VALUE) {
			return Short.MAX_VALUE;
		}
		return (short) -a;
	}

	/**
	 * returns the absolute value of the byte, unlike the Math function, this
	 * return <code>Byte.maxValue</code> for <code>Byte.minValue</code>, where
	 * the Math function would return <code>Byte.minValue</code>
	 * 
	 * @param a
	 * @return
	 */
	public static byte abs(byte a) {
		if (a >= 0) {
			return a;
		}
		if (a == Byte.MIN_VALUE) {
			return Byte.MAX_VALUE;
		}
		return (byte) -a;
	}

	/**
	 * Calculates the number of digits of a BigInteger
	 * 
	 * @param bigInt
	 * @return
	 */
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
	public static BigInteger bigPowerOfTen(int pow) {
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
	 * @param value
	 *            (float) The Value to clamp
	 * @param min
	 *            The Min Value.
	 * @param max
	 *            The Max Value
	 * 
	 * @return The clamped Value
	 */

	public static float clamp(float value, int min, int max) {
		if (value < min)
			return min;
		if (value > max)
			return max;
		return value;

	}

	/**
	 * @param value
	 *            (double) The Value to clamp
	 * @param min
	 *            The Min Value.
	 * @param max
	 *            The Max Value
	 * 
	 * @return The clamped Value
	 */

	public static double clamp(double value, int min, int max) {
		if (value < min)
			return min;
		if (value > max)
			return max;
		return value;

	}

	/***
	 * Calculates the floor value of a double more efficiently
	 * 
	 * @param x
	 * @return a more efficient floor method
	 */
	public static int fastfloor(double x) {
		return x > 0 ? (int) x : (int) x - 1;
	}

	/**
	 * Calculates the floor value of a float more efficiently
	 * 
	 * @param x
	 * @return a more efficient floor method
	 */

	public static int fastfloor(float x) {
		return x > 0 ? (int) x : (int) x - 1;
	}

	/**
	 *
	 * @param p1
	 *            Point 1
	 * @param p2
	 *            Point 2
	 * @return The length between those Points
	 */

	public static double length(NeoPoint p1, NeoPoint p2) {
		return Math.sqrt(
				(p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) + (p1.getY() - p2.getY()) * (p1.getY() - p2.getY()));
	}

	/**
	 * Generates a String representing the value filled with 0 in the front to
	 * match the number of digits <br>
	 * e.g <code>fillLongWith0(1234, 6)</code> will give you 001234
	 * 
	 * @param value
	 *            The value
	 * @param digits
	 *            How many digits it should have
	 * @param sb
	 *            The StringBuilder to attach the Value
	 * @return the String.
	 */
	public static String fillLongWith0(long value, int digits) {
		StringBuilder sb = new StringBuilder();
		String longS = String.valueOf(value);
		sb.append(longS);
		for (int i = longS.length(); i < digits; i++) {
			sb.insert(0, "0");
		}
		return sb.toString();
	}

	/**
	 * Adds 0 and value in front of sb, so that there are exactly digits of
	 * digits
	 * 
	 * @param value
	 *            The value
	 * @param digits
	 *            How many digits it should have
	 * @param sb
	 *            The StringBuilder to attach the Value
	 * @return sb again
	 */
	public static StringBuilder fillLongWith0(long value, int digits, StringBuilder sb) {
		String longS = String.valueOf(value);
		sb.insert(0, longS);
		for (int i = longS.length(); i < digits; i++) {
			sb.insert(0, "0");
		}
		return sb;
	}

	/**
	 * Calculates the Sum of an Array of doubles
	 * 
	 * @param values
	 * @return
	 */
	public static double sum(double... values) {
		double sum = 0;
		for (double d : values) {
			sum += d;
		}
		return sum;
	}

	/**
	 * Calculates the sum of an Array of BigIntegers
	 * 
	 * @param values
	 * @return
	 */
	public static BigInteger sum(BigInteger... values) {
		BigInteger sum = BigInteger.ZERO;
		for (BigInteger bInt : values) {
			sum = sum.add(bInt);
		}
		return sum;
	}

	/**
	 * Calculates the sum of an Array of BigDecimals
	 * 
	 * @param values
	 * @return
	 */
	public static BigDecimal sum(BigDecimal... values) {
		BigDecimal sum = BigDecimal.ZERO;
		for (BigDecimal bInt : values) {
			sum = sum.add(bInt);
		}
		return sum;
	}

	/**
	 * Rounds a double number to a specified number of digits behind the comma.
	 * The rounding algorithm is specified by the RoundingContext
	 * 
	 * @param value
	 *            The value to be rounded
	 * @param digits
	 *            The number of digits behind the comma
	 * @param rc
	 *            The RoundingContext for the algorithm
	 * @return
	 */
	public static double round(double value, int digits, RoundingContext rc) {
		if (digits > 16)
			digits = 16;
		double lastDigits = value % 10;
		double tmp = value - lastDigits;
		double pow10 = Math.pow(10, digits);
		tmp += rc.round(lastDigits * pow10) / pow10;
		return tmp;
	}

	/**
	 * Calculates the power of 2 for int pow.
	 * 
	 * @param pow
	 * 
	 * @return 2 ^ pow
	 */
	public static long powerOfTwo(int pow) {
		return 1L << pow;
	}

	/**
	 * Calculates the power of 10, uses long for convienience
	 * 
	 * @param pow
	 * @return 10^pow
	 */
	public static long powerOfTen(int pow) {
		long ret = 1L;
		for (int i = 0; i < pow; i++) {
			ret = ret * 10L;
		}
		return ret;
	}

	/**
	 * Caclulates the remainder division of byte[], like value % mod
	 * 
	 * @param value
	 *            The value
	 * @param mod
	 *            the mod value
	 * @return
	 */
	public static byte[] remainder(byte[] value, byte[] mod) {
		BigInteger v = new BigInteger(value);
		BigInteger m = new BigInteger(mod);
		return v.remainder(m).toByteArray();
	}

	/**
	 * Caclulates the modolo division of byte[], like value % mod, except the
	 * result is always positive
	 * 
	 * @param value
	 *            The value
	 * @param mod
	 *            the mod value
	 * @return
	 */
	public static byte[] mod(byte[] value, byte[] mod) {
		BigInteger v = new BigInteger(value);
		BigInteger m = new BigInteger(mod);
		return v.mod(m).toByteArray();
	}

	public static int[] byteArrayToIntArray(byte[] array) {
		int[] result = new int[array.length / 4 + (array.length % 4 == 0 ? 0 : 1)];
		for (int i = 0; i < result.length - 1; i++) {
			byte[] section = Arrays.copyOfRange(array, array.length - 1 - ((i + 1) * 4), array.length - 1 - (i * 4));
			result[result.length - i - 1] = byteArrayToIntIgnoreLast(section);
		}
		int length = array.length % 4 == 0 ? 4 : array.length % 4;
		byte[] section = Arrays.copyOfRange(array, 0, array.length % 4);
		result[0] = byteArrayToIntIgnoreLast(section);
		return result;
	}

	/**
	 * Converts a byte Array to an int. Only works when array has 4 or less
	 * values, else this method will ignore the first entries. <br>
	 * In the Array the most important bits have to be in index 0. The sign of
	 * the resulting int is equal to the sign of the byte in index 0
	 * 
	 * @param array
	 *            A byte Array which is converted to an int
	 * @return
	 * @see {@link #byteArrayToIntIgnoreLast(byte[])}
	 */
	public static int byteArrayToInt(byte[] array) {
		int result = 0;
		for (int i = 0; i < array.length; i++) {
			result = result << 8;
			result = result | array[i];
		}
		return result;
	}

	/**
	 * Converts a byte Array to an int. Only works when array has 4 or less
	 * values, else this method will ignore the last entries. <br>
	 * In the Array the most important bits have to be in index 0. The sign of
	 * the resulting int is equal to the sign of the byte in index 0
	 * 
	 * @param array
	 *            A byte Array which is converted to an int
	 * @return
	 * @see {@link #byteArrayToInt(byte[])}
	 */
	public static int byteArrayToIntIgnoreLast(byte[] array) {
		int result = 0;
		result = result + (array[0] & 0xFF);
		result = result << 8;
		result = result + (array[1] & 0xFF);
		result = result << 8;
		result = result + (array[2] & 0xFF);
		result = result << 8;
		result = result + (array[3] & 0xFF);
		return result;
	}
}