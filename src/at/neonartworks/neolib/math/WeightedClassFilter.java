package at.neonartworks.neolib.math;

import java.util.HashMap;

public class WeightedClassFilter implements IClassFilter {
	private HashMap<Class<?>, Integer> classes = new HashMap<Class<?>, Integer>();

	@Override
	public boolean isInstance(Object o) {
		return classes.containsKey(o.getClass());
	}

	@Override
	public boolean whitelist(Class<?> c) {
		return classes.containsKey(c);
	}

	public WeightedClassFilter(Class<?>[] classes, int[] weights) {
		if (classes.length != weights.length) {
			throw new IllegalArgumentException("Different ammount of classes and weights");
		}
		for (int i = 0; i < classes.length; i++) {
			this.classes.put(classes[i], weights[i]);
		}
	}

	@Override
	public void add(Class<?>... classes) {

	}

	public int getWeight(Class<?> c) {
		return classes.get(c);
	}

	public int getWeight(Object o) {
		return classes.get(o.getClass());
	}

	@Override
	public void remove(Class<?>... classes) {

	}

}
