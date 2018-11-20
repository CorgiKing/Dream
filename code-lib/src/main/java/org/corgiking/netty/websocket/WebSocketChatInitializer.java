package org.corgiking.netty.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketChatInitializer extends ChannelInitializer<Channel>{

	private ChannelGroup group;
	
	public WebSocketChatInitializer(ChannelGroup group) {
		this.group = group;
	}
	
	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		
		pipeline.addLast(new HttpServerCodec());
		pipeline.addLast(new HttpObjectAggregator(65535));
		pipeline.addLast(new ChunkedWriteHandler());
		pipeline.addLast(new HttpRequestHandler());
		pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
		pipeline.addLast(new TextWebSocketFrameHandler(group));
		
		
	}

}
