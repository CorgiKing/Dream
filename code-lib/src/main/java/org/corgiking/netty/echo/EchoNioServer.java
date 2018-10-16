package org.corgiking.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoNioServer {

	public static void main(String[] args) throws InterruptedException {
		int port = 5019;

		EventLoopGroup group = new NioEventLoopGroup();

		try{
		ServerBootstrap sbs = new ServerBootstrap();

		sbs.group(group)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new EchoServerHandler());
				}
			})
			// 当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
			.option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

		ChannelFuture cf = sbs.bind(port).sync();

		cf.channel().closeFuture().sync();
		}finally {
			group.shutdownGracefully().sync();
		}

	}

}
