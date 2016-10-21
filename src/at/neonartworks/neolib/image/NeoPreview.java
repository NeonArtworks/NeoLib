package at.neonartworks.neolib.image;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * @author Florian Wagner <br>
 *         This simple calss creates an preview window for image files.
 */


public class NeoPreview extends JFrame {


	private BufferedImage Buff;

	/**
	 * It displays the image -> buff with the size defined by sizeX and sizeY.
	 * The Window itself is resizeable.
	 * 
	 * @param buff
	 * @param sizeX
	 * @param sizeY
	 */

	public NeoPreview(BufferedImage buff, int sizeX, int sizeY) {
		setTitle("Preview Window");
		setBounds(0, 0, sizeY, sizeX);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		Buff = buff;
	}
	/**
	 * It displays the image -> buff with the size defined by sizeX and sizeY.
	 * The Window itself is resizeable.
	 * @param buff
	 * @param sizeX
	 * @param sizeY
	 * @param titel
	 */
	public NeoPreview(BufferedImage buff, int sizeX, int sizeY, String titel) {
		setTitle(titel);
		setBounds(0, 0, sizeY, sizeX);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		Buff = buff;
	}
	
	/**
	 * Draws the image.
	 */
	
	public void paint(Graphics g) {
		g.drawImage(Buff, 0, 0, getWidth(), getHeight(), this);
	}

}
