package at.neonartworks.neolib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NeoFileHandler extends NeoUI {

	/**
	 * 
	 * @author Florian Wagner <br>
	 *         This is the FileHandler Library Containing every FileHandling
	 *         function for this lib.
	 * 
	 */

	private NeoPath path;
	private BufferedWriter writer;
	private BufferedReader reader;
	private String filPath;

	public NeoFileHandler() {
		path = new NeoPath();

	}

	/**
	 * Sets the current file path.
	 */

	public void setFile() {
		path.setPath();
		filPath = path.getPath();
	}

	private String getPath() {
		return this.filPath;
	}

	/**
	 * 
	 * @param text
	 *
	 *            Writes a file to an Path, the path doesn't have to be set
	 *            before!
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
	 * Prints the every Line of the File into the Console
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
	 * Returns a String containing every line of the File
	 * 
	 */
	public String readFileByLine() {
		String line = null;
		String[] text;
		List<String> stringList = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		
		try {
			reader = new BufferedReader(new FileReader(getPath()));
			while ((line = reader.readLine()) != null) {
				stringList.add(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		for (String d : stringList.toString().split(",")) {
			sb.append(d.substring(1) + System.lineSeparator());
		}
		return sb.toString();
	}

	/**
	 * @return count <br>
	 *         Returns the total line-count of the file given.
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

}