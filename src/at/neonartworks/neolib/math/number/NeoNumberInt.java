package at.neonartworks.neolib.math.number;

import at.neonartworks.neolib.exceptions.OverflowException;
import at.neonartworks.neolib.math.NeoMath;

public class NeoNumberInt implements INeoNumber<Integer> {
	private final int value;
	public static final NeoNumberInt IntZERO = new NeoNumberInt(0);

	public NeoNumberInt(int value) {
		this.value = value;
	}

	public NeoNumberInt(String s) {
		int parse;
		try {
			parse = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			throw new OverflowException(s + " is not an Integer ", e);
		}
		value = parse;
	}

	public NeoNumberInt(INeoNumber<?> other) {
		this(other.toString());
	}

	@Override
	public INeoNumber<Integer> add(INeoNumber<?> other) {
		NeoNumberInt otherAsInt = new NeoNumberInt(other);
		int otherVal = otherAsInt.value;
		boolean canAdd = (this.getSign() == Sign.MINUS ? leftToBottom():leftToTop()) >= otherVal;
		if(!canAdd){
			throw new OverflowException("Cannot add " + other + " to " + this);
		}
		return new NeoNumberInt(this.value + otherVal);
	}

	@Override
	public NeoNumberInt multiply(INeoNumber<?> other) {
		NeoNumberInt otherInt = new NeoNumberInt(other);
		boolean sameSign = this.hasSameSign(other);
		short compare = (short) ((sameSign ? max() : min()) / otherInt.abs().value);
		compare = NeoMath.abs(compare);
		boolean canMult = compare >= this.abs().value;
		if(!canMult){
			throw new OverflowException("Cannot multiply short " + this + " with " + other);
		}
		short mult = (short) (this.value * otherInt.value);
		return new NeoNumberInt(mult);
	}

	@Override
	public INeoNumber<Integer> divide(INeoNumber<?> other) {
		NeoNumberInt otherNum = new NeoNumberInt(other);
		if (otherNum.value == 0) {
			throw new ArithmeticException("Divide by 0");
		}
		byte divVal = (byte) (this.value / otherNum.value);
		return new NeoNumberInt(divVal);
	}

	@Override
	public INeoNumber<?> divideAccurate(INeoNumber<?> other, int accuracy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NeoNumberInt abs() {
		return new NeoNumberInt(NeoMath.abs(value));
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public Integer leftToTop() {
		return max() - value;
	}

	@Override
	public Integer leftToBottom() {
		return -(min() + value);
	}

	@Override
	public Integer max() {
		return Integer.MAX_VALUE;
	}

	@Override
	public Integer min() {
		return Integer.MIN_VALUE;
	}

	@Override
	public NeoNumberInt valueZero() {
		return IntZERO;
	}

	@Override
	public int getAccurateDecimals() {
		return 0;
	}

	@Override
	public boolean hasDecimals() {
		return false;
	}

	@Override
	public Class<?> getType() {
		return Integer.class;
	}

	@Override
	public Sign getSign() {
		return value >= 0 ? Sign.PLUS : Sign.MINUS;
	}

}
