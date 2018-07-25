package com.sixliu.flow.component;

import java.util.List;

import com.sixliu.flow.entity.FlowInputDataModel;
import com.sixliu.flow.entity.FlowJob;
import com.sixliu.flow.entity.FlowJobModel;
import com.sixliu.flow.entity.FlowTask;
import com.sixliu.flow.entity.FlowTaskModel;
import com.sixliu.flow.entity.User;

/**
 * @author:MG01867
 * @date:2018年7月23日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 流程存储器
 */
public interface FlowStorage {

	/**
	 * 通过用户id获取用户
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	User getUser(String userId);

	/**
	 * 通过角色id获取该角色的用户集合
	 * 
	 * @param roleId
	 *            角色id
	 * @return
	 */
	List<User> listUserByRoleId(String roleId);

	/**
	 * 通过流程作业模型id获取流程作业模型
	 * 
	 * @param flowJobModelId
	 *            流程作业模型id
	 * @return
	 */
	FlowJobModel getFlowJobModel(String flowJobModelId);

	/**
	 * 通过流程作业模型id获取第一个流程作业任务模型
	 * 
	 * @param flowModelId
	 *            流程作业模型id
	 * @return
	 */
	FlowTaskModel getFirstFlowTaskModel(String flowJobModelId);

	/**
	 * 通过流程作业模型id和流程作业任务模型id 获取 下一个 流程作业任务模型
	 * 
	 * @param flowModelId
	 *            流程作业模型id
	 * @param flowTaskModelId
	 *            流程作业任务模型id
	 * @return
	 */
	FlowTaskModel getNextFlowTaskModel(String flowJobModelId, String flowTaskModelId);
	
	
	/**
	 * 通过流程作业模型id和流程作业任务模型id 获取 下一个 流程作业任务模型
	 * 
	 * @param flowModelId
	 *            流程作业模型id
	 * @param flowTaskModelId
	 *            流程作业任务模型id
	 * @return
	 */
	FlowTaskModel getFlowTaskModel(String flowJobModelId, int phase);

	/**
	 * 通过流程作业模型id获取流程作业任务模型集合
	 * 
	 * @param flowJobModelId
	 *            流程作业模型id
	 * @return
	 */
	List<FlowTaskModel> listFlowTaskModel(String flowJobModelId);

	/**
	 * 保存流程作业和流程作业第一个任务
	 * @param flowJob
	 * @param flowTask
	 */
	void insertFlowJob(FlowJob flowJob, FlowTask firstFlowTask);
	
	FlowJob getFlowJob(String flowJobId);
	
	FlowTask getFlowTask(String flowTaskId);
	
	void updateFlowTask(FlowTask flowTask);
	
	List<FlowInputDataModel> listFlowInputDataModel(String flowJobModelId,String flowTaskModelId);
}
