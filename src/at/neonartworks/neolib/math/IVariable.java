package at.neonartworks.neolib.math;

/**
 * An Interface for Variables. It can be used to create Variables with
 * non-String names. This Variables can be used for almost all NeoMath Classes
 * which use Variables, like <code>NeoComplexVar</code>
 * 
 * @author Alexander Daum
 * @param <I>
 *            The Identifier used for Naming the Variable, normal Variables use
 *            String
 */
public interface IVariable<I> extends Comparable<IVariable<I>>, Cloneable {
	/**
	 * @return The Identifier or name of this Variable
	 */
	I getIdentifier();

	/**
	 * 
	 * @return The double value of this variable
	 */
	double getValue();
}
