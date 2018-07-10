package com.sixliu.credit.order.service.impl;

import org.springframework.stereotype.Service;

import com.sixliu.credit.order.service.OrderReviewService;
import com.sixliu.flow.ReviewResult;

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
	public String createFlowJob(String flowModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void listPendingReviewTask(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void claimReviewTask(String userId, String taskId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void automaticClaimReviewTask(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submitReviewTaskResult(ReviewResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelFlowJob(String jobId) {
		// TODO Auto-generated method stub
		
	}

}
