package com.sixliu.flow.entity;


import com.sixliu.flow.InputType;

import lombok.Data;

/**
*@author:MG01867
*@date:2018年7月23日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
@Data
public class FlowInputDataModel {

	/**数据id**/
	private String id;
	
	/**流程作业模型id**/
	private String flowJobModelId;
	
	/**流程作业任务模型id**/
	private String flowTaskModelId;
	
	/**流程作业任务输入数据组**/
	private String inputGroup;
	
	/**流程作业任务输入数据名称**/
	private String inputName;
	
	/**流程作业任务输入数据类型**/
	private InputType inputType;
}
