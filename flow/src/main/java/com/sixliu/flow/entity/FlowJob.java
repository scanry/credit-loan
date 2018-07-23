package com.sixliu.flow.entity;


import java.util.Date;

import lombok.Data;

/**
*@author:MG01867
*@date:2018年7月6日
*@E-mail:359852326@qq.com
*@version:
*@describe 流程作业
*/
@Data
public class FlowJob {

	/**流程作业id**/
	private String id;
	
	/**流程作业名称**/
	private String name;
	
	/**流程作业名称**/
	private String flowJobModelId;
	
	/**流程作业状态**/
	private JobStatus status;
	
	/**流程作业创建用户**/
	private String createUserId;
	
	/**流程作业开始时间**/
	private Date startDate;
	
	/**流程作业结束时间**/
	private Date endDate;
	
}
