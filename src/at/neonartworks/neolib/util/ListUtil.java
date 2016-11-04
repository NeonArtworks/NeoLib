package at.neonartworks.neolib.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
	/**
	 * Creates a List filled with all ints between from and to, both inclusive
	 * 
	 * @param from
	 *            The smallest value in the list
	 * @param to
	 *            The biggest value in the list
	 * @return a List with all in values in the range, e.g.
	 *         <code>numbers(3,10)</code> returns a List with the values:
	 *         3,4,5,6,7,8,9,10
	 */
	public static List<Integer> numbers(int from, int to) {
		return numbers(from, to, 1);
	}

	/**
	 * Creates a List filled with all ints between from and to, both inclusive.
	 * Between two ints there is a difference of step
	 * 
	 * @param from
	 *            The smallest value in the list
	 * @param to
	 *            The biggest value in the list, may not be contained in the
	 *            list if it doesn't fit the step
	 * @param step
	 *            The difference between two values
	 * @return a List with all numbers in the range with a step of step. e.g
	 *         <code>numbers(15,57,4)</code> returns a List with the values:
	 *         15,19,23,27,31,35,39,43,47,51,55
	 */
	public static List<Integer> numbers(int from, int to, int step) {
		long length = (((to - from) / step) + 1);
		if (length > Integer.MAX_VALUE) {
			throw new IllegalArgumentException("Cannot add the value between " + from + " and " + to
					+ " with a step of " + step + " to a List, because this would result in too many numbers");
		}
		List<Integer> list = new ArrayList<Integer>(((to - from) / step) + 1);
		for (int i = from; i <= to; i += step) {
			list.add(i);
		}
		return list;
	}

	/**
	 * Creates a List filled with all longs between from and to, both inclusive
	 * 
	 * @param from
	 *            The smallest value in the list
	 * @param to
	 *            The biggest value in the list
	 * @return a List with all in values in the range, e.g.
	 *         <code>numbers(3,10)</code> returns a List with the values:
	 *         3,4,5,6,7,8,9,10
	 */
	public static List<Long> numbers(long from, long to) {
		return numbers(from, to, 1L);
	}

	/**
	 * Creates a List filled with all longs between from and to, both inclusive.
	 * Between two longs there is a difference of step
	 * 
	 * @param from
	 *            The smallest value in the list
	 * @param to
	 *            The biggest value in the list, may not be contained in the
	 *            list if it doesn't fit the step
	 * @param step
	 *            The difference between two values
	 * @return a List with all numbers in the range with a step of step. e.g
	 *         <code>numbers(15,57,4)</code> returns a List with the values:
	 *         15,19,23,27,31,35,39,43,47,51,55
	 */
	public static List<Long> numbers(long from, long to, long step) {
		long length = (((to - from) / step) + 1);
		if (length > Integer.MAX_VALUE) {
			throw new IllegalArgumentException("Cannot add the value between " + from + " and " + to
					+ " with a step of " + step + " to a List, because this would result in too many numbers");
		}
		List<Long> list = new ArrayList<Long>((int) length);
		for (long i = from; i <= to; i += step) {
			list.add(i);
		}
		return list;
	}
}
