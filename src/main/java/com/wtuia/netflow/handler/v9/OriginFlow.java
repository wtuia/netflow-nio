package com.wtuia.netflow.handler.v9;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * 原生的flow字节组
 */
class OriginFlow {

    private final int templateId;
	private final byte[] data;
	
	public OriginFlow(int templateId, byte[] data) {
		this.templateId = templateId;
		this.data = data;
	}
	
	public Map<FlowFieldEnum, byte[]> splitByFlied() {
        Map<FlowFieldEnum, byte[]> values = new EnumMap<>(FlowFieldEnum.class);
        int offset = 0;
		List<Template> templateList = TemplateCache.getInstance().getTemplate(templateId);
        for(Template template : templateList) {
            byte[] value = new byte[template.getLength()];
            System.arraycopy(data, offset, value, 0, value.length);
            values.put(template.getType(), value);
            offset += value.length;
        }
        return values;
    }
	
}
