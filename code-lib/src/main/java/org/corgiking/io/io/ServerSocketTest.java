package org.corgiking.io.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

import org.corgiking.util.ByteArrayUtil;

public class ServerSocketTest {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		InputStream is = null;
		
		serverSocket = new ServerSocket(5019);
		int recvMsgSize = 0;
		byte[] buf = new byte[1024];
		
		while(true){
			Socket socket = serverSocket.accept();
			SocketAddress clientAddress = socket.getRemoteSocketAddress();
			System.out.println("Handing client at "+clientAddress);
			is = socket.getInputStream();
			byte[] ret = new byte[0];
			while((recvMsgSize = is.read(buf))!=-1){
				ret = ByteArrayUtil.appendArray(buf, 0, ret, recvMsgSize);
			}
			System.out.println(new String(ret));
		}
		
	}

}
