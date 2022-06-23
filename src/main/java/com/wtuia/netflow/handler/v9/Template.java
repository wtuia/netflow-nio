package com.wtuia.netflow.handler.v9;

class Template {
	private final FlowFieldEnum type;
	private final int length;
	
	public Template(FlowFieldEnum type, int length) {
		this.type = type;
		this.length = length;
	}
	
	public FlowFieldEnum getType() {
		return type;
	}
	
	public int getLength() {
		return length;
	}
}
