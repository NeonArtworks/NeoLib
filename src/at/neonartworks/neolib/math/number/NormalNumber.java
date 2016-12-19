package at.neonartworks.neolib.math.number;

public class NormalNumber extends AbstractNeoNumber {
	private final double value;

	public NormalNumber(double value) {
		this.value = value;
	}

	@Override
	public double getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Double.hashCode(value);
	}

}
