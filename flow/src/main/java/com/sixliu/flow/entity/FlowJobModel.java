package com.sixliu.flow.entity;


import java.util.List;

import lombok.Data;

/**
*@author:MG01867
*@date:2018年7月6日
*@E-mail:359852326@qq.com
*@version:
*@describe 流程模型
*/
@Data
public class FlowJobModel {

	/**流程作业模型id**/
	private String id;
	
	/**流程作业模型name**/
	private String name;
	
	/**流程作业任务模型id**/
	private List<String> FlowTaskModelIds;
}
