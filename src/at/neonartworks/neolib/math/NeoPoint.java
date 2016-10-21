package at.neonartworks.neolib.math;

public class NeoPoint {

	/**
	 * Stores a x and a y value for point calculations
	 * 
	 * @author Florian Wagner
	 * 
	 */
	public double x, y;

	/**
	 * @param xkoord
	 * @param ykoord
	 */
	public NeoPoint(double xkoord, double ykoord) {
		this.x = xkoord;
		this.y = ykoord;
	}

	/**
	 * Constructor. Used in NeoPoly
	 */
	public NeoPoint() {

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

	/**
	 * Sets the x value of the point
	 * 
	 * @param px
	 */
	public void setX(double px) {
		this.x = px;
	}

	/**
	 * sets the y value of the point
	 * 
	 * @param py
	 */
	public void setY(double py) {
		this.y = py;
	}

	/**
	 * converts the to points to a String
	 */
	public String toString() {

		return "X: " + String.valueOf(x) + ",Y: " + String.valueOf(y);

	}

	/**
	 * Calculates the distance between the two points given with p1 and p2
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double distance(NeoPoint p1, NeoPoint p2) {
		return (double) Math.sqrt((p1.getX() - p2.getX())
				* (p1.getX() - p2.getX()) + (p1.getY() - p2.getY())
				* (p1.getY() - p2.getY()));
	}
}