package org.corgiking.other;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

public class FileMD5 {

	public static void main(String[] args) throws Exception {
		String md5File = md5File("src/main/resources/demo.txt");
		System.out.println(md5File);
	}
	
	private static String md5File(String fileName) throws Exception {
		MessageDigest msgDigest = MessageDigest.getInstance("md5");
		
		FileInputStream fis = new FileInputStream(fileName);
		
		int len;
		byte[] buf = new byte[1024];
		while((len = fis.read(buf)) > 0) {
			msgDigest.update(buf, 0, len);
		}
		
		return new BigInteger(1, msgDigest.digest()).toString(16);
		
	}

}
