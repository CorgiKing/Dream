package org.corgiking.netty;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

public class MyCodec extends ByteToMessageCodec<String>{

	@Override
	protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
