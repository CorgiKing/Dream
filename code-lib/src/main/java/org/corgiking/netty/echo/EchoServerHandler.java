package org.corgiking.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		try {
			ByteBuf in = (ByteBuf) msg;
			System.out.println(in.toString(CharsetUtil.US_ASCII));

			ctx.write(msg);
			ctx.flush();
		} finally {
			// msg需要释放,此处因为write后自动释放所以省略
			// ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// 出现异常时关闭连接。
		cause.printStackTrace();
		ctx.close();
	}

}
