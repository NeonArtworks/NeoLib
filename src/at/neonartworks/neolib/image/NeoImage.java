package at.neonartworks.neolib.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import at.neonartworks.neolib.NeoPath;

/**
 * NeoImage is an Class to convert, show, load Images.
 * 
 * @author Florian Wagner
 * 
 * 
 */

public class NeoImage extends NeoPath {
	private BufferedWriter writer;
	private BufferedReader reader;
	private NeoPreview prev;

	/**
	 * 
	 * Converts the Image given by setPath to an Grayscale images and saves it.
	 * 
	 */
	public void convertGrayscale() {
		BufferedImage image;
		BufferedImage image2;
		try {
			pathDialoge();

			File file = new File(getPath());
			image = ImageIO.read(file);
			int x = image.getHeight();
			int y = image.getWidth();

			image2 = new BufferedImage(x, y, BufferedImage.TYPE_BYTE_GRAY);
			image2 = ImageIO.read(file);

			for (int i = 0; i < x; i++) {
				for (int j = 0; j < y; j++) {
					int rgb = image2.getRGB(i, j);
					int r = (rgb >> 16) & 0xFF;
					int g = (rgb >> 8) & 0xFF;
					int b = (rgb & 0xFF);

					int grayLevel = (r + g + b) / 3;
					int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
					image2.setRGB(i, j, gray);
				}
			}
			pathDialoge();
			File writeFile = new File(getPath());
			file.createNewFile();
			ImageIO.write(image2, "png", writeFile);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Returns the given image and returns it as BufferedImage
	 * 
	 * @return
	 */
	public BufferedImage convertGrayscaleReturn() {
		BufferedImage image;
		BufferedImage image2 = null;
		pathDialoge();
		int x = 0;
		int y = 0;
		File file = new File(getPath());

		try {
			image = ImageIO.read(file);

			x = image.getHeight();
			y = image.getWidth();

			image2 = new BufferedImage(x, y, BufferedImage.TYPE_BYTE_GRAY);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			image2 = ImageIO.read(file);

			for (int i = 0; i < x; i++) {
				for (int j = 0; j < y; j++) {
					int rgb = image2.getRGB(i, j);
					int r = (rgb >> 16) & 0xFF;
					int g = (rgb >> 8) & 0xFF;
					int b = (rgb & 0xFF);

					int grayLevel = (r + g + b) / 3;
					int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
					image2.setRGB(i, j, gray);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image2;
	}

	/**
	 * Converts the given BufferedImage into a grayscale and returns it.
	 * 
	 * @param image
	 * @return
	 */
	public BufferedImage convertGrayscale(BufferedImage image) {

		int x = image.getWidth();
		int y = image.getHeight();

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				int rgb = image.getRGB(i, j);
				int r = (rgb >> 16) & 0xFF;
				int g = (rgb >> 8) & 0xFF;
				int b = (rgb & 0xFF);

				int grayLevel = (r + g + b) / 3;
				int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
				image.setRGB(i, j, gray);
			}
		}

		return image;
	}

	/**
	 * 
	 * Shows the Image set with 'setPath' in a new Window.
	 * 
	 */

	public void showImage() {
		BufferedImage image;
		BufferedImage image2;
		pathDialoge();

		File file = new File(getPath());
		try {
			image = ImageIO.read(file);

			int x = image.getHeight();
			int y = image.getWidth();

			image2 = new BufferedImage(x, y, BufferedImage.TYPE_BYTE_GRAY);
			image2 = ImageIO.read(file);

			prev = new NeoPreview(image2, x, y);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Loads an Image into an BufferedImage.
	 * 
	 * @return the BufferedImage
	 */
	public BufferedImage loadImage() {
		BufferedImage image;
		BufferedImage image2 = null;
		pathDialoge();

		File file = new File(getPath());
		try {
			image = ImageIO.read(file);

			int x = image.getHeight();
			int y = image.getWidth();

			image2 = new BufferedImage(x, y, BufferedImage.TYPE_BYTE_GRAY);
			image2 = ImageIO.read(file);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image2;
	}

	/**
	 * Generates a random Grayscale noise! Note this is not the typical Noise
	 * like a perlin for e.g.! This functions generates different Gray values
	 * for each pixel and returns it into a BufferedImage. The x and y size of
	 * the returned BufferedImage is set by the Input Image.
	 * 
	 * @param img
	 * @return
	 */

	public BufferedImage gRandomNoise(BufferedImage img) {
		int x = img.getHeight();
		int y = img.getWidth();
		Random random = new Random();
		img = new BufferedImage(x, y, BufferedImage.TYPE_BYTE_GRAY);

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				int rgb = img.getRGB(i, j);
				int r = (rgb >>> random.nextInt(100));
				int g = (rgb >>> random.nextInt(100));
				int b = (rgb);

				int grayLevel = (r + g + b) / 3;
				int gray = (grayLevel << random.nextInt(100))
						- (grayLevel << random.nextInt(100));
				img.setRGB(i, j, gray);
			}
		}
		return img;

	}

	/**
	 * Generates a random Colored noise! Note this is not the typical Noise like
	 * a perlin for e.g.! This functions generates different color values for
	 * each pixel and returns it into a BufferedImage. The x and y size of the
	 * returned BufferedImage is set by the Input Image.
	 * 
	 * @param img
	 * @return
	 */
	public BufferedImage gRandomCNoise(BufferedImage img) {
		int x = img.getHeight();
		int y = img.getWidth();
		Random random = new Random();
		img = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				int rgb = img.getRGB(i, j);
				int r = (rgb >>> random.nextInt(100));
				int g = (rgb >> random.nextInt(100));
				int b = (rgb >>> random.nextInt(100));

				int grayLevel = (r + g + b);
				int gray = (grayLevel << random.nextInt(100));
				img.setRGB(i, j, gray);
			}
		}
		return img;

	}

	/**
	 * Shows an Image given by the BufferedImage image in a new Window.
	 * 
	 * @param image
	 */

	public void showImage(BufferedImage image) {
		int x = image.getHeight();
		int y = image.getWidth();
		prev = new NeoPreview(image, x, y);
	}

	/**
	 * Generates the same noise as 'gRandomCNoise' with the only difference
	 * being, that you can define a Gap in between the nosie.
	 * 
	 * @param img
	 * @param size
	 * @return
	 */

	public BufferedImage gRandomCChungNoise(BufferedImage img, int size) {
		int x = img.getHeight();
		int y = img.getWidth();
		Random random = new Random();
		img = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (((i & size) == 0) && ((j % size) == 0)) {
					int rgb = img.getRGB(i, j);
					int r = (rgb >>> random.nextInt(100));
					int g = (rgb >> random.nextInt(100));
					int b = (rgb >>> random.nextInt(100));

					int grayLevel = (r + g + b);
					int gray = (grayLevel << random.nextInt(100));
					img.setRGB(i, j, gray);
				}

			}
		}
		return img;

	}

	/**
	 * Saves the Image given by the BufferedImage 'image' into a new file- The
	 * path of the file is defined with 'setPath()'!
	 * 
	 */

	public void saveImage(BufferedImage image) {
		pathDialoge();
		if (getPath().contains(".png")) {
			File file = new File(getPath());
			try {
				file.createNewFile();
				ImageIO.write(image, "png", file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			File file = new File(getPath() + ".png");
			try {
				file.createNewFile();
				ImageIO.write(image, "png", file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Gets the average color of the given rectangle from the BufferedImage.
	 * 
	 * @param image
	 * @param x0
	 * @param y0
	 * @param width
	 * @param height
	 * @return
	 */
	public Color getAverageColor(BufferedImage image, int x0, int y0,
			int width, int height) {
		int num = width * height;
		int sumr = 0, sumg = 0, sumb = 0;
		int x1 = x0 + width;
		int y1 = y0 + height;
		for (int x = x0; x < x1; x++) {
			for (int y = y0; y < y1; y++) {
				Color pixel = new Color(image.getRGB(x, y));
				sumr += pixel.getRed();
				sumg += pixel.getGreen();
				sumb += pixel.getBlue();
			}
		}

		return new Color(sumr / num, sumg / num, sumb / num);
	}
}