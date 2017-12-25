package org.corgiking.io.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.corgiking.util.ByteArrayUtil;

public class FileChannelTest {

	public static void main(String[] args) {
		RandomAccessFile raFile = null;
		try {
			raFile = new RandomAccessFile("src/main/resources/demo.txt","rw");
			FileChannel fileChannel = raFile.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(1024);
			
			int bytesRead = fileChannel.read(buf);
			byte[] ret = new byte[0];
			while(bytesRead != -1){
				buf.flip();
				ret = ByteArrayUtil.appendArray(buf.array(), 0, ret, bytesRead);
				bytesRead = fileChannel.read(buf);
			}
			System.out.println(new String(ret));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
