package org.corgiking.io.io;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ByteBufferTest {

	public static void main(String[] args) throws Exception {
		RandomAccessFile raFile = new RandomAccessFile("src/main/resources/demo.txt", "rw");
		FileChannel channel = raFile.getChannel();

		long s = System.currentTimeMillis();
		ByteBuffer buf = ByteBuffer.allocate((int) raFile.length());
		buf.clear();
		channel.read(buf);
		long e = System.currentTimeMillis();
		System.out.println("ByteBuffer用时：" + (e - s));

		raFile.close();
	}

}
