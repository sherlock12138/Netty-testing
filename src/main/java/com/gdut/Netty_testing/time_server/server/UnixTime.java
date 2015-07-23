package com.gdut.Netty_testing.time_server.server;

import java.util.Date;

/**   
 * @Title: UnixTime.java 
 * @Package com.gdut.netty_testing.pojo 
 * @Description: TODO 
 * @author Sherlock-lee   
 * @date 2015年7月8日 上午10:59:06 
 * @version V1.0   
 */
public class UnixTime {
	/** 
	 * @ClassName: UnixTime 
	 * @Description: TODO
	 * @author Sherlock-lee
	 * @date 2015年7月8日 上午10:59:06 
	 */
	private final long value;

    public UnixTime() {
        this(System.currentTimeMillis() / 100L + 2208988800L);
    }

    public UnixTime(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((value() - 2208988800L) * 100L).toString();
    }
	
	
	
	
	
	
	
	
}
