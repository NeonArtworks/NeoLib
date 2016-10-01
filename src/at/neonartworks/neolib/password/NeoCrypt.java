package at.neonartworks.neolib.password;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import at.neonartworks.neolib.NeoPath;
import at.neonartworks.neolib.file.NeoFileHandler;

public class NeoCrypt extends NeoFileHandler{
	private Decoder decoder;
	private Encoder encoder;
	byte[] arrayBytes;
	private NeoPath path;
	private BufferedWriter writer;
	private BufferedReader reader;
	
	/**
	 * @author Florian Wagner
	 * Class to En and DeCrypt Strings
	 * 
	 */
	
	public NeoCrypt() {
		path = new NeoPath();
	}
	/**
	 * Used to encrypt a complete file
	 */
	
	public void encryptFile(){
		String d = readFileByLine();
		String encrypted = encrypt(d);
		writeFile(encrypted);
		
	}
	/**
	 * Used to decrypt a complete file
	 */
	public void decryptFile(){
		String d = readFileByLine();
		String dncrypted = decrypt(d);
		System.out.println(d);
		writeFile(dncrypted);
		
	}
	/**
	 * @param unenString
	 * @return encryptedString
	 * 
	 * returns the encrypted String unenString 
	 */
	public String encrypt(String unenString) {
		encoder = Base64.getEncoder();
		byte[] bytesEncoded = null;
		try {
			bytesEncoded = encoder.encode(unenString.getBytes("UTF8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(bytesEncoded);
	}
	/**
	 * @param enString
	 * @return decryptedString
	 * 
	 * returns the decrypted String enString
	 */
	public String decrypt(String enString) {
		decoder = Base64.getDecoder();
		byte[] bytesDecoded = decoder.decode(enString);
		return new String(bytesDecoded);
		
		
	}
}
