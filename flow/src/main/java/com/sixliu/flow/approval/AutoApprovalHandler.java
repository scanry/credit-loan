package com.sixliu.flow.approval;

import com.sixliu.flow.ApprovalResult;
import com.sixliu.flow.entity.FlowTask;

/**
 * @author:MG01867
 * @date:2018年7月18日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 异步handler
 */
public interface AutoApprovalHandler {

	/**
	 * handler名称,运行时会将handler名称作为运行线程名字后缀
	 * 
	 * @return
	 */
	String getName();

	/**
	 * 定时执行检查间隔时间(毫秒)
	 * 
	 * @return
	 */
	long getCheckInterval();

	/**
	 * 检查
	 * 
	 * @return
	 */
	FlowTask check();

	/**
	 * 实际执行
	 * 
	 * @param arg
	 */
	ApprovalResult process(FlowTask flowTask);
}
