package com.wtuia.netflow.handler.v9;

import com.wtuia.netflow.handler.Flow;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class V9NetFlowHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(V9NetFlowHandler.class);
	
	private static final class InstanceHolder {
		static final V9NetFlowHandler instance = new V9NetFlowHandler();
	}
	
	public static V9NetFlowHandler getInstance() {
		return InstanceHolder.instance;
	}
	
	private V9NetFlowHandler() {
	}
	
	
	public List<Flow> getFlow(String ip, ByteBuf buff) {
		List<OriginFlow> originFlowList = unpack(buff);
		return FlowBuilder.builder(originFlowList, ip);
	}
	
	private List<OriginFlow> unpack(ByteBuf buff) {
		int count = buff.readShort();
		long sysUptime = buff.readInt();
		long timestamp = buff.readInt();
		int flowSequence = buff.readInt();
		int sourceId = buff.readInt();
		if (logger.isInfoEnabled()) {
			logger.info("v9 count:{},sysUptime:{},timestamp:{},flowSequence:{},sourceId{}",
					count, sysUptime, timestamp, flowSequence, sourceId);
		}
		List<OriginFlow> originFlowList = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			int id = buff.readShort();
			int length = buff.readShort() - 4;
			byte[] dataBytes = new byte[length];
			buff.readBytes(dataBytes);
			if (id == 0) {
				TemplateCache.getInstance().addTemplate(dataBytes);
			} else {
				originFlowList.add(new OriginFlow(id, dataBytes));
			}
		}
		return originFlowList;
	}
	
	
	
}
