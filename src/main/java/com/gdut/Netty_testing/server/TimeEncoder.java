package com.gdut.Netty_testing.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @Title: TimeEncoder.java
 * @Package com.gdut.netty_testing.pojo
 * @Description: TODO
 * @author Sherlock-lee
 * @date 2015年7月8日 上午11:08:10
 * @version V1.0
 */
public class TimeEncoder extends ChannelOutboundHandlerAdapter {
	/**
	 * @ClassName: TimeEncoder
	 * @Description: 处理出站的返回
	 * @author Sherlock-lee
	 * @date 2015年7月8日 上午11:08:10
	 */
	// @Override
	// protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf
	// out) {
	// out.writeInt((int) msg.value());
	// }

	@Override
	public void write(ChannelHandlerContext ctx, Object msg,
			ChannelPromise promise) {
		UnixTime m = (UnixTime) msg;
		ByteBuf encoded = ctx.alloc().buffer(4);
		encoded.writeInt((int) m.value());
		ctx.write(encoded, promise); // (1)
	}
}
