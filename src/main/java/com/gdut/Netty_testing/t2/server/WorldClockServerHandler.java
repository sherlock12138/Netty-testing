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
package com.gdut.Netty_testing.t2.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Calendar;
import java.util.TimeZone;

import static java.util.Calendar.*;

public class WorldClockServerHandler extends SimpleChannelInboundHandler<WorldClockProtocol.Locations> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, WorldClockProtocol.Locations locations) throws Exception {
        long currentTime = System.currentTimeMillis();

        WorldClockProtocol.LocalTimes.Builder builder = WorldClockProtocol.LocalTimes.newBuilder();
        for (WorldClockProtocol.Location l: locations.getLocationList()) {
            TimeZone tz = TimeZone.getTimeZone(
                    toString(l.getContinent()) + '/' + l.getCity());
            Calendar calendar = getInstance(tz);
            calendar.setTimeInMillis(currentTime);

            builder.addLocalTime(WorldClockProtocol.LocalTime.newBuilder().
                    setYear(calendar.get(YEAR)).
                    setMonth(calendar.get(MONTH) + 1).
                    setDayOfMonth(calendar.get(DAY_OF_MONTH)).
                    setDayOfWeek(WorldClockProtocol.DayOfWeek.valueOf(calendar.get(DAY_OF_WEEK))).
                    setHour(calendar.get(HOUR_OF_DAY)).
                    setMinute(calendar.get(MINUTE)).
                    setSecond(calendar.get(SECOND)).build());
        }

        /**
         * 服务器返回结果，值是放在protocol的属性中，也是框架建议的做法
         */
        ctx.write(builder.build());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    private static String toString(WorldClockProtocol.Continent c) {
        return c.name().charAt(0) + c.name().toLowerCase().substring(1);
    }
}
