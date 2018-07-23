package com.sixliu.flow.entity;


import lombok.Data;

/**
*@author:MG01867
*@date:2018年7月6日
*@E-mail:359852326@qq.com
*@version:
*@describe 流程作业任务模型
*/
@Data
public class FlowTaskModel {

	/**流程作业任务id**/
	private String id;
	
	/**流程作业任务所处阶段**/
	private int phase;
	
	/**流程作业任务所属角色**/
	private String ownerRoleId;
	
	/**流程作业任务处理类型**/
	private TaskType type;
	
	/**流程作业任务自动处理class**/
	private String autoHandlerClass;
	
}
