package org.corgiking.httpserver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;

public class CKHttpServer {

	public static void main(String[] args) throws Exception {

		// 设置监听端口
		int port = 50109;
		String contentType = "text/plain";
		String encoding = "utf-8";
		String content = "Hello World!";

		String head_tmp = "HTTP/1.0 200 OK\r\n" 
						+ "Content-type: " + contentType + "\r\n"
						+ "Content-length: " + content.getBytes().length+ "\r\n" 
						+ "Server: OneFile 1.0\r\n\r\n"
						;
		byte[] head = head_tmp.getBytes("ASCII");

		ServerSocket server = new ServerSocket(port);
		System.out.println("Accepting connections on port " + server.getLocalPort());
		System.out.println("Data to be sent:");

		while (true) {
			Socket con = null;

			System.out.println("accept:");
			con = server.accept();
			System.out.println("Welcome :"+con.getInetAddress());
			
			OutputStream out = new BufferedOutputStream(con.getOutputStream());
			InputStream in = new BufferedInputStream(con.getInputStream());

			//读取信息
			byte[] buf = new byte[1024];
			in.read(buf);
			
			String req = new String(buf);
			System.out.println("req:\r\n"+req);
			
			//发送信息
			out.write(head);
			out.write(content.getBytes());
			out.flush();
			System.out.println("rep:\r\n"+content);
		}
	}

	private static void net() throws IOException {
		ServerSocket server = new ServerSocket(2222);
		while (true) {
			try {
				Socket client = server.accept();
				OutputStream out = client.getOutputStream();
				DataOutputStream outStream = new DataOutputStream(client.getOutputStream());
				outStream.write("Hello".getBytes());
				InputStream in = client.getInputStream();
				byte[] buf = new byte[1024];
				in.read(buf);
				in.close();
				System.out.println("request from client " + client.getInetAddress().getHostAddress());
				System.out.println(new String(buf));
				client.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void test1() throws IOException {
		ServerSocket server = new ServerSocket(50109);
		int i = 0;
		while (i == 0) {
			System.out.println("accept:");
			Socket socket = server.accept();
			System.out.println("Welconme:" + socket.getInetAddress());

			// 接收
			InputStream dis = socket.getInputStream();
			byte[] req = new byte[1024];
			dis.read(req);
			// String req = dis.readUTF();
			System.out.println("req:  " + new String(req));

			// 反馈
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

			dos.write("hello world".getBytes());
			System.out.println("write success");
		}
		server.close();
	}

}
