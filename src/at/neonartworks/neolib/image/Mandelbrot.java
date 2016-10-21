package at.neonartworks.neolib.image;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Mandelbrot class. This Class allows you to create Mandelbrot and Julia set images.
 * 
 * @author Florian Wagner
 *
 */
public class Mandelbrot {

	private int _iter;
	private double _zoom;
	private int detail;

	private double cREn1, cIMn1, cRE, cIM, cREn1TMP;
	private int xSize, ySize;
	private double offX = 0, offY = 0;
	private BufferedImage image;
	private NeoImage nImage;
	private String color;
	private Color mandColor = new Color(0, 0, 0);;

	/**
	 * Constructor with X-Size, Y-Size, Iterations (should be min. 100), zoom,
	 * and detail level. Only sets the variables! The calculation must be called
	 * with either 'calcMandelbort' or 'getMandelbrot'!
	 * 
	 * @param x
	 * @param y
	 * @param iters
	 * @param size
	 * @param det
	 */

	public Mandelbrot(int x, int y, int iters, int size, int det) {
		nImage = new NeoImage();
		xSize = x;
		ySize = y;
		_iter = iters;
		_zoom = size;
		detail = det;

	}

	/**
	 * Constructor with X-Size, Y-Size, Iterations (should be min. 100), zoom,
	 * detail level and colMode (can either be : r, g, b, lsd). Only sets the
	 * variables! The calculation must be called with either 'calcMandelbort' or
	 * 'getMandelbrot'!
	 * 
	 * @param x
	 * @param y
	 * @param iters
	 * @param size
	 * @param det
	 * @param colMode
	 */

	public Mandelbrot(int x, int y, int iters, int size, int det, String colMode) {
		nImage = new NeoImage();
		xSize = x;
		ySize = y;
		_iter = iters;
		_zoom = size;
		detail = det;
		color = colMode;

	}

	/**
	 * Constructor with X-Size, Y-Size, Iterations (should be min. 100), zoom,
	 * and detail level. *EXTRA* X-Offset and Y-Offset Only sets the variables!
	 * The calculation must be called with either 'calcMandelbort' or
	 * 'getMandelbrot'!
	 * 
	 * @param x
	 * @param y
	 * @param iters
	 * @param size
	 * @param det
	 * @param offx
	 * @param offy
	 */

	public Mandelbrot(int x, int y, int iters, int size, int det, int offx,
			int offy) {
		nImage = new NeoImage();
		xSize = x;
		ySize = y;
		_iter = iters;
		_zoom = size;
		detail = det;
		offX = offx;
		offY = offy;

	}

	/**
	 * Constructor with X-Size, Y-Size, Iterations (should be min. 100), zoom,
	 * detail level and colMode (can either be : r, g, b, lsd). *EXTRA* X-Offset
	 * and Y-Offset Only sets the variables! The calculation must be called with
	 * either 'calcMandelbort' or 'getMandelbrot'!
	 * 
	 * @param x
	 * @param y
	 * @param iters
	 * @param size
	 * @param det
	 * @param offx
	 * @param offy
	 */

	public Mandelbrot(int x, int y, int iters, int size, int det,
			String colMode, int offx, int offy) {
		nImage = new NeoImage();
		xSize = x;
		ySize = y;
		_iter = iters;
		_zoom = size;
		detail = det;
		offX = offx;
		offY = offy;
		color = colMode;

	}

	/**
	 * gets the Iteration from the Mandelbrot.
	 * 
	 * @return iterations
	 */
	public int get_iter() {
		return _iter;
	}

	/**
	 * sets the Iterations for the Mandelbrot.
	 * 
	 * @param _iter
	 */
	public void set_iter(int _iter) {
		this._iter = _iter;
	}

	/**
	 * Gets the zoom level of the Mandelbrot.
	 * 
	 * @return zoom
	 */
	public double get_zoom() {
		return _zoom;
	}

	/**
	 * Sets the zoom level of the Mandelbrot.
	 * 
	 * @param _zoom
	 */
	public void set_zoom(double _zoom) {
		this._zoom = _zoom;
	}

	/**
	 * Gets the detail of the Mandelbrot.
	 * 
	 * @return detail
	 */
	public int getDetail() {
		return detail;
	}

	/**
	 * Sets the detail of the Mandelbrot.
	 * 
	 * @param detail
	 */
	public void setDetail(int detail) {
		this.detail = detail;
	}

	/**
	 * Returns the X-Size of the Mandelbrot. This is the pixel amount in
	 * X-Direction.
	 * 
	 * @return xSize
	 */
	public int getxSize() {
		return xSize;
	}

	/**
	 * Sets the X-Size of the Mandelbrot. This is the pixel amount in
	 * X-Direction.
	 * 
	 * @param xSize
	 */
	public void setxSize(int xSize) {
		this.xSize = xSize;
	}

