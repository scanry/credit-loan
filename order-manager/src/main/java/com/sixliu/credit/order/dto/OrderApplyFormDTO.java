package com.sixliu.credit.order.dto;

import java.util.Map;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;


import com.sixliu.credit.common.dto.BaseWriteDTO;
import com.sixliu.credit.common.validation.LoanAmountValidation;
import com.sixliu.credit.common.validation.LoanTermTypeValidation;

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
public class OrderApplyFormDTO extends BaseWriteDTO{

	/**客户id**/
	@NotBlank(message = "the customerId must not be blank")
	private String customerId;
	
	/**产品id**/
	@NotBlank(message = "the productId must not be blank")
	private String productId;
	
	/**申请贷款金额**/
	@LoanAmountValidation(message="the applyLoanAmount is illegal")
	private Double applyLoanAmount;
	
	/**申请贷款期限类型**/
	@LoanTermTypeValidation(message="the applyLoanTermType is illegal")
	private Integer applyLoanTermType;
	
	/**申请贷款期限**/
	@DecimalMin(value="0")
	private Integer applyLoanTerm;
	
	/**推荐人id**/
	private String referenceId;
	
	/**参与活动id**/
	private String activityId;
	
	/**扩展表单**/
	private Map<String,Object> extendForm;
}
