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
package com.gdut.Netty_testing.time_server.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import com.gdut.Netty_testing.time_server.server.UnixTime;

/**
 * 
* @Title: TimeClientHandler.java 
* @Package com.gdut.netty_testing.pojo.client 
* @Description: 处理进站的访问 
* @author Sherlock-lee   
* @date 2015年7月8日 下午11:54:57 
* @version V1.0
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {


//	@Override
//	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		
//	};
    @Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		UnixTime m = (UnixTime) msg;
		System.out.println(m);
		ctx.writeAndFlush(m);
	}

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
