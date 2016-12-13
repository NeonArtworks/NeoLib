package at.neonartworks.neolib.math.number;

/**
 * Interface for all NeoNumber, you can also add your own NeoNumber if you
 * implement this interface and register it in the NeoNumberRegistry. NeoNumbers
 * are used for a kind of formulas
 * 
 * @author Alexander Daum
 *
 * @param <V>
 *            The Value of the NeoNumber, it is the type used for calculation
 */
public abstract class AbstractNeoNumber {
	public abstract double getValue();

	public AbstractNeoNumber add(AbstractNeoNumber other) {
		return new NormalNumber(this.getValue() + other.getValue());
	}

	public AbstractNeoNumber mult(AbstractNeoNumber other){
		return new NormalNumber(this.getValue() * other.getValue());
	}

	public AbstractNeoNumber div(AbstractNeoNumber other){
		return new NormalNumber(this.getValue() / other.getValue());
	}
	
	public AbstractNeoNumber abs(){
		return new NormalNumber(Math.abs(this.getValue()));
	}
	
	@Override
	public String toString() {
		return String.valueOf(getValue());
	}
}
