package at.neonartworks.neolib.util;

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
		@SuppressWarnings("unchecked")
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

	/**
	 * Inserts an Element of primitive type int to any Index in an Array of
	 * primitive int
	 * 
	 * @param array
	 * @param obj
	 * @param index
	 * @return
	 */
	public static int[] insert(int[] array, int obj, int index) {
		if (index > array.length) {
			throw new ArrayIndexOutOfBoundsException(
					"cannot write object to index" + index + ", lenght of Array is only" + array.length);
		}
		int[] result = new int[array.length + 1];
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
	 * Inserts an Element of primitive type long to any Index in an Array of
	 * primitive long
	 * 
	 * @param array
	 * @param obj
	 * @param index
	 * @return
	 */
	public static long[] insert(long[] array, long obj, int index) {
		if (index > array.length) {
			throw new ArrayIndexOutOfBoundsException(
					"cannot write object to index" + index + ", lenght of Array is only" + array.length);
		}
		long[] result = new long[array.length + 1];
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
	 * Inserts an Element of primitive type char to any Index in an Array of
	 * primitive char
	 * 
	 * @param array
	 * @param obj
	 * @param index
	 * @return
	 */
	public static char[] insert(char[] array, char obj, int index) {
		if (index > array.length) {
			throw new ArrayIndexOutOfBoundsException(
					"cannot write object to index" + index + ", lenght of Array is only" + array.length);
		}
		char[] result = new char[array.length + 1];
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
	 * Appends one Object to an existing array. The original array will not be
	 * modified.
	 * 
	 * @param array
	 *            The array on which the object should be appended
	 * @param obj
	 *            The object which will appended on the end of the array
	 * @return A new Array with length of the original Array + 1 and the object
	 *         to append at the last index
	 */
	public static <T> T[] append(T[] array, T obj) {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[array.length + 1];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i];
		}
		result[result.length - 1] = obj;
		return result;
	}
	
	/**
	 * Appends one Int to an existing array. The original array will not be
	 * modified.
	 * 
	 * @param array
	 *            The array on which the object should be appended
	 * @param obj
	 *            The object which will appended on the end of the array
	 * @return A new Array with length of the original Array + 1 and the object
	 *         to append at the last index
	 */
	public static int[] append(int[] array, int obj) {
		int[] result = new int[array.length + 1];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i];
		}
		result[result.length - 1] = obj;
		return result;
	}
	
	/**
	 * Appends one byte to an existing array. The original array will not be
	 * modified.
	 * 
	 * @param array
	 *            The array on which the object should be appended
	 * @param obj
	 *            The object which will appended on the end of the array
	 * @return A new Array with length of the original Array + 1 and the object
	 *         to append at the last index
	 */
	public static byte[] append(byte[] array, byte obj) {
		byte[] result = new byte[array.length + 1];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i];
		}
		result[result.length - 1] = obj;
		return result;
	}
	
	/**
	 * Appends one long to an existing array. The original array will not be
	 * modified.
	 * 
	 * @param array
	 *            The array on which the object should be appended
	 * @param obj
	 *            The object which will appended on the end of the array
	 * @return A new Array with length of the original Array + 1 and the object
	 *         to append at the last index
	 */
	public static long[] append(long[] array, long obj) {
		long[] result = new long[array.length + 1];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i];
		}
		result[result.length - 1] = obj;
		return result;
	}
	
	/**
	 * Appends one char to an existing array. The original array will not be
	 * modified.
	 * 
	 * @param array
	 *            The array on which the object should be appended
	 * @param obj
	 *            The object which will appended on the end of the array
	 * @return A new Array with length of the original Array + 1 and the object
	 *         to append at the last index
	 */
	public static char[] append(char[] array, char obj) {
		char[] result = new char[array.length + 1];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i];
		}
		result[result.length - 1] = obj;
		return result;
	}
}
