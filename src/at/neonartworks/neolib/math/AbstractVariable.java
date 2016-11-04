package at.neonartworks.neolib.math;

/**
 * A Class which already Implements some Functions for Variables, such as
 * compareTo, clone, toString, hashCode and equals
 * 
 * @author Alexander Daum
 *
 * @param <I>
 *            he Identifier used for Naming the Variable, normal Variables use
 *            String
 */
public abstract class AbstractVariable<I> implements IVariable<I> {

	@Override
	public int compareTo(IVariable<I> o) {
		return Double.compare(this.getValue(), o.getValue());
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return "[Variable: " + getIdentifier() + " : " + getValue() + "]";
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result += prime * getValue();
		result += prime * getIdentifier().hashCode();
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o == this)
			return true;
		if (o.getClass() != this.getClass())
			return false;
		IVariable<?> other = (IVariable<?>) o;
		if (other.getValue() != this.getValue())
			return false;
		return this.getIdentifier().equals(other.getIdentifier());

	}
}
