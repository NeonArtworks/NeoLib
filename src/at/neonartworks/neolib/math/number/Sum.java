package at.neonartworks.neolib.math.number;

import java.util.HashSet;

/**
 * A NeoNumber representing a Sum of size() elements. All calculations return a
 * new NormalNumber, except add, it returnes a new Sum with other as an
 * additional element
 * 
 * @author alex
 *
 */
public class Sum extends AbstractNeoNumber {
	private HashSet<AbstractNeoNumber> nums;

	public Sum() {
		nums = new HashSet<>();
	}

	public int size() {
		return nums.size();
	}

	public Sum(HashSet<AbstractNeoNumber> nums) {
		this.nums = new HashSet<>(nums);
	}

	@Override
	public double getValue() {
		double sum = 0;
		for (AbstractNeoNumber n : nums) {
			sum += n.getValue();
		}
		return sum;
	}

	@Override
	public AbstractNeoNumber add(AbstractNeoNumber other) {
		Sum o = new Sum(this.nums);
		o.nums.add(other);
		return o;
	}

	@Override
	public int hashCode() {
		return nums.hashCode();
	}

}
