package at.neonartworks.neolib.math.number;

public class NormalNumber extends AbstractNeoNumber {
	private double value;

	public NormalNumber(double value) {
		this.value = value;
	}

	@Override
	public double getValue() {
		return value;
	}

}
