package com.sixliu.flow;

import java.util.List;
import java.util.Map;

import com.sixliu.flow.entity.FlowInputData;
import com.sixliu.flow.entity.FlowJobModel;
import com.sixliu.flow.entity.FlowTask;
import com.sixliu.flow.entity.FlowTaskModel;

/**
*@author:MG01867
*@date:2018年7月6日
*@E-mail:359852326@qq.com
*@version:
*@describe 流程管理
*/
public interface FlowManager {

	/**
	 * 增加流程任务模型
	 * @param flowTaskModel
	 */
	void addFlowTaskModel(FlowTaskModel flowTaskModel);
	
	/**
	 * 增加流程作业模型
	 * @param flowTaskModel
	 */
	void addFlowJobModel(FlowJobModel flowJobModel);
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

	
	void additionalInputData(String flowJoblId,String flowTasklId,Map<String,FlowInputData> flowInputDatas);
	/**
	 * 提交订单审核流程任务处理结果
	 * 
	 * @param result
	 *            订单审核流程任务处理结果
	 */
	void submitApprovalResult(ApprovalResult approvalResult);
	
	void cancelFlowJob(String jobId);
}
