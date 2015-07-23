package com.gdut.Netty_testing.dongjun.server;

/**   
 * @Title: Msg.java 
 * @Package com.gdut.Netty_testing.dongjun.server 
 * @Description: TODO 
 * @author Sherlock-lee   
 * @date 2015年7月14日 下午7:31:53 
 * @version V1.0   
 */
public class Command {
	/** 
	 * @ClassName: Msg 
	 * @Description: TODO
	 * @author Sherlock-lee
	 * @date 2015年7月14日 下午7:31:53 
	 */
	private String value = null;
	public Command() {
		super();
	}

	public Command(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return value;
	}
	
	
}
