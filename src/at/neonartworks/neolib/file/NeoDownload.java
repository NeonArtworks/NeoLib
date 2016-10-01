package at.neonartworks.neolib.file;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import at.neonartworks.neolib.NeoPath;

public class NeoDownload extends NeoPath{

	private URL url;
	private int byteWritten;
	private NeoPath path;
	
	
	public NeoDownload(){
		path = new NeoPath();
		path.setPath();
	}
	/**
	 * 
	 * @param URL
	 * @throws MalformedURLException
	 * 
	 * <br> Sets the URL based on an Input String.
	 */
	
	public void setURL(String URL) throws MalformedURLException {
		url = new URL(URL);
	}

	public URL getURL() {
		return this.url;
	}
	
	/**
	 * @return ByteWritten
	 * <br> Returns the total amount of bytes that where written for the last file.
	 */
	
	public int getTotalBytes(){
		return this.byteWritten;
	}
	
	/**
	 * 
	 * @throws FileNotFoundException
	 * <br> This method downloads the Input stream from the URL defined by 'setURL'.
	 */
	public void bufferedDownloadFromURL() throws FileNotFoundException {
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