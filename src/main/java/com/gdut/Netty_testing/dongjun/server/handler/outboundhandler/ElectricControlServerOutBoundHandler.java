package com.gdut.Netty_testing.dongjun.server.handler.outboundhandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @Title: ElectricControlServerOutBoundHandler.java
 * @Package com.gdut.Netty_testing.dongjun.server.handler.outboundhandler
 * @Description: TODO
 * @author Sherlock-lee
 * @date 2015年7月14日 下午11:56:46
 * @version V1.0
 */
public class ElectricControlServerOutBoundHandler extends
		ChannelOutboundHandlerAdapter {
	/**
	 * @ClassName: ElectricControlServerOutBoundHandler
	 * @Description: TODO
	 * @author Sherlock-lee
	 * @date 2015年7月14日 下午11:56:46
	 */
	// Stateful properties
	private volatile Channel channel;

//	@Override
//	public void channelRead(ChannelHandlerContext ctx, Object msg) { // (1)
//
//		while (true) {
//
//			ctx.writeAndFlush("10235555"); // (3)
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}

	@Override
	public void read(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		while (true) {

			ctx.writeAndFlush("10235555"); // (3)
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	public void heartBeat() {

		channel.writeAndFlush("10235555");
	}

}
