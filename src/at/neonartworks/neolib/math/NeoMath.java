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

	/**
	 * @param x
	 *            (double)
	 * @return
	 * 
	 * <br>
	 *         a more efficient floor method
	 */
	public static int fastfloor(double x) {
		return x > 0 ? (int) x : (int) x - 1;
	}

	/**
	 * @param x
	 *            (float)
	 * @return
	 * 
	 * <br>
	 *         a more efficient floor method
	 */

	public static int fastfloor(float x) {
		return x > 0 ? (int) x : (int) x - 1;
	}

	/**
	 *
	 * @param p1
	 * @param p2
	 * @return The length between those Points
	 */

	public static double length(NeoPoint p1, NeoPoint p2) {
		return Math.sqrt((p1.getX() - p2.getX()) * (p1.getX() - p2.getX())
				+ (p1.getY() - p2.getY()) * (p1.getY() - p2.getY()));
	}

	/**
	 * @param val
	 *            (float)
	 * @return Returns val as positive number , if val is negativ
	 * 
	 */

	public static float pos(float val) {
		if (val < 0) {
			val = val * (-1);
		}
		if (val > 0) {
			val = val;
		}
		return val;

	}

	/**
	 * @param val
	 *            (double)
	 * @return Returns val as positive number , if val is negativ
	 * 
	 */
	public static double pos(double val) {
		if (val < 0) {
			val = val * (-1);
		}
		if (val > 0) {
			val = val;
		}
		return val;

	}
}