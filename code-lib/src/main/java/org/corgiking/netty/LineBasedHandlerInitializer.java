package org.corgiking.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.LineBasedFrameDecoder;

public class LineBasedHandlerInitializer extends ChannelInitializer<Channel> {

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new LineBasedFrameDecoder(65 * 1024));
		pipeline.addLast(new FrameHandler());
	}

	public static final class FrameHandler extends SimpleChannelInboundHandler<ByteBuf> {
		@Override
		public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
			// Do something with the frame
		}
	}
}