	/**
	 * Returns the Y-Size of the Mandelbrot. This is the pixel amount in
	 * Y-Direction.
	 * 
	 * @return ySize
	 */
	public int getySize() {
		return ySize;
	}

	/**
	 * Sets the Y-Size of the Mandelbrot. This is the pixel amount in
	 * Y-Direction.
	 * 
	 * @param ySize
	 */
	public void setySize(int ySize) {
		this.ySize = ySize;
	}

	/**
	 * Get the Color String.
	 * 
	 * @return Color String (r, g, b, lsd)
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets the color for the Mandelbrot.
	 * 
	 * @param color
	 *            (r, g, b, lsd)
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * gets the the Color of the actual Mandelbrot (not the surroundings)
	 * 
	 * @return color
	 */
	public Color getMandelbrotColor() {
		return mandColor;
	}

	/**
	 * Sets the the Color of the actual Mandelbrot (not the surroundings)
	 * 
	 * @param mandColor
	 */
	public void setMandelbrotColor(Color mandColor) {
		this.mandColor = mandColor;
	}

	/**
	 * Calculates a Mandelbrot with the defined Variables. This function returns
	 * the Mandelbrot as BufferedImage. If the Image is completely black then
	 * the zoom was probably to high and of the offset.
	 * 
	 * @return A BufferedImage containing the Mandelbrot.
	 */

