package at.neonartworks.neolib.math.number;

import at.neonartworks.neolib.exceptions.OverflowException;
import at.neonartworks.neolib.math.NeoMath;

public class NeoNumberShort implements INeoNumber<Short> {
	
	public static NeoNumberShort ShortZERO = new NeoNumberShort((short) 0);
	private final short value;

	public NeoNumberShort(short value) {
		this.value = value;
	}

	public NeoNumberShort(String s) {
		short sValue;
		try {
			sValue = Short.parseShort(s);
		} catch (NumberFormatException e) {
			throw new OverflowException("Value" + s + "is not a Short", e);
		}
		this.value = sValue;
	}

	public NeoNumberShort(INeoNumber<?> value) {
		this(value.toString());
	}

	@Override
	public NeoNumberShort add(INeoNumber<?> other) {
		NeoNumberShort otherShort = new NeoNumberShort(other);
		short otherVal = otherShort.value;
		boolean canAdd = (otherVal >= 0) ? leftToTop() >= otherVal : leftToBottom() >= otherShort.abs().getValue();
		if (!canAdd) {
			throw new OverflowException("Cannot add " + other + " to short value " + this);
		}
		return new NeoNumberShort((short) (this.value + otherShort.value));
	}

	@Override
	public NeoNumberShort multiply(INeoNumber<?> other) {
		NeoNumberShort otherShort = new NeoNumberShort(other);
		short otherVal = otherShort.value;
		boolean sameSign = this.hasSameSign(other);
		short compare = (short) ((sameSign ? max() : min()) / otherShort.abs().value);
		compare = NeoMath.absShort(compare);
		boolean canMult = compare >= this.abs().value;
		if(!canMult){
			throw new OverflowException("Cannot multiply short " + this + " with " + other);
		}
		short mult = (short) (this.value * otherShort.value);
		return new NeoNumberShort(mult);
	}

	@Override
	public NeoNumberShort divide(INeoNumber<?> other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NeoNumberShort abs() {
		return new NeoNumberShort(NeoMath.absShort(value));
	}

	@Override
	public Short getValue() {
		return value;
	}

	@Override
	public Short leftToTop() {
		return (short) (max() - value);
	}

	@Override
	public Short leftToBottom() {
		return (short) -(min() - value);
	}

	@Override
	public Short max() {
		return Short.MAX_VALUE;
	}

	@Override
	public Short min() {
		return Short.MIN_VALUE;
	}

	@Override
	public int getAccurateDecimals() {
		return 0;
	}

	@Override
	public Class<?> getType() {
		return Short.class;
	}

	@Override
	public boolean hasDecimals() {
		return false;
	}

	@Override
	public Sign getSign() {
		return value < 0 ? Sign.MINUS : Sign.PLUS;
	}

	@Override
	public INeoNumber<?> divideAccurate(INeoNumber<?> other, int accuracy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public INeoNumber<Short> valueZero() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@Override
	public int hashCode() {
		return Short.hashCode(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NeoNumberShort other = (NeoNumberShort) obj;
		return value == other.value;
	}

}
