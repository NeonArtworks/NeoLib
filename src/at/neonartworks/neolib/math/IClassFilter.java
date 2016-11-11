package at.neonartworks.neolib.math;

/**
 * 
 * @author Alexander Daum
 *
 */
public interface IClassFilter {
	/**
	 * Checks if the object is an instance of this class
	 * 
	 * @param o
	 *            the object to be checked
	 * @return true if the object is instance of the class
	 */
	boolean isInstance(Object o);

	/**
	 * Checks if the class is on the whitelist
	 * 
	 * @param c
	 * 
	 */
	boolean whitelist(Class<?> c);

	/**
	 * Adds a class to the whitelist, so for objects which are instance of this
	 * class the method {@link isInstance} will return <code>true</code>
	 * 
	 * @param classes
	 *            The classes to be added to the whitelist
	 * @return
	 */
	void add(Class<?>... classes);

	/**
	 * Removes a class to the whitelist, so for objects which are instance of this
	 * class the method {@link isInstance} will return <code>false</code>
	 * 
	 * @param classes
	 *            The classes to be removed from the whitelist
	 * @return
	 */
	void remove(Class<?>... classes);
}
