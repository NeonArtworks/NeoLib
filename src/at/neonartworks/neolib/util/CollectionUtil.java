package at.neonartworks.neolib.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import at.neonartworks.neolib.exceptions.ClassNotCompatibleException;

public class CollectionUtil {
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
	public static Collection<Integer> numbers(int from, int to) {
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
	public static Collection<Integer> numbers(int from, int to, int step) {
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
	public static Collection<Long> numbers(long from, long to) {
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
	public static Collection<Long> numbers(long from, long to, long step) {
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

	/**
	 * Filters the Contents of a Collection using a Predicate of the specific
	 * Type. The Returned Collection is of the same Class as the original one,
	 * so if you use a HashSet for collection the result will be a HashSet. If
	 * the Type of Collection has no default Constructor, the result will be an
	 * ArrayList
	 * 
	 * @param condition
	 *            The Predicate used to Filter the Collection
	 * @param collection
	 *            The Collection which should be filtered
	 * @return A new Collection with all values from the original one matching
	 *         the condition, e.g. if the original condition is of type String
	 *         and contains: "a1","a2","b1","foo", and the Condition would be:
	 *         <code>s -> s.startsWith("a")</code>, this function would return a
	 *         Collection with: "a1","a2"
	 * @throws ReflectiveOperationException
	 */
	public static <T> Collection<T> getAll(Predicate<T> condition, Collection<T> collection) {
		Collection<T> ret;
		try {
			ret = collection.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			ret = new ArrayList<T>();
		}
		for (T o : collection) {
			if (condition.test(o)) {
				ret.add(o);
			}
		}
		return ret;
	}

	/**
	 * Filters the Contents of a Collection using a Predicate of the specific
	 * Type. The Returned Collection is always an ArrayList. This is preferable
	 * over
	 * <code>getAll(Predicate<T> condition, Collection<T> collection)</code> if
	 * the Type of the result doesn't matter
	 * 
	 * @param condition
	 *            The Predicate used to Filter the Collection
	 * @param collection
	 *            The Collection which should be filtered
	 * @return A new Collection with all values from the original one matching
	 *         the condition, e.g. if the original condition is of type String
	 *         and contains: "a1","a2","b1","foo", and the Condition would be:
	 *         <code>s -> s.startsWith("a")</code>, this function would return a
	 *         Collection with: "a1","a2"
	 */
	public static <T> Collection<T> getAllAsList(Predicate<T> condition, Collection<T> collection) {
		Collection<T> ret;
		ret = new ArrayList<T>();
		for (T o : collection) {
			if (condition.test(o)) {
				ret.add(o);
			}
		}
		return ret;
	}

	public static <T> Collection<T> getAllClassEqual(Class<T> targetClass, Collection<?> collection) {
		Collection<T> ret;
		try {
			ret = collection.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			ret = new ArrayList<T>();
		}
		for (Object o : collection) {
			if (o.getClass() == targetClass) {
				ret.add((T) o);
			}
		}
		return ret;
	}

	public static <T> Collection<T> getAllInstanceOf(Class<T> targetClass, Collection<?> collection) {
		Collection<T> ret;
		try {
			ret = collection.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			ret = new ArrayList<T>();
		}
		for (Object o : collection) {
			if (targetClass.isInstance(o)) {
				ret.add((T) o);
			}
		}
		return ret;
	}
}
