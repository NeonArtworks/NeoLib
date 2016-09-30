package at.neonartworks.neolib;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class NeoUI {
	
	/**
	 * Sets the UI to the System specific Look and Feel
	 */
	
	public NeoUI(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
