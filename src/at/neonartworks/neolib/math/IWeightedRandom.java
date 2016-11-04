package at.neonartworks.neolib.math;

/**
 * An Interface for WeightedRandom Algorythms. Typically a Class which
 * implements this, will define the Weight Type, but will also have generic
 * Parameters for the Element Type, except only one type of Element (e.g.
 * String) should be stored
 * 
 * @author Alexander
 *
 * @param <W>
 *            The Weight Type
 * @param <E>
 *            The Element Type
 */
public interface IWeightedRandom<W, E> {
	/**
	 * Adds an Element with a specific Weight. The Weight defines how often this
	 * Element is returned by next()
	 * 
	 * @param weight
	 *            the Weight
	 * @param element
	 *            The Element
	 */
	void add(W weight, E element);

	/**
	 * Generates a random Element and returnes it. It will return one Element
	 * previously added by <code>add(weight, element)</code>
	 * 
	 * @return A random element.
	 */
	E next();
}
