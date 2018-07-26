package com.sixliu.flow;

import com.sixliu.flow.approval.AutoApprovalHandlerManager;
import com.sixliu.flow.component.FlowStorage;
import com.sixliu.flow.component.FlowTaskSubmitAopManager;
import com.sixliu.flow.component.IdGenerator;
import com.sixliu.flow.core.FlowManagerImpl;

/**
 * @author:MG01867
 * @date:2018年7月26日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
public class FlowManagerBuilder {

	public static FlowManager newFlowManager(FlowStorage flowStorage, int workerThreads, int scheduledThreads) {
		FlowManager flowManager = new FlowManagerImpl(flowStorage, new IdGeneratorImpl(), new FlowTaskSubmitAopManager(),
				new AutoApprovalHandlerManager(workerThreads, scheduledThreads));
		return flowManager;
	}
	
	static class IdGeneratorImpl implements IdGenerator{

		@Override
		public String generateFlowJobId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String generateFlowTaskId() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
