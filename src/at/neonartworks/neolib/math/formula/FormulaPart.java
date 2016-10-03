package at.neonartworks.neolib.math.formula;

public interface FormulaPart<E> {
	/**
	 * Calculates a double from multiple Objects
	 * 
	 * @param args
	 * @return
	 */
	E calculate(Object... args);
}
