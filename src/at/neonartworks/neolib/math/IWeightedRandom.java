package at.neonartworks.neolib.math;

public interface IWeightedRandom<W, E> {
	void add(W weight, E element);
	E next();
}
