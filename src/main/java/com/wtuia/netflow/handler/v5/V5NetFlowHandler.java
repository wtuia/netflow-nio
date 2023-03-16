package com.wtuia.netflow.handler.v5;

import com.wtuia.netflow.handler.Flow;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class V5NetFlowHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(V5NetFlowHandler.class);
	
	private static final class InstanceHolder {
	    static final V5NetFlowHandler instance = new V5NetFlowHandler();
	}
	
	public static V5NetFlowHandler getInstance() {
	    return InstanceHolder.instance;
	}
	
	private static final int V5_HEADER_SIZE = 24;
	private static final int V5_FLOW_SIZE = 48;
	
	private V5NetFlowHandler() {
	}
	
	/**
	 * 解析地址时,不使用{@code byteBuf.getInt}直接获取值,32ip地址直接读取会改变符号位,导致数据变负数,
	 * 直接读取出byte数组,另外处理
	 */
	public List<Flow> getFlow(String ip, ByteBuf byteBuf) {
		short count = byteBuf.readShort();
		int sysUptime = byteBuf.readInt();
		int unixSecs = byteBuf.readInt();
		int unixNextSecs = byteBuf.readInt();
		if (logger.isDebugEnabled()) {
			logger.debug("v5 count:{},sysUptime:{},unixSecs:{},unixNextSecs:{}",
					count, sysUptime, unixSecs, unixNextSecs);
		}
		List<Flow> flows = new ArrayList<>();
		for (int i = 0, p = V5_HEADER_SIZE; i < count; i++, p += V5_FLOW_SIZE) {
			byte[] srcByte = new byte[4];
			byte[] desByte = new byte[4];
			byteBuf.getBytes(p, srcByte);
			byteBuf.getBytes(p + 4, desByte);
			Flow f = new Flow(ip)
					.setIpv4SrcAddr(toNumber(srcByte))
					.setIpv4DstAddr(toNumber(desByte))
					.setL4SrcPort(byteBuf.getShort(p + 12))
					.setL4DstPort(byteBuf.getShort(p + 14))
					.setInBytes(byteBuf.getInt(p + 16))
					.setInPaks(byteBuf.getInt(p + 20));
			if (f.getIpv4SrcAddr() > 0 && f.getIpv4DstAddr() > 0) {
				flows.add(f);
			}else {
				logger.error("ip转换错误, :{}, {}", f.getIpv4SrcAddr(), f.getIpv4DstAddr());
			}
		}
		return flows;
	}
	
	private long toNumber(byte[] p) {
		long ret = 0;
		for (int i = 0; i < 4; i++) {
			ret = ((ret << 8) & 0XFFFFFFFF) + (p[i] & 0XFF);
		}
		return ret;
	}
}
