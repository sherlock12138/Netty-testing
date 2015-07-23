package com.gdut.Netty_testing.dongjun.client;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import org.junit.Test;

public class CommUtil implements SerialPortEventListener {
	InputStream inputStream; // 从串口来的输入流
	OutputStream outputStream;// 向串口输出的流
	SerialPort serialPort; // 串口的引用
	CommPortIdentifier portId;

	public CommUtil(Enumeration portList, String name) {
		while (portList.hasMoreElements()) {
			CommPortIdentifier temp = (CommPortIdentifier) portList
					.nextElement();
			if (temp.getPortType() == CommPortIdentifier.PORT_SERIAL) {// 判断如果端口类型是串口
				if (temp.getName().equals(name)) { // 判断如果端口已经启动就连接
					portId = temp;
				}
			}
		}
		try {
			serialPort = (SerialPort) portId.open("My" + name, 2000);
		} catch (PortInUseException e) {

		}
		try {
			inputStream = serialPort.getInputStream();
			outputStream = serialPort.getOutputStream();
		} catch (IOException e) {
		}
		try {
			serialPort.addEventListener(this); // 给当前串口天加一个监听器
		} catch (TooManyListenersException e) {
		}

		serialPort.notifyOnDataAvailable(true); // 当有数据时通知
		try {
			serialPort.setSerialPortParams(2400, SerialPort.DATABITS_8, // 设置串口读写参数
					SerialPort.STOPBITS_1, SerialPort.PARITY_EVEN);
		} catch (UnsupportedCommOperationException e) {
		}
	}

	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:// 当有可用数据时读取数据,并且给串口返回数据
			byte[] readBuffer = new byte[20];
			try {
				while (inputStream.available() > 0) {
					System.out.println(inputStream.available());
					int numBytes = inputStream.read(readBuffer);
					System.out.println(numBytes);
				}
				System.out.println(new String(readBuffer).trim());
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	public void send(String content) {
		try {
			outputStream.write(content.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ClosePort() {
		if (serialPort != null) {
			serialPort.close();
		}
	}

	@Test
	public void main() throws InterruptedException {
		Enumeration portList = CommPortIdentifier.getPortIdentifiers(); // 得到当前连接上的端口
		CommUtil comm3 = new CommUtil(portList, "COM4");
		int i = 0;
		while (i < 5) {
			Thread.sleep(3000);
			comm3.send("68 AA AA AA AA AA AA 68 13 00 DF 16");
			i++;
		}
		comm3.ClosePort();
	}

}
