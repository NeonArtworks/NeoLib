package at.neonartworks.neolib.math;

import java.util.function.DoubleFunction;

public enum RoundingContext {
	roundTowardsZero(value -> {
		long tmp = Math.round(value);
		if (value > 0 && value < tmp) {
			tmp -= 1L;
		} else if (value < 0 && value > tmp) {
			tmp += 1L;
		}
		return tmp;
	}),
	roundToNearest(Math::round),
	roundTowardsPositive(value -> {
		long tmp = Math.round(value);
		if (value > tmp) {
			tmp += 1L;
		}
		return tmp;
	}),
	roundTowardsNegative(value -> {
		long tmp = Math.round(value);
		if (value < tmp) {
			tmp -= 1L;
		}
		return tmp;
	});

	private final DoubleFunction<Long> roundingFuction;

	private RoundingContext(DoubleFunction<Long> roundingFunction) {
		this.roundingFuction = roundingFunction;
	}

	public long round(double value) {
		return roundingFuction.apply(value);
	}
}
