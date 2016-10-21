package at.neonartworks.neolib.image;

import static at.neonartworks.neolib.image.VoronoiMethod.VORONOI;
import static at.neonartworks.neolib.image.VoronoiMethod.VORONOIF;
import static at.neonartworks.neolib.image.VoronoiMethod.VORONOIM;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import at.neonartworks.neolib.NeoPath;
import at.neonartworks.neolib.NeoUI;

/**
 * 
 * @author Florian Wagner <br>
 *         This class enables the generation of a 2D Voronoi Diagram
 * 
 */
public class NeoVoro extends NeoUI {

	private int sizeX;
	private int sizeY;
	private int cells;
	private int colorvec = 0;
	private VoronoiMethod method;
	private BufferedImage Buff;
	private int[] px, py, color;
	private Random rand = new Random();
	private NeoPath path;
	private boolean save;

	/**
	 * The constructor sets the x/y size and the cell amount aswell as the
	 * method. px and py are the random generated points on the plan defined by
	 * sizeX and sizeY The distances between these two int[] px and py are
	 * calculated internally.
	 * 
	 * @param x
	 * @param y
	 * @param cella
	 * @param meth
	 */

	public NeoVoro(int x, int y, int cella, VoronoiMethod meth) {
		path = new NeoPath();

		sizeX = x;
		sizeY = y;
		cells = cella;
		method = meth;

		Buff = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);

		px = new int[cells];
		py = new int[cells];
		color = new int[cells];

		if (colorvec == 0) {
			colorvec = 16226277;
		}

		for (int i = 0; i < cells; i++) {
			px[i] = rand.nextInt(sizeY);
			py[i] = rand.nextInt(sizeX);
			color[i] = rand.nextInt(colorvec);
		}

	}

	/**
	 * Simple method to set the Voronoi type. This method uses the
	 * 'VoronoiMethod meth' The three types are: 'VORONOI, VORONOIF, VORONOIM'.
	 * 
	 * @param meth
	 * 
	 */
	public void setMethod(VoronoiMethod meth) {
		this.method = meth;
	}

	/**
	 * Returns the current Method -> this will be a 'VoronoiMethod'
	 * 
	 * @return
	 */

	public VoronoiMethod getMethod() {
		return this.method;
	}

	/**
	 * Getter Method for the x - size of the Voronoi.
	 * 
	 * @return
	 */

	public int getSizeX() {
		return sizeX;
	}

	/**
	 * Sets the x- size of the Voronoi.
	 * 
	 * @param x
	 */

	public void setSizeX(int x) {
		sizeX = x;
	}

	/**
	 * Getter Method for the y - size of the Voronoi.
	 * 
	 * @return
	 */
	public int getSizeY() {
		return sizeY;
	}

	/**
	 * Sets the y - size of the Voronoi.
	 * 
	 * @param y
	 * 
	 */
	public void setSizeY(int y) {
		sizeY = y;
	}

	/**
	 * Returns the total cell amount of the Voronoi.
	 * 
	 * @return
	 */

	public int getCells() {
		return cells;
	}

	/**
	 * Sets the total cell amount in the Voronoi diagram
	 * 
	 * @param cells
	 * 
	 */
	public void setCells(int cells) {
		this.cells = cells;
	}

	/**
	 * A rather useless Method used to get the Colorvec as an int.
	 * 
	 * @return
	 */

	public int getColorevec() {
		return colorvec;
	}

	/**
	 * Optionally; Sets a integer and generates color vectors from it.
	 * 
	 * @param colorevec
	 * 
	 */
	public void setColorevec(int colorevec) {
		this.colorvec = colorevec;
	}

	private boolean getIsSave() {
		return this.save;
	}

	private double generateDistance(int x1, int x2, int y1, int y2) {
		double dist = 0;
		if (method.equals(VORONOI)) {
			dist = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		} else if (method.equals(VORONOIM)) {
			dist = Math.abs(x1 - x2) + Math.abs(y1 - y2);
		} else if (method.equals(VORONOIF)) {
			dist = Math.sqrt(Math.abs(x1 - x2)
					+ Math.abs(y1 - y2)
					+ Math.abs((x1 - x2)
							+ (y1 - y2)
							+ x2
							+ y2
							+ Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2)
									* (y1 - y2))))
					+ Math.abs(x1 - x2)
					+ Math.abs(y1 - y2)
					+ Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		} else {
			dist = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		}
		return dist;

	}

	/**
	 * Generates the VoronoiDiagram into a BufferedImage. Based on the input of
	 * x and y aswell as the total point px and py. The Distance is is
	 * calculated internally. It also creates the Diagram preview.
	 */

	public void generateVoronoi() {
		Thread thread = new Thread() {
			public void run() {
				int n;
				for (int x = 0; x < sizeY; x++) {
					@SuppressWarnings("unused")
					long time = System.nanoTime();

					for (int y = 0; y < sizeX; y++) {
						n = 0;
						for (int i = 0; i < cells; i++) {
							if (generateDistance(px[i], x, py[i], y) < generateDistance(
									px[n], x, py[n], y)) {
								n = i;
							}
						}
						Buff.setRGB(x, y, color[n]);
					}

				}
				new NeoPreview(Buff, getSizeX(), getSizeY());

				if (getIsSave() == true) {
					saveAsVoronoi();
				} else if (getIsSave() == false) {
					return;
				} else {
					return;
				}
			}
		};
		thread.start();
	}

	/**
	 * Generates a voronoi and returns it as BufferedImage.
	 * 
	 * @return BufferedImage (the Voronoi)
	 */

	public BufferedImage getVoronoi() {

		int n;
		for (int x = 0; x < sizeY; x++) {
			@SuppressWarnings("unused")
			long time = System.nanoTime();

			for (int y = 0; y < sizeX; y++) {
				n = 0;
				for (int i = 0; i < cells; i++) {
					if (generateDistance(px[i], x, py[i], y) < generateDistance(
							px[n], x, py[n], y)) {
						n = i;
					}
				}
				Buff.setRGB(x, y, color[n]);
			}

		}

		return Buff;
	}

	/**
	 * 
	 * isSaves defines if the Voronoi Image should be safed or not. If "true"
	 * the Image will be safed if "false" it won't.
	 * 
	 * @param isSave
	 * 
	 */
	public void saveVoronoi(boolean isSave) {
		save = isSave;
	}

	private void saveAsVoronoi() {
		path.setPath();
		File output = new File(path.getPath());
		try {
			output.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			ImageIO.write(Buff, "png", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}