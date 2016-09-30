package at.neonartworks.neolib;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

public class NeoMap extends NeoPath {

	private double latitude;
	private double longitude;
	private String apiKey;
	private String mapUrl;
	private int x, y;
	private int zoom;
	private NeoPath path;

	public NeoMap() {
		path = new NeoPath();
		path.setPath();
	}

	/**
	 * Getters and Setters for Latitude and Longitude!
	 **/

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setAPIKey(String key) {
		this.apiKey = key;
	}

	public String getAPIKey() {
		return this.apiKey;
	}

	public void setLat(double lat) {
		this.latitude = lat;
	}

	public void setLon(double lon) {
		this.latitude = lon;
	}

	public double getLat() {
		return this.latitude;
	}

	public double getLon() {
		return this.longitude;
	}

	public void setZoom(int z) {
		this.zoom = z;
	}

	public void setMap(double lat, double lon, int x, int y) {
		String st_lat = Double.toString(lat);
		String st_lon = Double.toString(lon);
		this.latitude = lat;
		this.longitude = lon;
		this.x = x;
		this.y = y;
		String st_zoom = Integer.toString(this.zoom);
		String st_x = Integer.toString(x);
		String st_y = Integer.toString(y);

		mapUrl = "https://maps.googleapis.com/maps/api/staticmap?maptype=satellite&center=" + st_lat + "," + st_lon
				+ "&zoom=" + st_zoom + "&scale=3" + "&size=" + st_x + "x" + st_y + "&key=" + this.apiKey;
	}

	public String getMapURL() {
		return this.mapUrl;
	}

	public void downloadMap(int x, int y, String URL) {
		InputStream inStream = null;

		try {
			URL mapURL = new URL(URL);
			int byteRead = 0, byteWritten = 0;

			File file = new File(path.getPath());
			BufferedImage image = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);

			URLConnection connection = mapURL.openConnection();
			inStream = connection.getInputStream();

			image = ImageIO.read(inStream);

			ImageIO.write(image, "png", file);

			byteWritten = byteRead;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public void downloadGrayscaleMap(int x, int y, String URL) {
		InputStream inStream = null;

		try {
			URL mapURL = new URL(URL);
			int byteRead = 0, byteWritten = 0;
			// writeStream = new BufferedOutputStream(new
			// FileOutputStream(path+".png"));
			File file = new File(path.getPath());
			BufferedImage image = new BufferedImage(x, y, BufferedImage.TYPE_BYTE_GRAY);

			URLConnection connection = mapURL.openConnection();
			inStream = connection.getInputStream();

			image = ImageIO.read(inStream);

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
			ImageIO.write(image, "png", file);

			byteWritten = byteRead;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
