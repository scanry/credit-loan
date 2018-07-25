package com.sixliu.flow.service.impl;

import java.util.Date;

import com.sixliu.flow.JobStatus;
import com.sixliu.flow.TaskStatus;
import com.sixliu.flow.entity.FlowJob;
import com.sixliu.flow.entity.FlowJobModel;
import com.sixliu.flow.entity.FlowTask;
import com.sixliu.flow.entity.FlowTaskModel;

/**
 * @author:MG01867
 * @date:2018年7月23日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
public class FlowUtils{
	
	public static FlowJob newFlowJob(FlowJobModel flowJobModel, String flowJobId) {
		FlowJob flowJob = new FlowJob();
		flowJob.setId(flowJobId);
		flowJob.setStatus(JobStatus.STARTED);
		Date nowDate = new Date();
		flowJob.setStartDate(nowDate);
		flowJob.setEndDate(nowDate);
		return flowJob;
	}

	public static FlowTask newFlowTask(FlowTaskModel flowTaskModel, String flowJobId,String flowTaskId,String channel,
			String userId) {
		FlowTask flowTask = new FlowTask();
		flowTask.setId(flowTaskId);
		flowTask.setFlowTaskModelId(flowTaskModel.getId());
		flowTask.setFlowJobId(flowJobId);
		flowTask.setPhase(flowTaskModel.getPhase());
		flowTask.setStatus(TaskStatus.POOLING);
		flowTask.setType(flowTaskModel.getType());
		flowTask.setOwnerRoleId(flowTaskModel.getOwnerRoleId());
		flowTask.setAutoHandlerClass(flowTaskModel.getAutoHandlerClass());
		Date nowDate = new Date();
		flowTask.setStartDate(nowDate);
		flowTask.setEndDate(nowDate);
		flowTask.setChannel(channel);
		flowTask.setCreateUserId(userId);
		return flowTask;
	}
}
