package at.neonartworks.neolib.math.number;

/**
 * Interface for all NeoNumber, you can also add your own NeoNumber if you
 * implement this interface and register it in the NeoNumberRegistry.
 * 
 * @author Alexander Daum
 *
 * @param <V>
 *            The Value of the NeoNumber, it is the type used for calculation
 */
public interface INeoNumber<V> {
	/**
	 * Adds an other NeoNumber to this NeoNumber and returns the Value, throws
	 * and OverflowException if the ACtion is not possible without an overflow
	 * 
	 * @param other
	 * @return a new NeoNumber, with the value, this.value + other.value
	 */
	INeoNumber<V> add(INeoNumber<?> other);

	/**
	 * Multiplies this NeoNumber with another one, throws an Overflow exception
	 * if this is not possible
	 * 
	 * @param other
	 * @return a new NeoNumber, with the value, this.value * other.value
	 */
	INeoNumber<V> multiply(INeoNumber<?> other);

	/**
	 * Divides this NeoNumber by another one, throws an Overflow Exception if
	 * the Action is not possible
	 * 
	 * @param other
	 * @return a new NeoNumber, with the value, this.value / other.value
	 */
	INeoNumber<V> divide(INeoNumber<?> other);

	/**
	 * Calculates the Absolute value, returns MaxValue, if MinValue would have
	 * an higher absolute as MaxValue is
	 * 
	 * @return
	 */
	INeoNumber<V> abs();

	/**
	 * Returns the Value of the NeoNumber
	 * 
	 * @return
	 */
	V getValue();

	/**
	 * Returns how much space is left to the top, 0 means, that you cannot add
	 * anything to this anymore
	 * 
	 * @return The space left to the maximum
	 */
	V leftToTop();

	/**
	 * Returns how much space is left to the bottom, 0 means, that you cannot
	 * subtract anything from this anymore
	 * 
	 * @return The space left to the minumum
	 */
	V leftToBottom();

	/**
	 * @return the maximum value
	 */
	V max();

	/**
	 * @return the minimum value
	 */
	V min();

	/**
	 * @return The decimal Accuracy of the Number, that means how many digits
	 *         behind the comma are accurate
	 */
	int getAccurateDecimals();

	/**
	 * @return true if the actual Value of this NeoNumber has numbers behind the
	 *         comma
	 */
	boolean hasDecimals();

	/**
	 * Returns the type of number this represents, for example NeoNumberByte
	 * returnes Byte.class, NeoNumberIn returns Integer.class
	 * 
	 * @return
	 */
	Class<?> getType();

	/**
	 * returns true if other is of the same type like this
	 * 
	 * @param other
	 * @return
	 */
	default boolean hasSameType(INeoNumber<?> other) {
		return this.getType().equals(other.getType());
	}

	/**
	 * Returns true if the sign of this NeoNumber is the same, than the one of
	 * the other
	 * 
	 * @param other
	 * @return
	 */
	default boolean hasSameSign(INeoNumber<?> other) {
		return this.getSign() == other.getSign();
	}

	/**
	 * 
	 * @return The Sign of the Number, as specified in the Enum Sign
	 */
	Sign getSign();

	/**
	 * 
	 * @return The ammount of digits this value has without +/-
	 */
	default int getSize() {
		int length = this.getValue().toString().length();
		return this.getSign() == Sign.PLUS ? length : length - 1;
	}
	/**
	 * 
	 * @return How many digits this Number can maximally hold
	 */
	default int getMaxSize(){
		
	}
}
