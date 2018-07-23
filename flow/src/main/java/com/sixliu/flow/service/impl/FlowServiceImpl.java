package com.sixliu.flow.service.impl;

import java.util.List;

import com.sixliu.flow.component.FlowStorage;
import com.sixliu.flow.component.IdGenerator;
import com.sixliu.flow.entity.FlowJob;
import com.sixliu.flow.entity.FlowJobModel;
import com.sixliu.flow.entity.FlowTask;
import com.sixliu.flow.entity.FlowTaskModel;
import com.sixliu.flow.entity.TaskStatus;
import com.sixliu.flow.entity.TaskStatus.FlowTaskModelGetter;
import com.sixliu.flow.entity.TaskType;
import com.sixliu.flow.entity.User;
import com.sixliu.flow.service.ApprovalResult;
import com.sixliu.flow.service.FlowService;

/**
 * @author:MG01867
 * @date:2018年7月23日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
public class FlowServiceImpl implements FlowService {

	private FlowStorage flowStorage;
	private IdGenerator idGenerator;

	@Override
	public String createFlowJob(String flowModelId, String userId, String channel) {
		FlowJobModel flowJobModel = flowStorage.getFlowJobModel(flowModelId);
		if (null == flowJobModel) {
			throw new IllegalArgumentException(String.format("This flowModel[%s] is non-existent", flowModelId));
		}
		User user = flowStorage.getUser(userId);
		if (null == user) {
			throw new IllegalArgumentException(String.format("This user[%s] is non-existent", userId));
		}
		String flowJobId = idGenerator.generateFlowJobId();
		FlowJob flowJob = FlowUtils.newFlowJob(flowJobModel, flowJobId);
		FlowTaskModel flowTaskModel = flowStorage.getFirstFlowTaskModel(flowModelId);
		if (null == flowTaskModel) {
			throw new IllegalArgumentException(
					String.format("This flowTaskModel[%s] configure empty flowTaskModel", flowModelId));
		}
		FlowTask flowTask = FlowUtils.newFlowTask(flowTaskModel, flowJobId, idGenerator.generateFlowTaskId(), channel,
				userId);
		flowStorage.insertFlowJob(flowJob, flowTask);
		return flowJobId;
	}

	@Override
	public List<FlowTask> listFlowTaskFromPool(String userId) {
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
	public void submitApprovalResult(ApprovalResult approvalResult) {
		FlowTask flowTask = getAndCheckFlowTask(approvalResult.getFlowTaskId());
		FlowJob flowJob = getAndCheckFlowJob(flowTask.getFlowJobId());
		TaskStatus taskStatus = flowTask.getStatus();
		FlowTask nextFlowTask = taskStatus.process(flowJob, flowTask, approvalResult, new FlowTaskModelGetter() {

			@Override
			public String generateFlowTaskId() {
				return idGenerator.generateFlowTaskId();
			}

			@Override
			public FlowTaskModel getNext(String flowJobModelId, String flowTaskModelId) {
				FlowTaskModel nextFlowTaskModel = flowStorage.getNextFlowTaskModel(flowJobModelId, flowTaskModelId);
				return nextFlowTaskModel;
			}

			@Override
			public FlowTaskModel get(String flowJobModelId, int phase) {
				FlowTaskModel nextFlowTaskModel = flowStorage.getFlowTaskModel(flowJobModelId, phase);
				return nextFlowTaskModel;
			}
		});
		flowStorage.updateFlowTask(flowTask);
		if (null != nextFlowTask) {
			flowStorage.insertFlowJob(flowJob, nextFlowTask);
			if (TaskType.AUTO == nextFlowTask.getType()) {
				notifyAutoHandler(nextFlowTask.getId());
			}
		}
	}

	private void notifyAutoHandler(String flowTaskId) {
		System.out.println();
	}

	@Override
	public void cancelFlowJob(String jobId) {

	}

	private FlowJob getAndCheckFlowJob(String flowJobId) {
		FlowJob flowJob = flowStorage.getFlowJob(flowJobId);
		if (null == flowJob) {
			throw new IllegalArgumentException(String.format("This flowJob[%s] is non-existent", flowJobId));
		}
		return flowJob;
	}

	private FlowTask getAndCheckFlowTask(String flowTaskId) {
		FlowTask flowTask = flowStorage.getFlowTask(flowTaskId);
		if (null == flowTask) {
			throw new IllegalArgumentException(String.format("This flowTask[%s] is non-existent", flowTaskId));
		}
		return flowTask;
	}

}
