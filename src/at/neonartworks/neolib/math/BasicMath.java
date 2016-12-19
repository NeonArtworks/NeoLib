package at.neonartworks.neolib.math;

public interface BasicMath<T> {
	/**
	 * Adds the other Number to this Number. This Number will not be modified
	 * 
	 * @param o
	 *            The Number to be added
	 * @return The sum
	 */
	T add(T o);

	/**
	 * Subtracts the other Number from this Number. This Number will not be
	 * modified
	 * 
	 * @param o
	 *            The Number to be subracted
	 * @return The sum
	 */
	T subtract(T o);

	/**
	 * Multiplies the other Number with this Number. This Number will not be
	 * modified. If the Number is a Quaternion or even Higher dimensional
	 * x.multiply(y) is not the same as y.multiply(x).
	 * 
	 * @param o
	 *            The Number to be multiplied with
	 * @return The sum
	 */
	T multiply(T o);

	/**
	 * Divides this Number by the other Number. This Number will not be modified
	 * 
	 * @param o
	 *            The Number to be subtracted with
	 * @return The sum
	 */
	T divide(T o);

	/**
	 * Calculates the absolute value of this Number.
	 * 
	 * @return
	 */
	T abs();

	/**
	 * Calculates the inverted Number, 1/x
	 * 
	 * @return
	 */
	T invert();

	/**
	 * Returns the current Number for work in Methods which just know the
	 * BasicMath Object not the Number itself.
	 * 
	 * @return
	 */
	T get();

	/**
	 * Calculates the square x²
	 * 
	 * @return
	 */
	default T sqare() {
		return this.multiply(this.get());
	}

	/**
	 * Does scalar Multiplication on this Number, only is different from
	 * multiply if this Number is at least a Complex Number
	 * 
	 * @param o
	 * @return
	 */
	T multiply(double o);
}
