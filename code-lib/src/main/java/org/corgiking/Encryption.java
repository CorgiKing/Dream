package org.corgiking;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Encryption {

	public static void main(String[] args) throws IOException {
		Encryption encryption = new Encryption();
		// encryption.handleFile("yy.rar","yyy.rar");
		long old = 1074635012231110540l;
		System.out.println(old);
		byte[] bs = encryption.longToByteArray(old);
		long lo = encryption.byteArrayToLong(bs);
		System.out.println(lo);
	}

	public void handleFile(String inFileName) {
		handleFile(inFileName, inFileName + ".enc");
	}

	public void handleFile(String inFileName, String outFileName) {
		File inFile = new File(inFileName);
		File outFile = new File(outFileName);

		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(inFile);
			fos = new FileOutputStream(outFile);

			long t = System.currentTimeMillis();
			// 边读边写
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = fis.read(buf)) > 0) {
				// 加密
				buf = encrypt(buf, len);

				fos.write(buf, 0, len);
				System.out.println("数据长度:" + len);
			}
			System.out.println("总用时:" + (System.currentTimeMillis() - t));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private byte[] encrypt(byte[] buf, int len) {

		Long t = System.currentTimeMillis();

		for (byte b : buf) {
			System.out.println(b);
		}

		return buf;
	}

	private byte format(byte old) {
		int n = old;
		while (true) {

			if (n > 127) {
				n = -128 + (n - 127 - 1);
			} else if (n < -128) {
				n = 127 - (-128 - n - 1);
			} else {
				break;
			}
		}

		return (byte) n;
	}

	private byte[] longToByteArray(Long n) {
		byte[] bs = new byte[8];

		for (int i = 0; i < bs.length; i++) {
			bs[i] = n.byteValue();
			n >>= 8;
			System.out.println(bs[i]);
		}
		return bs;
	}

	private long byteArrayToLong(byte[] bs) {
		long n = 0;

		for (byte b : bs) {
//			n |=b;
			n <<= 8;
		}

		return n;
	}

}
