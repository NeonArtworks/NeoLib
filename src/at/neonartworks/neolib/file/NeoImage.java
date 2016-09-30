package at.neonartworks.neolib.file;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import at.neonartworks.neolib.NeoPath;
import at.neonartworks.neolib.NeoPreview;

public class NeoImage {

	private NeoPath path;
	private BufferedWriter writer;
	private BufferedReader reader;
	private BufferedImage image;
	private BufferedImage image2;
	private NeoPreview prev;

	public NeoImage() {
		path = new NeoPath();

	}

	/**
	 * 
	 * Converts a picture to a Grayscale Image
	 * 
	 */

	public void convertGrayscale() {
		try {
			path.setPath();

			File file = new File(path.getPath());
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
			path.setPath();
			File writeFile = new File(path.getPath());
			file.createNewFile();
			ImageIO.write(image2, "png", writeFile);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param img
	 * @return Grayscale version of img
	 * 
	 *         Returns a Grayscale version of the BufferedImage img
	 */
	public BufferedImage convertGrayscale(BufferedImage img) {
		int x = img.getHeight();
		int y = img.getWidth();

		img = new BufferedImage(x, y, BufferedImage.TYPE_BYTE_GRAY);

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				int rgb = img.getRGB(i, j);
				int r = (rgb >> 16) & 0xFF;
				int g = (rgb >> 8) & 0xFF;
				int b = (rgb & 0xFF);

				int grayLevel = (r + g + b) / 3;
				int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
				img.setRGB(i, j, gray);
			}
		}
		return img;
	}

	/**
	 * Shows an Image based on the given file in a new Window
	 * 
	 */

	public void showImageFile() {
		path.setPath();

		File file = new File(path.getPath());
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
	 * Shows the given BufferedImage in a new Window
	 * 
	 */

	public void showImage(BufferedImage image) {
		int x = image.getHeight();
		int y = image.getWidth();
		prev = new NeoPreview(image, x, y);
	}

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

				int grayLevel = (r + g + b)/3;
				int gray = (grayLevel <<random.nextInt(100)) - (grayLevel << random.nextInt(100));
				img.setRGB(i, j, gray);
			}
		}
		return img;

	}
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
	public BufferedImage gRandomCHNoise(BufferedImage img, int size) {
		int x = img.getHeight();
		int y = img.getWidth();
		Random random = new Random();
		img = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (((i & size) == 0) && ((j % size) == 0)){
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
	public void saveImage(BufferedImage image){
		path.setPath();
		
		File file = new File(path.getPath());
		
		try {
			file.createNewFile();
			ImageIO.write(image, "png", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}