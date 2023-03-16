package com.wtuia.netflow.client;

public class NetFlowClient{
	
	public static void onMessage(NetflowCallback callback) {
		new Thread(new UDPClient(callback)).start();
	}
	
}
