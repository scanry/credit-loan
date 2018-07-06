package com.sixliu.flow;
/**
*@author:MG01867
*@date:2018年7月6日
*@E-mail:359852326@qq.com
*@version:
*@describe 流程处理状态
*/
public enum TaskStatus {

	/**通过**/
	PASS,
	/**拒绝**/
	REJECT,
	/**挂起**/
	HANG_UP,
	/**驳回**/
	OVERRULE;
}
