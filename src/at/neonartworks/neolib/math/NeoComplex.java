package at.neonartworks.neolib.math;

/**
 * 
 * Complex Class
 * 
 * @author Florian Wagner
 *
 */

public class NeoComplex {

	private double re;
	private double im;

	/**
	 * Constructor
	 * 
	 * @param real
	 * @param imag
	 */

	public NeoComplex(double real, double imag) {
		re = real;
		im = imag;

	}

	/**
	 * Constructor
	 * 
	 * @param NeoComplex
	 */
	public NeoComplex(NeoComplex c) {
		re = c.re;
		im = c.im;
	}

	/**
	 * Sets the "real" part of the Complex value.
	 * 
	 * @param real
	 */

	public void setReal(double real) {
		re = real;
	}

	/**
	 * Sets the "imaginary" part of the Complex value.
	 * 
	 * @param imag
	 */
	public void setImag(double imag) {
		im = imag;
	}

	/**
	 * Gets the "real" part of the Complex value.
	 * 
	 * @return
	 */

	public double getReal() {
		return this.re;
	}

	/**
	 * Gets the "imaginary" part of the Complex value.
	 * 
	 * @return
	 */
	public double getImag() {
		return this.im;
	}

	/**
	 * Returns the current Complex value as new Complex.
	 * 
	 * @return
	 */

	public NeoComplex getComplex() {
		return new NeoComplex(re, im);
	}

	/**
	 * 
	 * Returns the distance from the origin in polar coordinates.
	 * 
	 * @return
	 */

	public double mod() {
		if (re != 0 || im != 0) {
			return Math.sqrt(re * re + im * im);
		} else {
			return 0d;
		}
	}

	public static double mod(NeoComplex c) {
		if (c.re != 0 || c.im != 0) {
			return Math.sqrt(c.re * c.re + c.im * c.im);
		} else {
			return 0d;
		}
	}

	/**
	 * 
	 * @return the current NeoComplex as String
	 */

	public String toString() {
		if (re == 0) {
			return "/" + String.valueOf(im) + "i";
		}
		if (im == 0) {
			return String.valueOf(re) + "/" + "i";
		}
		if (im < 0) {
			return String.valueOf(re) + "+" + "(" + String.valueOf(im) + "i"
					+ ")";
		} else {
			return String.valueOf(re) + "+" + String.valueOf(im) + "i";
		}
	}

	/**
	 * Returns the absolute value of the Complex
	 * 
	 * @return
	 */

	public double abs() {
		return Math.hypot(re, im);
	}

	/**
	 * Returns the absolute value of the Complex
	 * 
	 * @return
	 */

	public static double abs(NeoComplex c) {
		return Math.hypot(c.re, c.im);
	}

	/**
	 * Returns the angle of the Complex value (normalized)
	 * 
	 * @return
	 */
	public double angle() {
		return Math.atan2(re, im);
	}

	/**
	 * Returns the angle of the Complex value (normalized)
	 * 
	 * @return
	 */

	public static double angle(NeoComplex c) {
		return Math.atan2(c.re, c.im);
	}

	/**
	 * Adds the NeoComplex c to the current NeoComplex
	 * 
	 * @param c
	 * @return new NeoComplex
	 */
	public NeoComplex add(NeoComplex c) {
		NeoComplex cur = this;
		double real = cur.re + c.re;
		double imag = cur.im + c.im;
		return new NeoComplex(real, imag);

	}

	/**
	 * Subtracts the NeoComplex c from the current NeoComplex
	 * 
	 * @param c
	 * @return new NeoComplex
	 */
	public NeoComplex sub(NeoComplex c) {
		NeoComplex cur = this;
		double real = cur.re - c.re;
		double imag = cur.im - c.im;
		return new NeoComplex(real, imag);

	}

	/**
	 * Multiplys the current NeoComplex with the given NeoComplex c
	 * 
	 * @param c
	 * @return
	 */

	public NeoComplex multiply(NeoComplex c) {
		NeoComplex cur = this;
		double real = cur.re * c.re - cur.im * c.im;
		double imag = cur.re * c.im + cur.im * c.re;
		return new NeoComplex(real, imag);

	}

	/**
	 * Divides the current NeoComplex with the given NeoComplex c
	 * 
	 * @param c
	 * @return
	 */
	public NeoComplex divide(NeoComplex c) {
		NeoComplex cur = this;
		double den = Math.pow(c.mod(), 2);
		return new NeoComplex((cur.re * c.re + cur.im * c.im) / den, (cur.im
				* c.re - cur.re * c.im)
				/ den);
	}

	/**
	 * Multiply the "real" and the "imaginary" part of the Input c to the
	 * current "real" and "imaginary" NeoComplex.
	 * 
	 * @param c
	 * @return
	 */

	public NeoComplex times(NeoComplex c) {
		NeoComplex cur = this;
		double real = cur.re * c.re;
		double imag = cur.im * c.im;
		return new NeoComplex(real, imag);

	}

	/**
	 * Generates a hashCode from the NeoComplex.
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(im);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(re);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Checks if the Object given by obj equal.
	 * 
	 * @param oboj
	 * @return true;false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NeoComplex other = (NeoComplex) obj;
		if (Double.doubleToLongBits(im) != Double.doubleToLongBits(other.im))
			return false;
		if (Double.doubleToLongBits(re) != Double.doubleToLongBits(other.re))
			return false;
		return true;
	}

}