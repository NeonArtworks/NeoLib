package at.neonartworks.neolib.math.formula;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

import at.neonartworks.neolib.math.ClassFilter;
import at.neonartworks.neolib.math.NeoMath;

public class NeoFormula<R,P> {
	private FormulaPart fp;
	static final ClassFilter formulaClasses;
	static{
		formulaClasses = NeoMath.calculateable;
	}
	public R Calculate(Map<String, P> variables){
		
	}
}
