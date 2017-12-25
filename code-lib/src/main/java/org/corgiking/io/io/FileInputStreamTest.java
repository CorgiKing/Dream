package org.corgiking.io.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.corgiking.util.ByteArrayUtil;

public class FileInputStreamTest {
	public static void main(String[] args) {
		InputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream("src/main/resources/demo.txt"));
			byte[] buf = new byte[1024];
			int byteRead = is.read(buf);
			byte[] ret = new byte[0];
			while (byteRead != -1) {
				ret = ByteArrayUtil.appendArray(buf, 0, ret, byteRead);
				byteRead = is.read(buf);
			}
			System.out.println(new String(ret));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
