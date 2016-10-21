package at.neonartworks.neolib.math;

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
