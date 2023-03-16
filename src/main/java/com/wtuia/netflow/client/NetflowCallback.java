package com.wtuia.netflow.client;

import com.wtuia.netflow.handler.Flow;

import java.util.List;

public interface NetflowCallback {
	void accept(List<Flow> flowList);
	
}
