package at.neonartworks.neolib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Florian Wagner <br>
 *         This is the FileHandler Class Containing most of the File Handling
 *         functions for this lib.
 * 
 */

public class NeoFileHandler extends NeoUI {
	private NeoPath path;
	private BufferedWriter writer;
	private BufferedReader reader;
	private String filPath;

	/**
	 * Constructor -> extends NeoUI and uses NeoPath internally to set and get
	 * file-paths.
	 */

	public NeoFileHandler() {
		path = new NeoPath();

	}

	/**
	 * Sets the current file path, used by every function in this class.
	 * 
	 */
	public void setFile() {
		path.pathDialoge();
		filPath = path.getPath();
	}

	private String getPath() {
		return this.filPath;
	}

	/**
	 * 
	 * Appends a text defined by the parameter "text", to the defined path, set
	 * with: "setFilePath()"
	 * 
	 * @param text
	 * 
	 */

	public void appendFile(String text) {
		setFile();
		try {
			writer = new BufferedWriter(new FileWriter(getPath(), true));
			writer.write(text + System.lineSeparator());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Writes a file to an Path, the path doesn't have to be set before!
	 * 
	 * @param text
	 *
	 * 
	 */

	public void writeFile(String text) {
		setFile();
		try {
			writer = new BufferedWriter(new FileWriter(getPath()));
			String line;
			writer.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Reads the file given by 'setFile' and prints out every Line in the
	 * console.
	 * 
	 */

	public void readFile() {
		try {
			reader = new BufferedReader(new FileReader(getPath()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function reads a file and appends every line to one String using the
	 * StringBuilder class.
	 * 
	 * @return everyLine
	 */
	public String readFileByLine() {
		String line = null;
		String[] text;
		List<String> stringList = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();

		try {
			reader = new BufferedReader(new FileReader(getPath()));
			while ((line = reader.readLine()) != null) {
				sb.append(line + System.lineSeparator());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	/**
	 * Returns the total line count of the file. The used file is the one set by
	 * 'setFile'
	 * 
	 * @return count
	 */

	public int getLineCount() {
		String line;
		int count = 0;

		try {
			reader = new BufferedReader(new FileReader(getPath()));

			while ((line = reader.readLine()) != null) {
				count += 1;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * An extra method especially for NeoCrypt. The En / Decrypter can't
	 * recognize "ÖÄÜ" and throws an internal error in order to get rid of this
	 * problem, we set the "ÖÄÜ" to "ue, oe, ae". This is only to fix this
	 * problem, but can be used for other purposes.
	 * 
	 * @param d
	 * @return
	 */

	public String replaceÄÖÜ(String d) {

		d.replaceAll("ö", "oe");
		d.replaceAll("ä", "ae");
		d.replaceAll("ü", "ue");
		d.replaceAll("Ö", "Oe");
		d.replaceAll("A", "Ae");
		d.replaceAll("Ü", "Ue");

		return d;
	}

}