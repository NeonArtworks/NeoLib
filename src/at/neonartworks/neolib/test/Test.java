package at.neonartworks.neolib.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import at.neonartworks.neolib.password.NeoPassword;

public class Test {
	public static void main(String[] args){
		
		String string;
		String news;
		
		// Generate Voronoi
//		NeoVoro vrLib = new NeoVoro(800, 800, 100, VoronoiMethod.VORONOIF);
//		vrLib.generateVoronoi();
//		vrLib.saveVoronoi(true);
		
//		//Logger 
//		NeoLogger logger = new NeoLogger();
//		logger.setLogFile();
//		logger.timeLog("Test", LoggerLevel.INFO);
//		logger.writeLogFile(LoggerLevel.INFO);
//		
//		//Download File from URL
//		NeoDownload dlLib = new NeoDownload();
//		try {
//			dlLib.setURL("https://maps.googleapis.com/maps/api/staticmap?maptype=hybrid&center=47.7958335,13.0277948&zoom=17&size=640x640");
//			dlLib.bufferedDownloadFromURL();
//		} catch (MalformedURLException | FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		//Download Image from Google Maps
//		NeoMap nMap = new NeoMap();
//		nMap.setAPIKey("AIzaSyAAI91j1nm1JECCL3J6l0TteybQLecg7IA");
//		nMap.setZoom(13);
//		nMap.setMap(47.8027886, 12.98639, 640, 640);
//		nMap.downloadMap(640, 640, nMap.getMapURL());
		
		//Password and encryption
//		NeoPassword pw = new NeoPassword();
//		String pwa = pw.generatePassword(25);
//		pw.setPassword(pwa);
//		string = pw.encrypt(pwa);
//		news = pw.decrypt(string);
//		System.out.println(pwa);
//		System.out.println(string);
//		System.out.println(news);
		
		//En/DeCrypt Object
//		NeoCrypt crypt = new NeoCrypt();
//		
	}
}
