package com.sixliu.flow.service;

import com.sixliu.flow.ReviewResult;

/**
*@author:MG01867
*@date:2018年7月6日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public interface FlowService {

	
	/**
	 * 创建流程作业
	 * @param flowModel
	 * @return
	 */
	String createFlowJob(String flowModel);
	
	/**
	 * 获取待处理订单审核流程任务
	 * 
	 * @param userId
	 */
	void listPendingReviewTask(String userId);

	/**
	 * 认领待处理订单审核流程任务
	 * 
	 * @param userId
	 * @param taskId
	 */
	void claimReviewTask(String userId, String taskId);

	/**
	 * 自动认领待处理订单审核流程任务
	 * 
	 * @param userId
	 * @param taskId
	 */
	void automaticClaimReviewTask(String userId);

	/**
	 * 提交订单审核流程任务处理结果
	 * 
	 * @param result
	 *            订单审核流程任务处理结果
	 */
	void submitReviewTaskResult(ReviewResult result);
}
