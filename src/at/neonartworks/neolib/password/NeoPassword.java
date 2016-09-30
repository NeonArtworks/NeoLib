package at.neonartworks.neolib.password;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Random;

public class NeoPassword extends NeoCrypt{

	private Random random = new Random();
	private StringBuilder sb;
	private String password;
	
	private String[] symbols = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "10", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
			"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
			"y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
			"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
			"Y", "Z", "!", "@", "ö", "ä", "<", ">", "|", "ß", "\\", "/", "§",
			"$", "%", ";", ",", "&" };
	
	byte[] arrayBytes;
	private String priKey;
	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	
	
	public String getPassword(){
		return this.priKey;
	}
	public void setPassword(String key){
		this.priKey = key;
	}
	
	public String generatePassword(int length) {
		sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int iRan = random.nextInt(symbols.length);
			sb.append(symbols[iRan]);
		}
		password = sb.toString();
		return password;
	}
	
	public String generateHash(String password) {
		MessageDigest md;
		String hash = "";
		try {
			md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(password.getBytes());
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