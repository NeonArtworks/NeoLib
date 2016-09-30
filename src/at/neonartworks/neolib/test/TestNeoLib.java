package at.neonartworks.neolib.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

import at.neonartworks.neolib.BigRandom;
import at.neonartworks.neolib.NeoMath;
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
		for (int i = 0; i < 10000; i++) {
			BigInteger random = r.nextBInt();
//			System.out.println(random);
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
//			System.out.println(random);
			if (random.compareTo(min) < 0 || random.compareTo(max) > 0) {
				fail("BigDecimal "+ random +" not in range");
			}
		}
	}
	
	@Test
	public void testFillLong(){
		long l1 = 123;
		String l1S =NeoMath.fillLongWith0(l1);
		assertEquals("0000000000000000123", l1S);
	}
	@Test
	public void testPw(){
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

}
