package at.neonartworks.neolib.testClasses;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import at.neonartworks.neolib.NeoPath;
import at.neonartworks.neolib.NeoUI;
import at.neonartworks.neolib.image.Mandelbrot;
import at.neonartworks.neolib.image.NeoImage;
import at.neonartworks.neolib.image.NeoPreview;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.Toolkit;

/**
 * 
 * @author Florian Wagner
 *
 */
public class MandelbrotSet_WIndow extends JFrame {

	private static final long serialVersionUID = -5482356461130213193L;
	private JPanel contentPane;
	private BufferedImage buff;
	private BufferedImage buffHQ;
	private Mandelbrot mand;
	// private Graphics g;
	private JPanel DrawPanel;
	private boolean redraw, invMouse, renderJulia;
	private JSlider slOffY;
	private JSlider slZoom;
	private JSlider slOffX;
	private JSlider slQual;
	private NeoImage img = new NeoImage();
	private JTextField txMXZoom;
	private JTextField txMXOff;
	private JComboBox<String> cbColor;
	private JComboBox<String> cbLoadSave;
	private NeoPath nPath = new NeoPath();
	private BufferedWriter writer;
	private BufferedReader reader;
	private int xA, yA;
	private JRadioButton rdInvMouse;
	private JRadioButton rdRenderJulia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MandelbrotSet_WIndow frame = new MandelbrotSet_WIndow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MandelbrotSet_WIndow() {

		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						MandelbrotSet_WIndow.class
								.getResource("/at/neonartworks/neolib/testClasses/resources/mbIcon.png")));
		setResizable(false);

		new NeoUI();
		// g = getGraphics();

		setTitle("Mandelbrot Set");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		DrawPanel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(buff, 0, 0, getWidth(), getHeight(), this);
			}
		};
		DrawPanel.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent w) {
				if (renderJulia == true) {
					int wZoom = w.getWheelRotation();
					slZoom.setValue(slZoom.getValue() - wZoom);
					slZoom.setMaximum(slZoom.getValue() - wZoom);
					txMXZoom.setText(String.valueOf(slZoom.getValue() - wZoom));
				} else {
					int wZoom = w.getWheelRotation() * 30;
					slZoom.setValue(slZoom.getValue() - wZoom);
					slZoom.setMaximum(slZoom.getValue() - wZoom);
					txMXZoom.setText(String.valueOf(slZoom.getValue() - wZoom));
				}
			}
		});

		DrawPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xA = e.getX();
				yA = e.getY();
			}
		});

		DrawPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent a) {

				if (renderJulia == true) {
					if (invMouse == false) {
						int dx = xA - (a.getX());
						int dy = yA - (a.getY());

						dx = dx / 400;
						dy = dy / 400;

						slOffX.setValue(slOffX.getValue() + (dx));
						slOffY.setValue(slOffY.getValue() + (dy));
						slOffX.setMaximum(slOffX.getValue() + (dx));
						slOffY.setMaximum(slOffY.getValue() + (dy));
					} else {
						int dx = xA - (a.getX());
						int dy = yA - (a.getY());

						dx = dx / 400;
						dy = dy / 400;

						slOffX.setValue(slOffX.getValue() - (dx));
						slOffY.setValue(slOffY.getValue() - (dy));
						slOffX.setMaximum(slOffX.getValue() - (dx));
						slOffY.setMaximum(slOffY.getValue() - (dy));
					}

					// txMXOff.setText(String.valueOf(slOffY.getValue() +
					// (dx/3)));

				} else {
					if (invMouse == false) {
						float dx = xA - (a.getX());
						float dy = yA - (a.getY());
						slOffX.setValue((int) (slOffX.getValue() + (dx/50)));
						slOffY.setValue((int) (slOffY.getValue() + (dy/50)));
						slOffX.setMaximum((int) (slOffX.getValue() + (dx/50)));
						slOffY.setMaximum((int) (slOffY.getValue() + (dy/50)));
					} else {
						float dx = xA - (a.getX());
						float dy = yA - (a.getY());

						slOffX.setValue((int) (slOffX.getValue() - (dx / 50)));
						slOffY.setValue((int) (slOffY.getValue() - (dy / 50)));
						slOffX.setMaximum((int) (slOffX.getValue() - (dx / 50)));
						slOffY.setMaximum((int) (slOffY.getValue() - (dy / 50)));
					}
				}
			}
		});

		DrawPanel.setBounds(0, 0, 643, 315);
		contentPane.add(DrawPanel);
		DrawPanel.setLayout(null);

		JLabel lblPreviewOnly = new JLabel("Preview ONLY!");
		lblPreviewOnly.setFont(new Font("Sansumi", Font.BOLD, 11));
		lblPreviewOnly.setForeground(new Color(255, 255, 255));
		lblPreviewOnly.setBounds(10, 11, 204, 40);
		DrawPanel.add(lblPreviewOnly);

		JLabel lblPreviewMayDiffer = new JLabel(
				"Preview may differ from the HQ Image!");
		lblPreviewMayDiffer.setForeground(new Color(255, 255, 255));
		lblPreviewMayDiffer.setFont(new Font("Sansumi", Font.BOLD, 11));
		lblPreviewMayDiffer.setBounds(10, 52, 429, 40);
		DrawPanel.add(lblPreviewMayDiffer);

		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(0, 0, 643, 26);
		DrawPanel.add(menuBar_1);
		cbLoadSave = new JComboBox<String>();
		menuBar_1.add(cbLoadSave);

		JRadioButton rbReOnChange = new JRadioButton("Render on Change");
		rbReOnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (rbReOnChange.isSelected()) {
					redraw = true;
				} else {
					redraw = false;
				}

			}
		});
		menuBar_1.add(rbReOnChange);

		rdInvMouse = new JRadioButton("Invert Mouse Movement");
		rdInvMouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (rdInvMouse.isSelected()) {
					invMouse = true;
				} else {
					invMouse = false;
				}

			}
		});
		menuBar_1.add(rdInvMouse);

		rdRenderJulia = new JRadioButton("Render Julia set");
		rdRenderJulia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (rdRenderJulia.isSelected()) {
					renderJulia = true;
				} else {
					renderJulia = false;
				}
			}
		});
		menuBar_1.add(rdRenderJulia);

		cbLoadSave.addItem("Load");
		cbLoadSave.addItem("Save");
		cbLoadSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int index = cbLoadSave.getSelectedIndex();

				if (index == 0) {
					nPath.pathDialoge();
					try {
						reader = new BufferedReader(new FileReader(nPath
								.getPath()));

						String line;
						StringBuilder sb = new StringBuilder();
						while ((line = reader.readLine()) != null) {
							sb.append(line + System.lineSeparator());
						}

						String[] d = sb.toString()
								.split(System.lineSeparator());
						if (Integer.parseInt(d[0]) > slZoom.getMaximum()) {
							slZoom.setMaximum(Integer.parseInt(d[0]));
							txMXZoom.setText(String.valueOf(d[0]));
							slZoom.setValue(Integer.parseInt(d[0]));
						}
						if (Integer.parseInt(d[1]) > slOffX.getMaximum()) {
							slOffX.setMaximum(Integer.parseInt(d[1]));
							txMXOff.setText(String.valueOf(d[1]));
							slOffX.setValue(Integer.parseInt(d[1]));
						}
						if (Integer.parseInt(d[2]) > slOffY.getMaximum()) {
							slOffY.setMaximum(Integer.parseInt(d[2]));
							slOffY.setValue(Integer.parseInt(d[2]));
						}
						if (Integer.parseInt(d[3]) > slQual.getMaximum()) {
							slQual.setValue(Integer.parseInt(d[3]));
						} else {
							slZoom.setValue(Integer.parseInt(d[0]));
							slOffY.setMaximum(Integer.parseInt(d[2]));
							slOffX.setValue(Integer.parseInt(d[1]));
							slQual.setValue(Integer.parseInt(d[3]));

						}

					} catch (IOException e1) {

						e1.printStackTrace();
					} finally {
						try {
							reader.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

					// System.out.println("LOAD");

				} else if (index == 1) {
					String path;

					nPath.pathDialoge();

					if (nPath.getPath().contains(".txt")) {
						path = nPath.getPath();
					} else {
						path = nPath.getPath() + ".txt";
					}

					try {
						writer = new BufferedWriter(new FileWriter(path));

						writer.write(getItems());

					} catch (IOException e1) {
						e1.printStackTrace();
					} finally {
						try {
							writer.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

					// System.out.println("File Saved to: " + nPath.getPath());

				} else {
					System.out.println("WRONG");
				}

			}
		});

		JLabel lbZoom = new JLabel("Zoom level: ");
		lbZoom.setBounds(10, 411, 180, 14);
		contentPane.add(lbZoom);

		JLabel lbOffX = new JLabel("X-Offset: ");
		lbOffX.setBounds(12, 434, 180, 14);
		contentPane.add(lbOffX);

		JLabel lbOffY = new JLabel("Y-Offset: ");
		lbOffY.setBounds(202, 434, 180, 14);
		contentPane.add(lbOffY);

		JLabel lbQuality = new JLabel("Quality level: ");
		lbQuality.setBounds(200, 411, 180, 14);
		contentPane.add(lbQuality);

		slZoom = new JSlider();
		slZoom.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {

				lbZoom.setText("Zoom Level: " + slZoom.getValue());
				if (redraw) {
					ClacMandelbrot();
				}

			}
		});
		slZoom.setSnapToTicks(true);
		slZoom.setPaintTicks(true);
		slZoom.setValue(100);
		slZoom.setMaximum(5000);
		slZoom.setBounds(48, 337, 407, 26);
		contentPane.add(slZoom);

		slOffX = new JSlider();
		slOffX.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				lbOffX.setText("X-Offset: " + slOffX.getValue());
				if (redraw) {
					ClacMandelbrot();
				}

			}
		});
		slOffX.setSnapToTicks(true);
		slOffX.setPaintTicks(true);
		slOffX.setValue(0);
		slOffX.setMaximum(1000);
		slOffX.setBounds(58, 374, 165, 26);
		contentPane.add(slOffX);

		slOffY = new JSlider();
		slOffY.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				lbOffY.setText("Y-Offset: " + slOffY.getValue());
				if (redraw) {
					ClacMandelbrot();
				}

			}
		});
		slOffY.setSnapToTicks(true);
		slOffY.setPaintTicks(true);
		slOffY.setValue(0);
		slOffY.setMaximum(1000);
		slOffY.setBounds(290, 374, 165, 26);
		contentPane.add(slOffY);
		JLabel lblZoom = new JLabel("Zoom");
		lblZoom.setBounds(12, 339, 46, 14);
		contentPane.add(lblZoom);

		JLabel lblOffx = new JLabel("offX");
		lblOffx.setBounds(15, 374, 46, 14);
		contentPane.add(lblOffx);

		JLabel lblOffy = new JLabel("offY");
		lblOffy.setBounds(245, 375, 46, 14);
		contentPane.add(lblOffy);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 326, 637, 2);
		contentPane.add(separator);

		slQual = new JSlider();
		slQual.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				lbQuality.setText("Quality Level: " + slQual.getValue());
				if (redraw) {
					ClacMandelbrot();
				}

			}
		});
		slQual.setMinimum(1);
		slQual.setMaximum(10);
		slQual.setBounds(68, 460, 460, 26);
		contentPane.add(slQual);

		JLabel lblQuality = new JLabel("Quality");
		lblQuality.setBounds(12, 466, 46, 14);
		contentPane.add(lblQuality);
		JButton btnCalculate = new JButton("Render");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClacMandelbrot();

			}
		});
		btnCalculate.setBounds(538, 497, 89, 23);
		contentPane.add(btnCalculate);

		JButton btnRenderHqImage = new JButton("Render HQ Image");
		btnRenderHqImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clacHQMandlebrot();

			}
		});

		btnRenderHqImage.setBounds(409, 497, 119, 23);
		contentPane.add(btnRenderHqImage);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 400, 497, 2);
		contentPane.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(1, 459, 497, 2);
		contentPane.add(separator_2);

		cbColor = new JComboBox<String>();
		cbColor.setBounds(15, 499, 168, 23);
		contentPane.add(cbColor);

		cbColor.addItem("Red");
		cbColor.addItem("Green");
		cbColor.addItem("Blue");
		cbColor.addItem("Colorfull");

		JButton btnSaveImage = new JButton("Save Image");
		btnSaveImage.setBounds(202, 497, 89, 23);
		contentPane.add(btnSaveImage);

		txMXZoom = new JTextField();
		txMXZoom.setText("5000");
		txMXZoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int max = Integer.parseInt(txMXZoom.getText());
					slZoom.setMaximum(max);
				} catch (IllegalArgumentException a) {

				}
			}
		});
		txMXZoom.setBounds(551, 337, 86, 20);
		contentPane.add(txMXZoom);
		txMXZoom.setColumns(10);

		JLabel lblMaxZoom = new JLabel("Max. Zoom");
		lblMaxZoom.setBounds(470, 343, 75, 14);
		contentPane.add(lblMaxZoom);

		JLabel lblMaxOffset = new JLabel("Max. Offset");
		lblMaxOffset.setBounds(469, 372, 75, 14);
		contentPane.add(lblMaxOffset);

		txMXOff = new JTextField();
		txMXOff.setText("1000");
		txMXOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int max = Integer.parseInt(txMXOff.getText());
					slOffX.setMaximum(max);
					slOffY.setMaximum(max);
				} catch (IllegalArgumentException a) {

				}

			}
		});
		txMXOff.setColumns(10);
		txMXOff.setBounds(551, 366, 85, 20);
		contentPane.add(txMXOff);
		btnSaveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clacHQMandlebrot();
				img.saveImage(buffHQ);
			}
		});

	}

	private String getItems() {
		StringBuilder sb = new StringBuilder();
		sb.append(slZoom.getValue() + System.lineSeparator());
		sb.append(slOffX.getValue() + System.lineSeparator());
		sb.append(slOffY.getValue() + System.lineSeparator());
		sb.append(slQual.getValue() + System.lineSeparator());
		return sb.toString();
	}

	private void clacHQMandlebrot() {
		Thread thread = new Thread() {
			public void run() {
				String s;
				int i = cbColor.getSelectedIndex();
				if (i == 0) {
					s = "r";
				} else if (i == 1) {
					s = "g";
				} else if (i == 2) {
					s = "b";
				} else {
					s = "lsd";
				}
				int _zoomLevel;
				if (renderJulia == true) {
					_zoomLevel = (int) (slZoom.getValue());
				} else {
					_zoomLevel = (int) (slZoom.getValue()*4.8);
				}

				int _offX = (int) (slOffX.getValue()/100);
				int _offY = (int) (slOffY.getValue()/100);
				int qual = (int) (slQual.getValue() * 4.8);
				mand = new Mandelbrot(1920, 1080, 1200, _zoomLevel, qual, s,
						_offX, _offY);
				// mand.setMandelbrotColor(new Color(255, 255, 255));
				if (renderJulia == true) {
					buffHQ = mand.getRenderedJulia();
				} else {
					buffHQ = mand.getRenderedMandelbrot();
				}

				new NeoPreview(buffHQ, getHeight(), getWidth(),
						"Mandelbrot HQ Imgae");
			}
		};
		thread.start();
	}

	private void ClacMandelbrot() {
		Thread thread = new Thread() {
			public void run() {
				String s;
				int i = cbColor.getSelectedIndex();
				if (i == 0) {
					s = "r";
				} else if (i == 1) {
					s = "g";
				} else if (i == 2) {
					s = "b";
				} else {
					s = "lsd";
				}

				int _zoomLevel = (slZoom.getValue());
				int _offX = slOffX.getValue()/100;
				int _offY = slOffY.getValue()/100;
				int qual = slQual.getValue();
				int x = 200;
				int y = 100;

				mand = new Mandelbrot(x, y, 200, _zoomLevel, qual, s, _offX,
						_offY);
				// mand.setMandelbrotColor(new Color(255, 255, 255));
				if (renderJulia == true) {
					buff = mand.getRenderedJulia();
				} else {
					buff = mand.getRenderedMandelbrot();
				}

				repaint();

			}
		};
		thread.start();
		try {
			Thread.sleep(10L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}