/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.gdut.Netty_testing.dongjun.server.handler.inboundhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import com.gdut.Netty_testing.dongjun.server.Command;

public class ElectricControlServerHandler extends ChannelInboundHandlerAdapter {

	@SuppressWarnings("unused")
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {

		Command com = new Command((String) msg);
		String val = "";
		if (com != null) {
			val = com.getValue();
		} else {
			ctx.close();
			return;
		}
		if (val.startsWith("10")) {
			ctx.channel().writeAndFlush("104901004A16");
			System.out.println("定长帧");
		} else if (val.startsWith("68")) {
			System.out.println("可变长帧");
		}

	};

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

}
