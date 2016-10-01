package at.neonartworks.neolib.math;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

/**
 * A Class for generating one element out of a List. Each Element can have a
 * Weight. The percentage chance to get one Object is: ObjectWeight/sumWeight.
 * It is only accurate when the weight of the Object * 10^16 is bigger than the
 * total Weight, if you need such giant Randoms use: <code> AccurateWeightedRandom </code>
 * 
 * @author Alexander Daum
 *
 * @param <E>
 */
public class WeightedRandom<E> implements IWeightedRandom<Double, E>{
	private final NavigableMap<Double, E> map = new TreeMap<Double, E>();
	private final Random random;
	private double total = 0;

	public WeightedRandom() {
		this(new Random());
	}

	public WeightedRandom(Random random) {
		this.random = random;
	}

	public void add(Double weight, E result) {
		if (weight <= 0)
			return;
		total += weight;
		map.put(total, result);
	}

	public E next() {
		double value = random.nextDouble() * total;
		return map.ceilingEntry(value).getValue();
	}
}