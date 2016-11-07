package at.neonartworks.neolib.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Test;

import at.neonartworks.neolib.math.NeoComplex;
import at.neonartworks.neolib.math.NeoPoint;
import at.neonartworks.neolib.util.CollectionUtil;

public class TestNeoLib2 {

	@Test
	public void testCollectionUtil() {
		Collection<Object> c = new HashSet<Object>();
		c.add("ab");
		c.add("ode");
		c.add(new NeoPoint());
		c.add(new NeoComplex(2, 3));
		c.add(2);
		Collection<Object> equalsAB = CollectionUtil.getAll(o -> o.equals("ab"), c);
		Collection<Object> onlyAB = new HashSet<Object>();
		onlyAB.add("ab");
		assertEquals(onlyAB, equalsAB);
		Collection<Object> allStrings = new HashSet<Object>();
		allStrings.add("ab");
		allStrings.add("ode");
		assertEquals(allStrings, CollectionUtil.getAllClassEqual(String.class, c));
		
	}

}
