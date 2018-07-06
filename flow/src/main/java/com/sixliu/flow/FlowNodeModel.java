package com.sixliu.flow;

import lombok.Data;

/**
*@author:MG01867
*@date:2018年7月6日
*@E-mail:359852326@qq.com
*@version:
*@describe 流程节点模型
*/
@Data
public class FlowNodeModel {

	private String id;
	private String name;
	
	/**处理类型**/
	private FlowTaskHandler.Type handlerType;
	
	/**处理者名称**/
	private String handlerName;
}
