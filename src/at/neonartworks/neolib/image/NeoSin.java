package at.neonartworks.neolib.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class NeoSin extends JFrame {

	double sin(double x) {
		return Math.sin(x);
	}

	double cos(double y) {
		return Math.cos(y);
	}

	private int off;
	private float freq;
	private int ampl;
	private int point;
	private Color c;
	private int sY;
	private int sX;
	private String mo;
	private int fac1;
	private int fac2;

	public int getOff() {
		return off;
	}

	public void setOff(int off) {
		this.off = off;
	}

	public float getFreq() {
		return freq;
	}

	public void setFreq(float freq) {
		this.freq = freq;
	}

	public int getAmpl() {
		return ampl;
	}

	public void setAmpl(int ampl) {
		this.ampl = ampl;
	}

	public int getsY() {
		return sY;
	}

	public void setsY(int sY) {
		this.sY = sY;
	}

	public int getsX() {
		return sX;
	}

	public void setsX(int sX) {
		this.sX = sX;
	}

	public int getFac1() {
		return fac1;
	}

	public void setFac1(int fac1) {
		this.fac1 = fac1;
	}

	public int getFac2() {
		return fac2;
	}

	public void setFac2(int fac2) {
		this.fac2 = fac2;
	}

	public NeoSin(int offX, float f, int amp, int x, int y, String mode,
			int fact1, int fact2) {
		this.off = offX;
		this.freq = f;
		this.ampl = amp;
		this.point = getWidth();
		sY = y;
		sX = x;
		mo = mode;
		fac1 = fact1;
		fac2 = fact2;
		setVisible(true);
		if (mo.equalsIgnoreCase("sin")) {
			setTitle("SinWave Graphic");
		} else if (mo.equalsIgnoreCase("cos")) {
			setTitle("CosWave Graphic");
		} else if (mo.equalsIgnoreCase("lissajous")) {
			setTitle("Lissajous Graphic");
		}

		setBounds(0, 0, x, y);
		setBackground(c = new Color(0.9f, 0.9f, 0.9f));
	}

	public NeoSin(int offX, float f, int amp, int x, int y, String mode) {
		this.off = offX;
		this.freq = f;
		this.ampl = amp;
		this.point = getWidth();
		sY = y;
		sX = x;
		mo = mode;
		setVisible(true);
		if (mo.equalsIgnoreCase("sin")) {
			setTitle("SinWave Graphic");
		} else if (mo.equalsIgnoreCase("cos")) {
			setTitle("CosWave Graphic");
		} else if (mo.equalsIgnoreCase("lissajous")) {
			setTitle("Lissajous Graphic");
		}

		setBounds(0, 0, x, y);
		setBackground(c = new Color(0.9f, 0.9f, 0.9f));
	}

	public void paint(Graphics g) {
		Polygon p2 = new Polygon();
		point = getWidth();
		if (mo.equalsIgnoreCase("sin")) {
			for (int x = -point; x <= point; x++) {
				int offset = 200 + off;
				p2.addPoint(x + offset + (getWidth() / 2), (getHeight() / 2)
						- (int) (ampl * sin((x / freq) * Math.PI)));
			}
		} else if (mo.equalsIgnoreCase("cos")) {
			for (int x = -point; x <= point; x++) {
				int offset = 200 + off;
				p2.addPoint(x + offset + (getWidth() / 2), (getHeight() / 2)
						- (int) (ampl * cos((x / freq) * Math.PI)));
			}
		} else if (mo.equalsIgnoreCase("lissajous")) {
			freq = 1 / freq;

			for (int x = -point; x <= point; x++) {
				int offset = 200 + off;
				p2.addPoint((int) (ampl * sin(((x * freq) * fac1) * Math.PI))
						+ (getWidth() / 2), (getHeight() / 2)
						- (int) (ampl * sin(((x * freq) * fac2) * Math.PI)));
			}
		}
		g.drawLine((getWidth() / 2), 0, (getWidth() / 2), getHeight());
		g.drawLine(0, (getHeight() / 2), getWidth(), (getHeight() / 2));
		g.drawString(String.valueOf(getWidth()), getWidth() - 100,
				getHeight() - 10);
		g.drawString(" | " + String.valueOf(getHeight()), getWidth() - 70,
				getHeight() - 10);

		g.setColor(Color.blue);
		g.drawPolyline(p2.xpoints, p2.ypoints, p2.npoints);
	}
}