package org.corgiking.netty.websocket;

import java.io.RandomAccessFile;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
		if (msg.uri().contains("/ws")) {
			ctx.fireChannelRead(msg.retain());
		}else {
			RandomAccessFile file = new RandomAccessFile("src/main/resources/webSocketIndex.html", "r");
			
			HttpResponse response = new DefaultHttpResponse(msg.protocolVersion(), HttpResponseStatus.OK);
			response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
			boolean keepAlive = HttpUtil.isKeepAlive(msg);
            if (keepAlive) {
                response.headers().set(HttpHeaderNames.CONTENT_LENGTH, file.length());
                response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            }
            ctx.write(response);
            
            if (ctx.pipeline().get(SslHandler.class) == null) {
                ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
            } else {
                ctx.write(new ChunkedNioFile(file.getChannel()));
            }
            
            ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            future.addListener(new GenericFutureListener<Future<? super Void>>() {

				@Override
				public void operationComplete(Future<? super Void> future) throws Exception {
					file.close();
				}
			});
            if (!keepAlive) {
                future.addListener(ChannelFutureListener.CLOSE);
            }
		}
	}

}
