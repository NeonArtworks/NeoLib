package at.neonartworks.neolib.math;

import java.math.BigDecimal;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

/**
 * A Class for generating one element out of a List. Each Element can have a
 * Weight. The percentage chance to get one Object is: ObjectWeight/sumWeight.
 * The only Difference between <code> WeightedRandomAccurate </code> and
 * <code> WeightedRandom </code> is that <code> WeightedRandomAccurate </code>
 * uses BigDecimal as Weight, so it is totally accurate. This will only make a
 * difference if you have very small weights for some objects, but a huge total
 * weight. These small Objects will have a chance of 10^-14% to be returned, so
 * normally you don't need a <code> WeightedRandomAccurate </code>. <br>
 * If you don't need super high accuracy, use <code> WeightedRandom </code>
 * instead, because it is much faster.
 * 
 * @author Alexander Daum
 *
 * @param <E>
 */
public class WeightedRandomAccurate<E> implements IWeightedRandom<BigDecimal, E> {
	private final NavigableMap<BigDecimal, E> map = new TreeMap<BigDecimal, E>();
	private final BigRandom random;
	private BigDecimal total = BigDecimal.ZERO;
	
	public WeightedRandomAccurate(BigRandom random) {
		this.random = random;
	}

	public WeightedRandomAccurate() {
		this.random = new BigRandom();
	}

	@Override
	public void add(BigDecimal weight, E element) {
		// TODO Auto-generated method stub

	}

	@Override
	public E next() {
		// TODO Auto-generated method stub
		return null;
	}

}
