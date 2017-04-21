package at.neonartworks.neolib.math;

import java.util.List;

/**
 * A Class representing a Quaternion. A Quaternion is a 4-dimensional Vector
 * space with the form x0 + x1*i + x2*j + x3*k. The values are called r, i, j
 * and k in this Class. This Class allows Basic Math with Quaternions. <br>
 * The multiplications follow the following rules <br>
 * i² = j² = k² = ijk = -1<br>
 * ij = k, jk = i, ki = j <br>
 * ji = -k
 * 
 * @author Alexander Daum
 *
 */
public class NeoQuaternion implements Cloneable, BasicMath<NeoQuaternion> {
	/**
	 * The NeoQuaternion 0,0,0,0.
	 */
	public static final NeoQuaternion ZERO;
	/**
	 * The NeoQuaternion 1,0,0,0.
	 */
	public static final NeoQuaternion ONE;
	
	static {
		ZERO = new NeoQuaternion();
		ONE = new NeoQuaternion(1D, 0D, 0D, 0D);
	}
	private final double r, i, j, k;
	
	/**
	 * Constructs a new NeoQuaternion with the specified r, i, j, k Values
	 * 
	 * @param r
	 *            the real part
	 * @param i
	 *            the first imaginary part i
	 * @param j
	 *            the second imaginary part j
	 * @param k
	 *            the third imaginary part k
	 */
	public NeoQuaternion(double r, double i, double j, double k) {
		this.r = r;
		this.i = i;
		this.j = j;
		this.k = k;
	}
	
	public NeoQuaternion(double[] val) {
		if (val.length != 4) {
			throw new IllegalArgumentException("NeoQuaternion needs 4 values in input Array!");
		}
		this.r = val[0];
		this.i = val[1];
		this.j = val[2];
		this.k = val[3];
	}
	
	public NeoQuaternion(List<Double> val) {
		if (val.size() != 4) {
			throw new IllegalArgumentException("NeoQuaternion needs 4 values in input List!");
		}
		this.r = val.get(0);
		this.i = val.get(1);
		this.j = val.get(2);
		this.k = val.get(3);
	}
	
	/**
	 * Constructs a new NeoQuaternion with all values 0
	 */
	public NeoQuaternion() {
		this(0D, 0D, 0D, 0D);
	}
	
	@Override
	public NeoQuaternion add(NeoQuaternion o) {
		return new NeoQuaternion(r + o.r, i + o.i, j + o.j, k + o.k);
	}
	
	@Override
	public NeoQuaternion subtract(NeoQuaternion o) {
		return new NeoQuaternion(r - o.r, i - o.i, j - o.j, k - o.k);
	}
	
	@Override
	public Object clone() {
		return new NeoQuaternion(r, i, j, k);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(i);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(j);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(k);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(r);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NeoQuaternion other = (NeoQuaternion) obj;
		if (Double.doubleToLongBits(i) != Double.doubleToLongBits(other.i))
			return false;
		if (Double.doubleToLongBits(j) != Double.doubleToLongBits(other.j))
			return false;
		if (Double.doubleToLongBits(k) != Double.doubleToLongBits(other.k))
			return false;
		if (Double.doubleToLongBits(r) != Double.doubleToLongBits(other.r))
			return false;
		return true;
	}
	
	@Override
	public NeoQuaternion multiply(NeoQuaternion o) {
		double nR, nI, nJ, nK;
		nR = r * o.r - i * o.i - j * o.j - k * o.k;
		nI = r * o.i + i * o.r + j * o.k - k * o.j;
		nJ = r * o.j + j * o.r + k * o.i - i * o.k;
		nK = r * o.k + k * o.r + i * o.j - j * o.i;
		return new NeoQuaternion(nR, nI, nJ, nK);
	}
	
	@Override
	public NeoQuaternion divide(NeoQuaternion o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public NeoQuaternion abs() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public NeoQuaternion invert() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public NeoQuaternion get() {
		return this;
	}
	
	@Override
	public NeoQuaternion multiply(double o) {
		return new NeoQuaternion(r * o, i * o, j * o, k * o);
	}
}
