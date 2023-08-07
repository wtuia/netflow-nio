package com.wtuia.netflow.client;

public class NetFlowClient{
	
	public static void onMessage(NetflowCallback callback) {
		new Thread(new UDPClient(callback)).start();
	}
	
	public static void onMessage(int port, NetflowCallback callback) {
		new Thread(new UDPClient(port, callback)).start();
	}
	
}