	public BufferedImage getRenderedMandelbrot() {
		image = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				cREn1 = cIMn1 = 0;
				cRE = (x - xSize / 2 - (offX/100)) / ( _zoom )- (offX/100);
				cIM = (y - ySize / 2 - (offY/100)) / ( _zoom) - (offY/100);
				int iter = _iter;
				while ((cREn1 * cREn1) + (cIMn1 + cIMn1) < detail && iter > 0) {
					cREn1TMP = (cREn1 * cREn1) - (cIMn1 * cIMn1) + cRE;
					cIMn1 = 2.0 * (cREn1 * cIMn1) + cIM;
					cREn1 = cREn1TMP;
					iter -= 1;
				}
				if (color.equalsIgnoreCase("r")
						|| color.equalsIgnoreCase("red")) {
					image.setRGB(x, y, iter << 20 | iter >> 2 | iter
							+ mandColor.getRGB()); // Redisch
				} else if (color.equalsIgnoreCase("g")
						|| color.equalsIgnoreCase("green")) {
					image.setRGB(x, y, iter << 10 | iter >> 2 | iter
							+ mandColor.getRGB()); // Greenisch
				} else if (color.equalsIgnoreCase("b")
						|| color.equalsIgnoreCase("blue")) {
					image.setRGB(x, y,
							iter << 3 | iter >> 2 | iter + mandColor.getRGB()); // Blueisch
				} else if (color.equalsIgnoreCase("lsd")
						|| color.equalsIgnoreCase("colorfull")) {
					image.setRGB(x, y, iter << 10 + iter >> 2 | iter
							+ mandColor.getRGB()); // LSD
				} else {
					image.setRGB(x, y, iter << 10 | iter + mandColor.getRGB());
				}
			}
		}
		return image;
	}

	/**
	 * Calculates a Mandelbrot with the defined Variables. This function opens a
	 * new NeoPreview window. If the Image is completely black then the zoom was
	 * probably to high and of the offset.
	 */
	public void renderMandelbrot() {
		image = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				cREn1 = cIMn1 = 0;
				cRE = (x - xSize / 2) / (0.5 * _zoom * xSize) + offX;
				cIM = (y - ySize / 2) / (0.5 * _zoom * ySize) + offY;
				int iter = _iter;
				while ((cREn1 * cREn1) + (cIMn1 + cIMn1) < detail && iter > 0) {
					cREn1TMP = (cREn1 * cREn1) - (cIMn1 * cIMn1) + cRE;
					cIMn1 = 2.0 * (cREn1 * cIMn1) + cIM;
					cREn1 = cREn1TMP;
					iter -= 1;
				}
				if (color.equalsIgnoreCase("r")
						|| color.equalsIgnoreCase("red")) {
					image.setRGB(x, y, iter << 20 | iter >> 2 | iter
							+ mandColor.getRGB()); // Redisch
				} else if (color.equalsIgnoreCase("g")
						|| color.equalsIgnoreCase("green")) {
					image.setRGB(x, y, iter << 10 | iter >> 2 | iter
							+ mandColor.getRGB()); // Greenisch
				} else if (color.equalsIgnoreCase("b")
						|| color.equalsIgnoreCase("blue")) {
					image.setRGB(x, y,
							iter << 3 | iter >> 2 | iter + mandColor.getRGB()); // Blueisch
				} else if (color.equalsIgnoreCase("lsd")
						|| color.equalsIgnoreCase("colorfull")) {
					image.setRGB(x, y, iter << 10 + iter >> 2 | iter
							+ mandColor.getRGB()); // LSD
				} else {
					image.setRGB(x, y, iter << 10 | iter + mandColor.getRGB());
				}

				// image.setRGB(x, y, iter << 20 | iter >> 2 ); // Redisch
				// image.setRGB(x, y, iter << 10 | iter >> 2 ); // Greenisch
				// image.setRGB(x, y, iter << 3 | iter >> 2 ); // Blueisch
				// image.setRGB(x, y, iter << 10 + iter >> 2 ); //LSD
			}
		}
		new NeoPreview(image, ySize, xSize, "Mandelbrot-Preview");

	}

	/**
	 * Calculates a Julia set with the defined Variables. This function returns
	 * the Julia set as BufferedImage. If the Image is completely black then the
	 * zoom was probably to high and of the offset.
	 * 
	 * @return A BufferedImage containing the Julia set.
	 */
	public BufferedImage getRenderedJulia() {
		image = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_RGB);

		double cX = -0.7;
		double cY = 0.27015;
		double zx, zy;

		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				zx = 1.5 * (x - xSize / 2) / (0.5 * _zoom * xSize) + offX;
				zy = (y - ySize / 2) / (0.5 * _zoom * ySize) + offY;
				int iter = _iter;
				while (zx * zx + zy * zy < detail && iter > 0) {
					double tmp = zx * zx - zy * zy + cX;
					zy = 2.0 * zx * zy + cY;
					zx = tmp;
					iter--;

				}
				if (color.equalsIgnoreCase("r")
						|| color.equalsIgnoreCase("red")) {
					image.setRGB(x, y, iter << 20 | iter >> 2 | iter
							+ mandColor.getRGB()); // Redisch
				} else if (color.equalsIgnoreCase("g")
						|| color.equalsIgnoreCase("green")) {
					image.setRGB(x, y, iter << 10 | iter >> 2 | iter
							+ mandColor.getRGB()); // Greenisch
				} else if (color.equalsIgnoreCase("b")
						|| color.equalsIgnoreCase("blue")) {
					image.setRGB(x, y,
							iter << 3 | iter >> 2 | iter + mandColor.getRGB()); // Blueisch
				} else if (color.equalsIgnoreCase("lsd")
						|| color.equalsIgnoreCase("colorfull")) {
					image.setRGB(x, y, iter << 10 + iter >> 2 | iter
							+ mandColor.getRGB()); // LSD
				} else {
					image.setRGB(x, y, iter << 10 | iter + mandColor.getRGB());
				}
			}
		}
		return image;
	}

	/**
	 * Returns a hash code value for the object. This method is supported for
	 * the benefit of hash tables such as those provided by HashMap.
	 **/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _iter;
		long temp;
		temp = Double.doubleToLongBits(_zoom);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(cRE);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(cIM);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + detail;
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		temp = Double.doubleToLongBits(cREn1TMP);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + xSize;
		result = prime * result + ySize;
		temp = Double.doubleToLongBits(cREn1);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(cIMn1);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Compares this string to the specified object. The result is true if and
	 * only if the argument is not null and is a String object that represents
	 * the same sequence of characters as this object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mandelbrot other = (Mandelbrot) obj;
		if (_iter != other._iter)
			return false;
		if (Double.doubleToLongBits(_zoom) != Double
				.doubleToLongBits(other._zoom))
			return false;
		if (Double.doubleToLongBits(cRE) != Double.doubleToLongBits(other.cRE))
			return false;
		if (Double.doubleToLongBits(cIM) != Double.doubleToLongBits(other.cIM))
			return false;
		if (detail != other.detail)
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (Double.doubleToLongBits(cREn1TMP) != Double
				.doubleToLongBits(other.cREn1TMP))
			return false;
		if (xSize != other.xSize)
			return false;
		if (ySize != other.ySize)
			return false;
		if (Double.doubleToLongBits(cREn1) != Double
				.doubleToLongBits(other.cREn1))
			return false;
		if (Double.doubleToLongBits(cIMn1) != Double
				.doubleToLongBits(other.cIMn1))
			return false;
		return true;
	}

	/**
	 * Saves the Mandelbrot as png. Using the NeoImage class.
	 */
	public void saveMandelbrot() {
		nImage.saveImage(image);
	}

	/**
	 * Gets the average Color of the complete Image -> Using the NeoImage
	 * getAverageColor function.
	 *
	 */
	public void getAverageColor() {
		Color c = nImage.getAverageColor(image, 0, 0, image.getWidth(),
				image.getHeight());
		System.out.println("R:" + c.getRed() + " G:" + c.getGreen() + " B:"
				+ c.getBlue() + " Alpha:" + c.getAlpha());
	}
}