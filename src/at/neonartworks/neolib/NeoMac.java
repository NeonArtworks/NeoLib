package at.neonartworks.neolib;

import java.io.InputStreamReader;

/**
 * 
 * Originally only a test class.
 * Can be used to retrieve the LAN -MacAddress.
 * @author Florian Wagner
 *
 */
public class NeoMac {

	public NeoMac() {

	}

	/**
	 * Returns the MacAddress of the LAN (Windows ONLY!)
	 * 
	 * @return MacAddress as String
	 * 
	 */

	public String getMacAddress() {
		String address = "";
		try {
			Process p = Runtime.getRuntime().exec("getmac /fo csv /nh");
			java.io.BufferedReader in = new java.io.BufferedReader(
					new InputStreamReader(p.getInputStream()));
			String line;
			line = in.readLine();
			String[] result = line.split(",");
			address = result[0].replace('"', ' ').trim();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return address;
	}
}