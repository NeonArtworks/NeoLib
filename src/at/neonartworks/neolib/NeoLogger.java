package at.neonartworks.neolib;

import static at.neonartworks.neolib.LoggerLevel.ALERT;
import static at.neonartworks.neolib.LoggerLevel.ERROR;
import static at.neonartworks.neolib.LoggerLevel.INFO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

/**
 * 
 * @author Florian Wagner
 * 
 * <br>
 *         This class enables to log and save Strings It can log to three
 *         different log types: INFO, ALERT, ERROR
 *
 */

public class NeoLogger extends NeoUI {

	private ArrayList<String> log = new ArrayList<String>();
	private String logPath;
	private JFrame saveFrame;
	private BufferedWriter writer;
	private NeoPath path;

	/**
	 * No intern functionality normal constructor.
	 */
	public NeoLogger() {
		path = new NeoPath();
	}

	public void setLogFile() {
		path.setPath();
	}

	/**
	 * 
	 * Gets the current System Time. The time is formatted and returned as
	 * String.
	 * 
	 */

	public String getLogTime() {
		long yourmilliseconds = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date resultdate = new Date(yourmilliseconds);
		return sdf.format(resultdate);
	}

	/**
	 * @param logg
	 * @param type
	 * 
	 * <br>
	 *            Adds a String with the defined LogLevel to the logfile.
	 */

	public void log(String logg, LoggerLevel type) {
		log.add("[" + type.toString() + "] " + ": " + logg);
	}

	/**
	 * @param logg
	 * @param type
	 * <br>
	 *            Adds a String with the defined LogLevel and the current System
	 *            time to the logfile.
	 */
	public void timeLog(String logg, LoggerLevel type) {
		log.add("[" + getLogTime() + "]" + " [" + type.toString() + "] " + ": "
				+ logg);

	}

	private LoggerLevel getLevelFromString(String s) {

		String[] data = s.trim().split(" ");
		String level = data[1].substring(1, data[1].length() - 1);
		return LoggerLevel.valueOf(level);

	}

	private <T> List<T> addAllToList(T[] array, List<T> list) {
		for (T data : array) {
			list.add(data);
		}
		return list;
	}

	/**
	 * @param levels
	 * <br>
	 *            Writes the logfile to the path defined by 'setLogFile'. only
	 *            the log types witch match with levels are being written.
	 * 
	 */
	public void writeLogFile(LoggerLevel... levels) {
		List<LoggerLevel> levelList = new ArrayList<LoggerLevel>();
		if (levels.length == 0) {
			levels = LoggerLevel.values();
		}
		levelList = addAllToList(levels, levelList);
		try {
			writer = new BufferedWriter(new FileWriter(path.getPath()));
			writer.write("Sarting Log at: " + getLogTime()
					+ System.lineSeparator());

			for (String sLine : log.toString().split(",")) {
				// System.out.println(sLine);
				LoggerLevel logLevel;
				logLevel = getLevelFromString(sLine);

				if (levelList.contains(logLevel)) {
					writer.write(sLine + System.lineSeparator());
				}
			}
			writer.write("Log Ended");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}