package at.neonartworks.neolib.math;

public class NeoPoint {
	private double x, y;
	
    public NeoPoint (double xkoord, double ykoord) {
        this.x = xkoord;
        this.y = ykoord;
    }
    
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
}