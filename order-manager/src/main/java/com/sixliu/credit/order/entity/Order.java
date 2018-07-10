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
public class Order extends BaseEntity{

	/**客户id**/
	private String customerId;
	
	/**产品id**/
	private String productId;
	
	/**申请贷款金额**/
	private Double applyLoanAmount;
	
	/**申请贷款期限类型**/
	private Integer applyLoanTermType;
	
	/**申请贷款期限**/
	private Integer applyLoanTerm;
	
	/**推荐人id**/
	private String referenceId;
	
	/**审批流程作业id**/
	private String flowJobId;
	
}
