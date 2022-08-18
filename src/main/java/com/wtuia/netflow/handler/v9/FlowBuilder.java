package com.wtuia.netflow.handler.v9;

import com.wtuia.netflow.handler.Flow;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 将原生的flow字节转换为flow对象
 */
class FlowBuilder {
	
	private static final Logger logger = LoggerFactory.getLogger(FlowBuilder.class);
	private static final Map<FlowFieldEnum, String> FIELD_METHOD_MAP = initMethod();
	
	private FlowBuilder() {}
	
	private static Map<FlowFieldEnum, String> initMethod() {
		Map<FlowFieldEnum, String> fileMethodMap = new EnumMap<>(FlowFieldEnum.class);
		Class<Flow> flowDataClass = Flow.class;
		Field[] declaredFields = flowDataClass.getDeclaredFields();
		Map<String, Field> clzNameFieldMap = Arrays.stream(declaredFields).collect(Collectors.toMap(v -> v.getName().toUpperCase(), v -> v));
		for (FlowFieldEnum value : FlowFieldEnum.values()) {
			Field field = clzNameFieldMap.get(value.toString().replace("_", ""));
			if (Objects.nonNull(field)) {
				fileMethodMap.put(value, "set" + StringUtils.capitalize(field.getName()));
			}
		}
		return Collections.unmodifiableMap(fileMethodMap);
	}
	
	/**
	 * 将netflow流量组按特定格式组装
	 */
	static List<Flow> builder(List<OriginFlow> originFlowList, String ip) {
		List<Flow> flowList = new ArrayList<>();
		try {
			originFlowList.forEach(originFlow -> {
				Map<FlowFieldEnum, byte[]> fileValueMap = originFlow.splitByFlied();
				flowList.add(createFlow(fileValueMap, ip));
			});
		} catch (Exception e) {
			logger.error("ip:{}", ip, e);
		}
		return flowList;
	}
	
	private static Flow createFlow(Map<FlowFieldEnum, byte[]> filedValueMap, String ip) {
		Class<Flow> flowDataClass = Flow.class;
		try {
			Flow flow = flowDataClass.getConstructor(String.class).newInstance(ip);
			for (Map.Entry<FlowFieldEnum, byte[]> entry : filedValueMap.entrySet()) {
				String method = FIELD_METHOD_MAP.get(entry.getKey());
				if (Objects.isNull(method)) {
					continue;
				}
				flowDataClass.getMethod(method, long.class).invoke(flow, readBytes(entry.getValue()));
			}
			return flow;
		} catch (Exception e) {
			logger.error("", e);
			return new Flow(ip);
		}
	}
	
	
	private static long readBytes(byte[] bytes) {
		ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer(bytes.length);
		buffer.writeBytes(bytes);
		switch (bytes.length) {
			case 1:
				return buffer.readByte();
			case 2:
				return buffer.readShort();
			case 4:
				return buffer.readInt();
			case 8:
				return buffer.readLong();
			default:
				return buffer.readInt();
		}
		// 默认长度
	}
	
	
}
