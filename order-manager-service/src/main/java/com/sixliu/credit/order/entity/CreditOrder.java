package com.sixliu.credit.order.entity;


import com.sixliu.credit.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
*@author:MG01867
*@date:2018年7月7日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
@Data
@EqualsAndHashCode(callSuper = false)
public class CreditOrder extends BaseEntity{

	/**客户id**/
	private String customerId;
	
	/**产品id**/
	private String productId;
	
	/**产品快照id**/
	private String productSnapshotId;
	
	/**审批流程作业id**/
	private String flowJobId;
	
	/**申请授信额度**/
	private Double applyCreditlimit;
	
	/**申请贷款金额**/
	private Double applyLoanAmount;
	
	/**申请贷款期限类型**/
	private Integer applyLoanTermType;
	
	/**申请贷款期限**/
	private Integer applyLoanTerm;
	
	/**推荐人id**/
	private String referenceId;
	
	/**参与活动id**/
	private String activityId;
	
	/**申请渠道**/
	private String channel;
	
}
