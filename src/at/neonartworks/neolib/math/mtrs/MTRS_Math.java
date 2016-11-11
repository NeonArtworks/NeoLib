package at.neonartworks.neolib.math.mtrs;

import java.util.ArrayList;
import java.util.List;

/**
 * MTRS is a subject in the HTL which is all about mesuring signals. This class
 * offers many convienient methods to calculate some statistical values from
 * discrete measuring result. In every Method comment there is also the german
 * translation of the term, because the HTL is in Austria, where all the terms
 * are explained in german
 * 
 * @author Alexander Daum
 *
 */
public class MTRS_Math {
	private final List<Double> values;

	/**
	 * Constructs a new MTRS_Math object which has all values in the List
	 * 
	 * @param values
	 */
	public MTRS_Math(List<Double> values) {
		this.values = new ArrayList<Double>(values);
	}

	/**
	 * Adds a Value to an existing List of values
	 * 
	 * @param value
	 */
	public void add(double value) {
		this.values.add(value);
	}

	/**
	 * 
	 * @return The average of the Values (germ. Mittelwert)
	 */
	public double Average() {
		double sum = 0;
		for (int i = 0; i < values.size(); i++) {
			sum += values.get(i);
		}
		return sum / values.size();
	}

	/**
	 * 
	 * @return The Abolute Average Value (germ. Gleichrichtwert)
	 */
	public double AAV() {
		double sum = 0;
		for (int i = 0; i < values.size(); i++) {
			sum += Math.abs(values.get(i));
		}
		return sum / values.size();
	}

	/**
	 * 
	 * @return The Root-Mean-Square (germ. Effektivwert)
	 */
	public double RMS() {
		double sum = 0;
		for (int i = 0; i < values.size(); i++) {
			sum += (values.get(i) * values.get(i));
		}
		sum /= values.size();
		return Math.sqrt(sum);
	}

	/**
	 * 
	 * @return The Ripple (germ. Welligkeit)
	 */
	public double ripple() {
		double rms = RMS();
		double GLW = AAV();
		return (Math.sqrt(rms * rms - GLW * GLW) / GLW);
	}

	/**
	 * Calculates the power of an Alternating Electric Signal
	 * 
	 * @param The
	 *            missing Component ether the voltage or the current
	 * @return
	 */
	public double power(List<Double> UI) {
		double sum = 0;
		for (int i = 0; i < values.size(); i++) {
			sum += values.get(i) * UI.get(i);
		}
		return sum / values.size();
	}

	/**
	 * 
	 * @return The standard deviation (germ. Standardabweichung)
	 */
	public double standardDeviation() {
		double average = Average();
		double diffSum = 0;
		for (double value : values) {
			diffSum += (value - average) * (value - average);
		}
		diffSum /= (values.size() - 1);
		return Math.sqrt(diffSum);
	}

	/**
	 * 
	 * @return The Confidence Interval (germ. Vertrauensbereich)
	 * 
	 * @param tValue
	 *            The t value, to be found in a Table
	 */
	public double convInterval(double tValue) {
		double stdDev = standardDeviation();
		double result;
		result = tValue / Math.sqrt(values.size());
		return result * stdDev;
	}

}
