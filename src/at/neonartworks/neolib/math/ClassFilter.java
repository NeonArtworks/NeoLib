package at.neonartworks.neolib.math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ClassFilter implements IClassFilter{
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public ClassFilter(Class<?>... classes) {
		this.classes.addAll(Arrays.asList(classes));
	}

	@Override
	public void add(Class<?>... classes) {
		this.classes.addAll(Arrays.asList(classes));
	}
	
	@Override
	public boolean isInstance(Object o){
		return classes.contains(o.getClass());
	}

	@Override
	public boolean whitelist(Class<?> c) {
		return classes.contains(c);
	}
}
