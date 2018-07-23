package com.sixliu.flow.service;

import java.util.List;

import com.sixliu.flow.entity.FlowTask;

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
	 * @param flowModelId
	 * @param userId
	 * @param channel
	 * @return
	 */
	String createFlowJob(String flowModelId,String userId,String channel);
	
	/**
	 * 通过流程任务审批用户id从任务池中获取任务
	 * @param userId
	 * @return
	 */
	List<FlowTask> listFlowTaskFromPool(String userId);

	/**
	 * 认领待处理订单审核流程任务
	 * 
	 * @param userId
	 * @param taskId
	 */
	void receiveFlowTask(String userId, String taskId);

	/**
	 * 自动认领待处理订单审核流程任务
	 * 
	 * @param userId
	 * @param taskId
	 */
	void autoReceiveFlowTask(String userId);

	/**
	 * 提交订单审核流程任务处理结果
	 * 
	 * @param result
	 *            订单审核流程任务处理结果
	 */
	void submitApprovalResult(ApprovalResult approvalResult);
	
	void cancelFlowJob(String jobId);
}
