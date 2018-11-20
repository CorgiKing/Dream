package org.corgiking.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;

public class WebSocketChatServer {

	public static void main(String[] args) {
		ServerBootstrap bootstrap  = new ServerBootstrap();
		EventLoopGroup group = new NioEventLoopGroup();
		ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
		
        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WebSocketChatInitializer(channelGroup));
        ChannelFuture future = bootstrap.bind(5019);
	}

}
