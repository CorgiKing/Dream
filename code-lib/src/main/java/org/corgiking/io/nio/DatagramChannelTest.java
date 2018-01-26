package org.corgiking.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelTest {

	public static void main(String[] args) throws IOException {

		DatagramChannel channel = DatagramChannel.open();
		channel.socket().bind(new InetSocketAddress(5019));

		// 发送数据
		String data = " I am data to test " + System.currentTimeMillis();
		ByteBuffer buf2 = ByteBuffer.allocate(1024);
		buf2.clear();
		buf2.put(data.getBytes());
		buf2.flip();

		int sent = channel.send(buf2, new InetSocketAddress("localhost", 5019));

		
		// 接收数据
		// receive()方法会将接收到的数据包内容复制到指定的Buffer. 如果Buffer容不下收到的数据，多出的数据将被丢弃
		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.clear();
		channel.receive(buf);
		
		
		//连接到指定地址
		channel.connect(new InetSocketAddress("localhost", 5019));
		ByteBuffer buf3 = ByteBuffer.allocate(1024);
		int bytesRead = channel.read(buf3);
		int bytesWritten = channel.write(buf3);

		
	}

}
