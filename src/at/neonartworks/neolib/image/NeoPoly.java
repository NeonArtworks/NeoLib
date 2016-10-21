package at.neonartworks.neolib.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import at.neonartworks.neolib.math.NeoPoint;

/**
 * 
 * Originally onyl a test class.
 * 
 * @author Florian Wagner
 *
 */
public class NeoPoly extends JFrame {

	private NeoPoint point;
	private NeoPoint p2;
	private NeoPoint p3;
	private int count = 0;
	private JPanel contentPane;
	private NeoImage image;
	private Polygon poly;
	private BufferedImage img;
	private List<Polygon> polylist = new ArrayList<Polygon>();
	private List<Color> colorlist = new ArrayList<Color>();
	private int polycounter = 0;

	private void loadImage() {
		img = image.loadImage();
	}

	public NeoPoly() {
		point = new NeoPoint();
		p2 = new NeoPoint();
		p3 = new NeoPoint();
		image = new NeoImage();
		loadImage();

		setVisible(true);
		setBounds(0, 0, img.getWidth(), img.getHeight());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent mouse) {

				if (count == 0) {
					point.setX(mouse.getX());
					point.setY(mouse.getY());
					count = 1;
					System.out.println("p1" + point.toString());
					repaint();
				} else if (count == 1) {
					p2.setX(mouse.getX());
					p2.setY(mouse.getY());
					count = 2;
					System.out.println("p2" + p2.toString());
					repaint();
				} else if (count == 2) {
					p3.setX(mouse.getX());
					p3.setY(mouse.getY());
					count = 0;
					System.out.println("p3" + p3.toString());
					repaint();

				} else if (count > 2) {
					count = 0;
				} else {
					count = 0;
				}

				System.out.println(count);

			}

			@Override
			public void mouseEntered(MouseEvent mouse) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent mouse) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent mouse) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent mouse) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void paint(Graphics g) {
		NeoPoint tmp0 = null;
		NeoPoint tmp1 = null;
		NeoPoint tmp2 = null;
		g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), this);
		if (point.x != 0 && point.y != 0 && p2.x != 0 && p2.y != 0 && p3.x != 0
				&& p3.y != 0) {
			// g.drawOval((int) point.x + 4, (int) point.y + 25, 10, 10);
			// g.drawOval((int) p2.x + 4, (int) p2.y + 25, 10, 10);
			// g.drawOval((int) p3.x + 4, (int) p3.y + 25, 10, 10);
			poly = new Polygon();

			tmp1 = new NeoPoint(p2.getX(), p2.getY());
			tmp2 = new NeoPoint(p3.getX(), p3.getY());
			tmp0 = new NeoPoint(point.getX(), point.getY());
			if (count == 2) {
				if (NeoPoint.distance(point, tmp2) < NeoPoint.distance(point,
						tmp0)) {
					poly.addPoint((int) tmp1.getX() + 9, (int) tmp1.getY() + 31);
					poly.addPoint((int) tmp2.getX() + 9, (int) tmp2.getY() + 31);
					poly.addPoint((int) point.getX() + 9,
							(int) point.getY() + 31);
				} else if (NeoPoint.distance(point, tmp2) < NeoPoint.distance(
						point, tmp1)) {
					poly.addPoint((int) tmp0.getX() + 9, (int) tmp0.getY() + 31);
					poly.addPoint((int) tmp2.getX() + 9, (int) tmp2.getY() + 31);
					poly.addPoint((int) point.getX() + 9,
							(int) point.getY() + 31);
				} else if (NeoPoint.distance(point, tmp0) < NeoPoint.distance(
						point, tmp2)) {
					poly.addPoint((int) tmp0.getX() + 9, (int) tmp0.getY() + 31);
					poly.addPoint((int) tmp1.getX() + 9, (int) tmp1.getY() + 31);
					poly.addPoint((int) point.getX() + 9,
							(int) point.getY() + 31);
				}

			}

			poly.addPoint((int) point.getX() + 9, (int) point.getY() + 31);
			poly.addPoint((int) p2.getX() + 9, (int) p2.getY() + 31);
			poly.addPoint((int) p3.getX() + 9, (int) p3.getY() + 31);

			Color c = image.getAverageColor(img, (int) point.x, (int) point.y,
					(int) NeoPoint.distance(point, p2) / 6,
					(int) NeoPoint.distance(point, p3) / 6);
			polylist.add(poly);
			colorlist.add(c);

			for (int i = 0; i < polylist.size(); i++) {
				g.drawPolygon(polylist.get(i));
				g.setColor(colorlist.get(i));
				g.fillPolygon(polylist.get(i));
			}
		}
	}

}
