package com.wtuia.netflow.client;

import com.wtuia.netflow.handler.NetflowHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class UDPClient implements Runnable{
	
	private final int port;
	
	private static final Logger logger = LoggerFactory.getLogger(UDPClient.class);
	
	private final NetflowCallback callback;
	
	public UDPClient(NetflowCallback callback) {
		this.port = 2055;
		this.callback = callback;
	}
	
	public UDPClient(int port, NetflowCallback callback) {
		this.port = port;
		this.callback = callback;
	}
	
	@Override
	public void run() {
		runClient();
	}
	
	private void runClient() {
		logger.info("netflow client start...");
		//创建reactor 线程组
		EventLoopGroup workerLoopGroup = new NioEventLoopGroup();
		try {
			// 设置reactor 线程组
			Bootstrap b = new Bootstrap()
					.group(workerLoopGroup)
					.channel(NioDatagramChannel.class)
					// 是否开启广播能力
					.option(ChannelOption.SO_BROADCAST, true)
					.handler(new NetflowHandler(callback));
			b.bind(port).sync().channel().closeFuture().await();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			logger.error("", e);
		}finally {
			workerLoopGroup.shutdownGracefully();
			logger.info("workerLoopGroup shutdown");
		}
	}
}
