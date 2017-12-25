package org.corgiking.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class ClientSocketChannelTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		ByteBuffer buf = ByteBuffer.allocate(1024);
		SocketChannel socketChannel = null;

		socketChannel = SocketChannel.open();
		// 关闭阻塞
		socketChannel.configureBlocking(false);
		socketChannel.connect(new InetSocketAddress("127.0.0.1", 5019));

		if (socketChannel.finishConnect()) {
			int i = 0;
			while (true) {
				TimeUnit.SECONDS.sleep(1);
				String info = "I'm "+i+++"-th information from client";
				buf.clear();
				buf.put(info.getBytes());
				buf.flip();
				while(buf.hasRemaining()){
					socketChannel.write(buf);
					System.out.println("client:"+info);
				}
			}
		}
	}

}
