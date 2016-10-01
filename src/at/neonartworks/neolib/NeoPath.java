package at.neonartworks.neolib;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class NeoPath  extends NeoUI{
	/**
	 * @author Florian Wagner <br>
	 * Simple Path class for defining paths in this lib.
	 * 
	 */
	private String path;
	private JFrame saveFrame;
	
	public NeoPath(){
		
	}
	
	public NeoPath(String path){
		this.path = path;
	}
	/**
	 * @param path
	 * 
	 * Allows to set a Path based on the input String.
	 */
	public void setPathManual(String path){
		this.path = path;
	}
	/**
	 * 
	 * Sets the file path with an integrated FileChooser.
	 */
	public void setPath(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Choose a File");   

		int userSelection = fileChooser.showSaveDialog(saveFrame);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			this.path = fileToSave.getAbsolutePath();
		}
	}
	
	public String getPath(){
		return this.path;
	}
	
}
