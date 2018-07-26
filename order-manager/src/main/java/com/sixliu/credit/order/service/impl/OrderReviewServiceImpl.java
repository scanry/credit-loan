package com.sixliu.credit.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sixliu.credit.order.service.OrderReviewService;
import com.sixliu.flow.ApprovalResult;
import com.sixliu.flow.entity.FlowInputData;
import com.sixliu.flow.entity.FlowTask;

/**
*@author:MG01867
*@date:2018年7月10日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
@Service
public class OrderReviewServiceImpl implements OrderReviewService{

	@Override
	public String createFlowJob(String flowModelId, String userId, String channel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FlowTask> listFlowTaskFromPool(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void receiveFlowTask(String userId, String taskId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void autoReceiveFlowTask(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void additionalInputData(String flowJoblId, String flowTasklId, Map<String, FlowInputData> flowInputDatas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submitApprovalResult(ApprovalResult approvalResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelFlowJob(String jobId) {
		// TODO Auto-generated method stub
		
	}
	
}
