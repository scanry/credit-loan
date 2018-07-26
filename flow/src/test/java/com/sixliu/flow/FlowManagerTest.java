package com.sixliu.flow;

import java.util.List;

import com.sixliu.flow.component.FlowStorage;
import com.sixliu.flow.entity.FlowInputDataModel;
import com.sixliu.flow.entity.FlowJob;
import com.sixliu.flow.entity.FlowJobModel;
import com.sixliu.flow.entity.FlowTask;
import com.sixliu.flow.entity.FlowTaskModel;
import com.sixliu.flow.entity.User;

/**
 * @author:MG01867
 * @date:2018年7月26日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
public class FlowManagerTest {

	public static void main(String[] args) {
		int workerThreads = 4;
		int scheduledThreads = 2;
		FlowManager flowManager = FlowManagerBuilder.newFlowManager(new FlowStorageImpl(), workerThreads,
				scheduledThreads);
		flowManager.addFlowJobModel(null);
	}

	static class FlowStorageImpl implements FlowStorage {

		@Override
		public User getUser(String userId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<User> listUserByRoleId(String roleId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public FlowJobModel getFlowJobModel(String flowJobModelId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public FlowTaskModel getFirstFlowTaskModel(String flowJobModelId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public FlowTaskModel getNextFlowTaskModel(String flowJobModelId, String flowTaskModelId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public FlowTaskModel getFlowTaskModel(String flowJobModelId, int phase) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<FlowTaskModel> listFlowTaskModel(String flowJobModelId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void insertFlowJob(FlowJob flowJob, FlowTask firstFlowTask) {
			// TODO Auto-generated method stub

		}

		@Override
		public FlowJob getFlowJob(String flowJobId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public FlowTask getFlowTask(String flowTaskId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void updateFlowTask(FlowTask flowTask) {
			// TODO Auto-generated method stub

		}

		@Override
		public List<FlowInputDataModel> listFlowInputDataModel(String flowJobModelId, String flowTaskModelId) {
			// TODO Auto-generated method stub
			return null;
		}

	}

}
