package at.neonartworks.neolib.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Test;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import at.neonartworks.neolib.exceptions.OverflowException;
import at.neonartworks.neolib.math.BigRandom;
import at.neonartworks.neolib.math.NeoMath;
import at.neonartworks.neolib.math.NeoRandom;
import at.neonartworks.neolib.math.number.INeoNumber;
import at.neonartworks.neolib.math.number.NeoNumberByte;
import at.neonartworks.neolib.math.number.NeoNumberRegistry;
import at.neonartworks.neolib.password.NeoPassword;

public class TestNeoLib {

	@Test
	public void test() {
		// testBigDecRandom();
		// testBigIntRandom();
	}

	@Test
	public void testBigIntRandom() {
		BigInteger min = new BigInteger("-1000000000000000000000000000000");
		BigInteger max = new BigInteger("1000000000000000000000000000000");
		BigRandom r = new BigRandom().setDefaultFrom(min).setDefaultTo(max);
		// r.setThreadSafe(true);
		for (int i = 0; i < 10000; i++) {
			BigInteger random = r.nextBInt();
			// System.out.println(random);
			if (random.compareTo(min) < 0 || random.compareTo(max) > 0) {
				fail("BigInt " + random + " not in range");
			}
		}
	}

	@Test
	public void testBigDecRandom() {
		BigDecimal min = new BigDecimal("-12345678901234567890.123456789");
		BigDecimal max = new BigDecimal("12345678901234567890.123456789");
		BigRandom r = new BigRandom().setDefaultFrom(min).setDefaultTo(max);
		for (int i = 0; i < 10000; i++) {
			BigDecimal random = r.nextBDecimal(2);
			// System.out.println(random);
			if (random.compareTo(min) < 0 || random.compareTo(max) > 0) {
				fail("BigDecimal " + random + " not in range");
			}
		}
	}

	@Test
	public void testFillLong() {
		long l1 = 123;
		String l1S = NeoMath.fillLongWith0(l1, 19);
		assertEquals("0000000000000000123", l1S);
	}

	@Test
	public void testPw() {
		NeoPassword pw = new NeoPassword();
		String orig = pw.generatePassword(25);
		pw.setPassword(orig);
		System.out.println(orig);
		String encrypt = pw.encrypt(orig);
		System.out.println(encrypt);
		String decrypt = pw.decrypt(encrypt);
		System.out.println(decrypt);
		assertEquals(orig, decrypt);

	}

	@Test
	public void testNeoNumber() {
		INeoNumber<Byte> num = new NeoNumberByte("25");
		num = num.add(num);
		assertEquals(50, num.getValue().byteValue());
		assertEquals(Byte.class, num.getType());
		num = num.multiply(new NeoNumberByte((byte) -1));
		assertEquals(-50, num.getValue().byteValue());
		boolean crashed = false;
		try {
			num.multiply(new NeoNumberByte((byte) 50));
		} catch (OverflowException e) {
			crashed = true;
		}
		assertEquals(true, crashed);
	}

	@Test
	public void testNeoNumberRegistry() {
		assertEquals(true, NeoNumberRegistry.isRegistered(NeoNumberByte.class));
		assertEquals(true, NeoNumberRegistry.isRegistered(new NeoNumberByte((byte) 0)));
		INeoNumber<Byte> nn = new NeoNumberByte((byte) 0);
		assertEquals(true, NeoNumberRegistry.isRegistered(nn));
	}

	@Test
	public void testFastFloor() {
		float[] fa = new float[100];
		float[] floorFast = new float[100];
		float[] floorNormal = new float[100];
		NeoRandom r = new NeoRandom();
		r.setDefaultFrom(-10000);
		r.setDefaultTo(10000);
		for (int i = 0; i < fa.length; i++) {
			fa[i] = r.nextFloat(2);
		}
		long tStart = System.nanoTime();
		for (int i = 0; i < fa.length; i++) {
			floorFast[i] = NeoMath.fastfloor(fa[i]);
		}
		System.out.println("fast: " + (System.nanoTime() - tStart));
		tStart = System.nanoTime();
		for (int i = fa.length - 1, j = 0; i >= 0; i--, j++) {
			floorNormal[j] = (float) Math.floor(fa[j]);
		}
		System.out.println("normal: " + (System.nanoTime() - tStart));
		assertEquals(true, Arrays.equals(floorFast, floorNormal));
	}

}
