package at.neonartworks.neolib.math.mtrs;

import java.util.List;

public class MTRS_Math {
	private final 
	List<Double> values;

	public MTRS_Math(List<Double> values) {
		this.values = values;
	}

	public double Average() {
		double sum = 0;
		for (int i = 0; i < values.size(); i++) {
			sum += values.get(i);
		}
		return sum / values.size();
	}

	public double AAV() {
		double sum = 0;
		for (int i = 0; i < values.size(); i++) {
			sum += Math.abs(values.get(i));
		}
		return sum / values.size();
	}

	public double RMS() {
		double sum = 0;
		for (int i = 0; i < values.size(); i++) {
			sum += (values.get(i) * values.get(i));
		}
		sum /= values.size();
		return Math.sqrt(sum);
	}

	public double Ripple() {
		double rms = RMS();
		double GLW = AAV();
		return (Math.sqrt(rms * rms - GLW * GLW) / GLW);
	}
	/**
	 * Calculates the power of an Alternating Electric Signal
	 * @param The missing Component ether the voltage or the current
	 * @return
	 */
	public double Power(List<Double> UI){
		double sum = 0;
		for (int i = 0; i < values.size(); i++) {
			sum += values.get(i)*UI.get(i);
		}
		return sum / values.size();
	}
}
