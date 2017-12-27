package org.corgiking.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerSocketChannelTest {

	public static void main(String[] args) throws Exception {
		Selector selector = Selector.open();
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		//绑定端口
		serverChannel.socket().bind(new InetSocketAddress(5019));
		//关闭阻塞
		serverChannel.configureBlocking(false);
		//注册selector,与Selector一起使用时，Channel必须处于非阻塞模式下
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		while(true){
			int timeout = 5000;
			if (selector.select(timeout) == 0) {
				System.out.println("没有可操作IO");
				continue;
			}
			
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			for(Iterator<SelectionKey> iter=selectedKeys.iterator();iter.hasNext();){
				SelectionKey key = iter.next();
				if(key.isAcceptable()){
                    handleAccept(key);
                }
                if(key.isReadable()){
                    handleRead(key);
                }
                if(key.isWritable() && key.isValid()){
                    handleWrite(key);
                }
                if(key.isConnectable()){
                    System.out.println("isConnectable = true");
                }
                iter.remove();
			}
		}
		
	}

	private static void handleAccept(SelectionKey key) throws Exception {
		ServerSocketChannel ssChannel = (ServerSocketChannel)key.channel();
        SocketChannel sc = ssChannel.accept();
        if (sc != null) {
        	sc.configureBlocking(false);
        	sc.register(key.selector(), SelectionKey.OP_READ,ByteBuffer.allocateDirect(1024));			
		}
	}

	private static void handleRead(SelectionKey key) {
		// TODO Auto-generated method stub
		
	}

	private static void handleWrite(SelectionKey key) {
		// TODO Auto-generated method stub
		
	}

}
