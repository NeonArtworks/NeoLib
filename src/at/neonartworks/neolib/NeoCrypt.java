package at.neonartworks.neolib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class NeoCrypt {
	private Decoder decoder;
	private Encoder encoder;
	byte[] arrayBytes;
	private NeoPath path;
	private BufferedWriter writer;
	private BufferedReader reader;
	
	
	public NeoCrypt() {
		path = new NeoPath();
	}
	
	
	
	public String encrypt(String unenString) {
		encoder = Base64.getEncoder();
		byte[] bytesEncoded = encoder.encode(unenString.getBytes());
		return new String(bytesEncoded);
	}

	public String decrypt(String enString) {
		decoder = Base64.getDecoder();
		byte[] bytesDecoded = decoder.decode(enString);
		return new String(bytesDecoded);
	}
}
