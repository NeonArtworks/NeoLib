package at.neonartworks.neolib.math.number;

import at.neonartworks.neolib.exceptions.OverflowException;
import at.neonartworks.neolib.math.NeoMath;

public class NeoNumberByte implements INeoNumber<Byte> {
	private final byte value;

	public NeoNumberByte(byte b) {
		this.value = b;
	}

	public NeoNumberByte(INeoNumber<?> value) {
		this(parse(value.toString()).value);
	}

	/**
	 * Parses a NeoNumberByte from a String, if the String value is not in Range
	 * of an Byte, an Overflow Exception is thrown
	 * 
	 * @param s
	 *            The String value
	 */
	public static NeoNumberByte parse(String s) {
		byte b;
		try {
			b = Byte.parseByte(s);
		} catch (NumberFormatException e) {
			throw new OverflowException("Value" + s + "is not a Byte", e);
		}
		return new NeoNumberByte(b);
	}

	@Override
	public NeoNumberByte add(INeoNumber<?> other) {
		NeoNumberByte otherAsNum = new NeoNumberByte(other);
		byte otherVal = otherAsNum.value;
		boolean canAdd = (otherVal >= 0) ? leftToTop() >= otherVal : leftToBottom() >= otherAsNum.abs().value;
		if (!canAdd) {
			throw new OverflowException("Overflow when adding " + other + " to " + this + " As Byte");
		}
		return new NeoNumberByte((byte) (otherAsNum.value + this.value));
	}

	@Override
	public NeoNumberByte multiply(INeoNumber<?> other) {
		NeoNumberByte otherAsNum = new NeoNumberByte(other);
		byte otherVal = otherAsNum.value;
		// beide Gleiches VZ -> ergebnis +. Also muss this.abs <= max/other.abs
		// sein.
		// beide Unterschiedliches VZ -> ergebnis -. Also muss this.abs <=
		// min/other.abs sein.
		boolean hasSign = hasSameSign(otherAsNum);
		byte compareValue = (byte) ((hasSign ? max() : min()) / otherAsNum.abs().value);
		compareValue = NeoMath.absByte(compareValue);
		boolean canMult = this.abs().value <= compareValue;
		if (!canMult) {
			throw new OverflowException("Overflow when multiplying " + other + " with " + this + " As Byte");
		}

		return new NeoNumberByte((byte) (otherVal * this.value));
	}

	@Override
	public NeoNumberByte divide(INeoNumber<?> other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Byte leftToTop() {
		return (byte) (Byte.MAX_VALUE - value);
	}

	@Override
	public Byte leftToBottom() {
		return (byte) (Byte.MIN_VALUE + value);
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@Override
	public Class<?> getType() {
		return Byte.class;
	}

	@Override
	public NeoNumberByte abs() {
		return new NeoNumberByte(NeoMath.absByte(value));
	}

	@Override
	public Byte getValue() {
		return value;
	}

	@Override
	public Byte max() {
		return Byte.MAX_VALUE;
	}

	@Override
	public Byte min() {
		return Byte.MIN_VALUE;
	}

	@Override
	public boolean hasSameSign(INeoNumber<?> other) {
		NeoNumberByte otherAsNum = new NeoNumberByte(other);
		return this.value >= 0 ? otherAsNum.getValue() >= 0 : otherAsNum.getValue() < 0;
	}

	@Override
	public int getAccurateDecimals() {
		return 0;
	}

}
