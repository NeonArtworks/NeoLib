package at.neonartworks.neolib.math;

/**
 * An Implementation of AbstractVariable and thus of IVariable, which uses
 * String as an Identifier, normally this is the best option
 * 
 * @author Alexander
 *
 */
public class Variable extends AbstractVariable<String> {
	private final double value;
	private final String identifier;

	public Variable(String id, double val) {
		this.value = val;
		this.identifier = id;
	}

	public Variable(String id) {
		this.value = 0.0D;
		this.identifier = id;
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public double getValue() {
		return value;
	}

}
