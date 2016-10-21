package at.neonartworks.neolib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;


/**
 * @author Florian Wagner<br>
 *         NeoCrypt class. Used to En / Decrypt strings.
 */

public class NeoCrypt extends NeoFileHandler {
	private Decoder decoder;
	private Encoder encoder;
	byte[] arrayBytes;
	private NeoPath path;
	private BufferedWriter writer;
	private BufferedReader reader;

	public NeoCrypt() {
		path = new NeoPath();
	}

	/**
	 * Can be used to encrypt complete Files using the NeoFileHandler ->
	 * readFilebyLine method. The encrypted String will be saved with the
	 * NeoFileHandler -> writerFile method.
	 * 
	 */

	public void encryptFile() {
		String d = readFileByLine();
		d = replaceÄÖÜ(d);
		String encrypted = encrypt(d);
		writeFile(encrypted);

	}

	/**
	 * Can be used to decrypt complete Files using the NeoFileHandler ->
	 * readFilebyLine method. The decrypted String will be saved with the
	 * NeoFileHandler -> writerFile method.
	 * 
	 */
	public void decryptFile() {
		String d = readFileByLine();
		String dncrypted = decrypt(d);
		System.out.println(d);
		writeFile(dncrypted);

	}

	/**
	 * Encrypts the String file using the Base64 encoder. This function can be
	 * used to encrypt passwords and complete files.
	 * 
	 * @param unenString
	 * @return
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
	 * Decrypts the input String using the Base64 decoder. This function can be
	 * used to decrypt, encrypted Strings for e.g. passwords.
	 * 
	 * @param enString
	 * @return
	 */
	public String decrypt(String enString) {
		decoder = Base64.getDecoder();
		byte[] bytesDecoded = decoder.decode(enString);
		return new String(bytesDecoded);

	}

	/**
	 * Method to generate a hash code out of an input String.
	 * 
	 * @param password
	 * @return
	 */

	public String generateHash(String input) {
		MessageDigest md;
		String hash = "";
		try {
			md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(input.getBytes());
			byte[] digest = md.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			hash = bigInt.toString(16);

			return hash;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash;
	}
}