package com.sixliu.flow.entity;
/**
*@author:MG01867
*@date:2018年7月6日
*@E-mail:359852326@qq.com
*@version:
*@describe 流程作业状态
*/
public enum JobStatus {

	/**开始**/
	STARTED,
	/**通过结束**/
	PASS_ENDED,
	/**拒绝结束**/
	REJECT_ENDED,
	/**取消结束**/
	CANCEL_ENDED,
}
