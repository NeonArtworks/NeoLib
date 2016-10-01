package at.neonartworks.neolib.math;

public class NeoPoint {

	/**
	 * 
	 * @author Florian Wagner Stores a x and a y value for point calculations
	 * 
	 */
	private double x, y;

	/**
	 * @param xkoord
	 * @param ykoord
	 */
	public NeoPoint(double xkoord, double ykoord) {
		this.x = xkoord;
		this.y = ykoord;
	}

	/**
	 * @return the x - Value
	 */

	public double getX() {
		return x;
	}

	/**
	 * @return the y - Value
	 */

	public double getY() {
		return y;
	}
}