package com.sixliu.flow.component;

import com.sixliu.flow.ApprovalResult;
import com.sixliu.flow.entity.FlowTask;

/**
 * @author:MG01867
 * @date:2018年7月23日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 流程任务提交切面aop
 */
@FunctionalInterface
public interface FlowTaskSubmitAop {

	/** 流程作业任务提交前aop class **/
	String BEFORE_AOP_CLASS = Before.class.getName();

	/** 流程作业任务提交后aop class **/
	String AFTER_AOP_CLASS = After.class.getName();

	void intercept(FlowTask flowTask, ApprovalResult approvalResult);

	public class Before implements FlowTaskSubmitAop {

		@Override
		public void intercept(FlowTask flowTask, ApprovalResult approvalResult) {

		}

	}

	public class After implements FlowTaskSubmitAop {

		@Override
		public void intercept(FlowTask flowTask, ApprovalResult approvalResult) {

		}

	}

}
