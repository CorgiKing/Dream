package org.corgiking.io.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 很快
 * @author Corgi King
 *
 */
public class MappedByteBufferTest {

	public static void main(String[] args) throws Exception {
		RandomAccessFile raFile = new RandomAccessFile("src/main/resources/redis.txt", "rw");
		FileChannel channel = raFile.getChannel();

		long s = System.currentTimeMillis();
		MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, raFile.length());
		buf.clear();
		byte[] dst = new byte[(int) raFile.length()];
		buf.get(dst);
		long e = System.currentTimeMillis();
		System.out.println("MappeByteBuffer用时：" + (e - s));

		raFile.close();
	}

}
