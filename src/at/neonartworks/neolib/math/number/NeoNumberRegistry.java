package at.neonartworks.neolib.math.number;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NeoNumberRegistry {
	/**
	 * A Map containig all NeoNumbers
	 */
	private static final Map<Class<?>, INeoNumber<?>> registryNamespace = new HashMap<Class<?>, INeoNumber<?>>();
	static{
		register(new NeoNumberByte((byte) 0));
	}
	/**
	 * Register a new NeoNumber
	 * 
	 * @param num
	 *            An instance of the NeoNumber class you want to register
	 * @return true if the class wasn't registered yet, false if it was
	 */
	public static boolean register(INeoNumber<?> num) {
		if (!registryNamespace.containsKey(num.getClass())) {
			registryNamespace.put(num.getClass(), num);
			return true;
		}
		return false;
	}

	public static boolean isRegistered(INeoNumber<?> number) {
		return isRegistered(number.getClass());
	}

	public static boolean isRegistered(Class<?> c) {
		return registryNamespace.containsKey(c);
	}

	public static Collection<INeoNumber<?>> getAllNumbers() {
		return registryNamespace.values();
	}
}
