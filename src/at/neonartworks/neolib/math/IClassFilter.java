package at.neonartworks.neolib.math;

public interface IClassFilter {
	boolean isInstance(Object o);

	boolean whitelist(Class<?> c);

	void add(Class<?>... classes);
}
