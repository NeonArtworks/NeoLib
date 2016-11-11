package at.neonartworks.neolib.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.neonartworks.neolib.util.CollectionUtil;

public class UniqueIdentifier {

	private List<Integer> ids = new ArrayList<Integer>();
	private static final int range = 100000;
	private int index = 0;

	public UniqueIdentifier(int range) {
		CollectionUtil.numbers(0, range, ids);
		Collections.shuffle(ids);
	}

	public UniqueIdentifier() {
		CollectionUtil.numbers(0, range, ids);
		Collections.shuffle(ids);
	}

	/**
	 * Returns an unique Identifier. The range is between 0 and 100000;
	 * 
	 * @return Integer
	 */
	public int getIdentifier() {
		if (index > ids.size() - 1)
			index = 0;
		return ids.get(index++);
	}
}
