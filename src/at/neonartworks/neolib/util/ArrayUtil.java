package at.neonartworks.neolib.util;

import java.util.Arrays;

public class ArrayUtil {
	/**
	 * Inserts an Element to any Index in an Array of any Type
	 * 
	 * @param array
	 * @param obj
	 * @param index
	 * @return
	 */
	public static <T> T[] insert(T[] array, T obj, int index) {
		if (index > array.length) {
			throw new ArrayIndexOutOfBoundsException(
					"cannot write object to index" + index + ", lenght of Array is only" + array.length);
		}
		T[] result = (T[]) new Object[array.length + 1];
		for (int i = 0; i < index; i++) {
			result[i] = array[i];
		}
		for (int i = index; i < array.length; i++) {
			result[i + 1] = array[i];
		}
		result[index] = obj;
		return result;

	}

	/**
	 * Inserts an Element of primitive type byte to any Index in an Array of
	 * primitive byte
	 * 
	 * @param array
	 * @param obj
	 * @param index
	 * @return
	 */
	public static byte[] insert(byte[] array, byte obj, int index) {
		if (index > array.length) {
			throw new ArrayIndexOutOfBoundsException(
					"cannot write object to index" + index + ", lenght of Array is only" + array.length);
		}
		byte[] result = new byte[array.length + 1];
		for (int i = 0; i < index; i++) {
			result[i] = array[i];
		}
		for (int i = index; i < array.length; i++) {
			result[i + 1] = array[i];
		}
		result[index] = obj;
		return result;

	}
}
