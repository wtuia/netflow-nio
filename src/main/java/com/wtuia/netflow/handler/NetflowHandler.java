package com.wtuia.netflow.handler;

import com.wtuia.netflow.handler.v5.V5NetFlowHandler;
import com.wtuia.netflow.handler.v9.V9NetFlowHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.List;

public class NetflowHandler extends SimpleChannelInboundHandler<DatagramPacket> {
	
	private static final Logger logger = LoggerFactory.getLogger(NetflowHandler.class);
	
	private final V9NetFlowHandler v9NetFlowHandler = V9NetFlowHandler.getInstance();
	
	private final V5NetFlowHandler v5NetFlowHandler = V5NetFlowHandler.getInstance();
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) {
		InetSocketAddress remoteAddress = msg.sender();
		String ip = remoteAddress.getHostString();
		final ByteBuf buff = msg.copy().content();
		List<Flow> flowList = getFlow(ip, buff);
		flowList.forEach(v -> logger.info("ip:{}, flow:{}", ip, v));
		buff.release();
	}
	
	private List<Flow> getFlow(String ip, ByteBuf buff) {
		short version = buff.readShort();
		if (version == 5) {
			return v5NetFlowHandler.getFlow(ip, buff);
		}
		if (version == 9) {
			return v9NetFlowHandler.getFlow(ip, buff);
		}
		return Collections.emptyList();
	}
	
}
