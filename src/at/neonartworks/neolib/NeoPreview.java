package at.neonartworks.neolib;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class NeoPreview extends JFrame{
	
	private BufferedImage Buff;
	
	
	public NeoPreview(BufferedImage buff, int sizeX, int sizeY){
		setTitle("2D Voronoi Diagram");
		setBounds(0, 0, sizeY, sizeX);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		Buff = buff;
	}
	
	public void paint(Graphics g) {
		g.drawImage(Buff, 0, 0, getWidth(), getHeight(), this);
	}
	
}
