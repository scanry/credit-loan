package com.sixliu.credit.core.component;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.sixliu.flow.FlowManager;
import com.sixliu.flow.FlowManagerBuilder;
import com.sixliu.flow.component.FlowStorage;
/**
*@author:MG01867
*@date:2018年8月6日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
@Component
public class FlowManagerWrapper{

	FlowStorage flowStorage=null;
	int workerThreads=4;
	int scheduledThreads=4;
	
	@Bean(destroyMethod = "shutdown")
	public FlowManager newFlowManager() {
		FlowStorage flowStorage=null;
		int workerThreads=4;
		int scheduledThreads=4;
		FlowManager flowManager=FlowManagerBuilder.newFlowManager(flowStorage, workerThreads, scheduledThreads);
		return flowManager;
	}
}
