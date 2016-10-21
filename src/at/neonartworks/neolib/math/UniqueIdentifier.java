package at.neonartworks.neolib.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UniqueIdentifier {

	private static List<Integer> ids = new ArrayList<Integer>();
	private static final int range = 100000;
	private static int index = 0;

	private UniqueIdentifier() {

	}

	static {
		for (int i = 0; i < range; i++) {
			ids.add(i);
		}
		Collections.shuffle(ids);
	}

	/**
	 * Returns an unique Identifier. The range is between 0 and 100000;
	 * 
	 * @return Integer
	 */
	public static int getIdentifier() {
		if (index > ids.size() - 1)
			index = 0;
		return ids.get(index++);
	}
}
