package at.neonartworks.neolib;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NeoDownload extends NeoPath {

	/**
	 * @author Florian Wagner <br>
	 *         This class can be used to Download Images, Videos etc. from an
	 *         URL. It extends NeoPath, so all paht-Handling is done using
	 *         NeoPath.
	 */

	private URL url;
	private int byteWritten;
	private NeoPath path;

	public NeoDownload() {
		path = new NeoPath();
		path.pathDialoge();
	}

	/**
	 * Sets the URL based on an Input String.
	 * 
	 * @param URL
	 * @throws MalformedURLException
	 * 
	 */

	public void setURL(String URL) throws MalformedURLException {
		url = new URL(URL);
	}

	/**
	 * Gets the current URL.
	 * 
	 * @return
	 */

	public URL getURL() {
		return this.url;
	}

	/**
	 * Returns the total amount of bytes that where written for the last file.
	 * 
	 * @return ByteWritten <br>
	 * 
	 */

	public int getTotalBytes() {
		return this.byteWritten;
	}
	/**
	 * This method downloads the Input stream from the URL given by direct.
	 * <br>
	 * 
	 * @throws FileNotFoundException
	 */
	public void DirectDownload(String direct) throws FileNotFoundException {
		InputStream iStream = null;
		BufferedOutputStream writeStream = new BufferedOutputStream(new FileOutputStream(path.getPath()));
		try {
			byte[] buf;
			int byteRead = 0;
			URL durl = new URL(direct);
			iStream = durl.openStream();
			buf = new byte[1024];
			while ((byteRead = iStream.read(buf)) != -1) {
				writeStream.write(buf, 0, byteRead);
				byteWritten += byteRead;

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				iStream.close();
				writeStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * This method downloads the Input stream from the URL defined by 'setURL'.
	 * <br>
	 * 
	 * @throws FileNotFoundException
	 */
	public void DownloadFromURL() throws FileNotFoundException {
		InputStream iStream = null;
		BufferedOutputStream writeStream = new BufferedOutputStream(new FileOutputStream(path.getPath()));
		try {
			byte[] buf;
			int byteRead = 0;

			URLConnection connection = url.openConnection();
			iStream = connection.getInputStream();
			buf = new byte[1024];
			while ((byteRead = iStream.read(buf)) != -1) {
				writeStream.write(buf, 0, byteRead);
				byteWritten += byteRead;

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				iStream.close();
				writeStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}