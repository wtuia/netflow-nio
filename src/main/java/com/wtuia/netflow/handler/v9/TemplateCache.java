package com.wtuia.netflow.handler.v9;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TemplateCache {
	
	private static final class InstanceHolder {
		static final TemplateCache instance = new TemplateCache();
	}
	
	public static TemplateCache getInstance() {
		return InstanceHolder.instance;
	}
	
	private final Map<Integer, List<Template>> templateMap = new ConcurrentHashMap<>();
	
	
	public List<Template> getTemplate(int templateId) {
		return templateMap.get(templateId);
	}
	
	public void addTemplate(Integer templateId, List<Template> templateList) {
		templateMap.put(templateId, templateList);
		
	}
	
	public void addTemplate(byte[] bytes) {
		List<Template> templates = new ArrayList<>();
		int templateId = ((bytes[0] & 0xFF) << 8) | (bytes[1] & 0xFF);
		int count = ((bytes[2] & 0xFF) << 8) | (bytes[3] & 0xFF);
		for (int i = 0; i < count; i++) {
			templates.add(new Template(
					FlowFieldEnum.fromValue(((bytes[4 + (i * 4)] & 0xFF) << 8) | (bytes[5 + (i * 4)] & 0xFF)),
					((bytes[6 + (i * 4)] & 0xFF) << 8) | (bytes[7 + (i * 4)] & 0xFF)
			));
		}
		// 总是用新模板替代旧模板
		templateMap.put(templateId, templates);
	}
}
