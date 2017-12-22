package org.corgiking.io.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {

	public static void main(String[] args) {
		RandomAccessFile raFile = null;
		try {
			raFile = new RandomAccessFile("src/main/resources/demo.txt","rw");
			FileChannel fileChannel = raFile.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(1024);
			buf.flip();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
