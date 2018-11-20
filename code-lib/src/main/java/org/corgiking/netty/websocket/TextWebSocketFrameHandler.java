package org.corgiking.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{

	private ChannelGroup group;
	
	public TextWebSocketFrameHandler(ChannelGroup group) {
		this.group = group;
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		System.out.println("收到"+ctx.channel().remoteAddress()+"的消息："+msg.text());
		//转发到所有的客户端
//		group.writeAndFlush(msg);
		
		ctx.channel().writeAndFlush(msg);

	}

}
