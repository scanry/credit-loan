package com.sixliu.credit.product.po;

import java.util.Date;

import com.sixliu.credit.common.po.BasePo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:MG01867
 * @date:2018年6月15日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 贷款产品
 * 
 * 贷款产品配置:
 * 	1.基础配置
 * 	2.申请配置
 * 	3.账户配置
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductConfigLog extends BasePo {

	/**产品编码:VARCHAR(20)**/
	private String code;
	
	/**产品名称:VARCHAR(20)**/
	private String name;
	
	/**产品类型:VARCHAR(36)**/
	private String productTypeId;
	
	/**产品优先级:INT(4)**/
	private Integer priority;
	
	/**是否跟所有产品互斥:INT(1)**/
	private Boolean applyMutexForAll;
	
	/**是否跟同类型产品互斥:INT(1)**/
	private Boolean applyMutexForSimilar;
	
	/**是否支持申请多次:INT(1)**/
	private Boolean applyMultiple;
	
	/**是否循环额度:INT(1)**/
	private Boolean loopQuota;
	
	/**是否支持提高额度:INT(1)**/
	private Boolean increaseQuota;
	
	/**是否支持降低额度:INT(1)**/
	private Boolean decreaseQuota;
	
	/**贷款期限类型:INT(1)**/
	private Integer loanTermType;
	
	/**贷款期限:INT(4)**/
	private Integer loanTerm;
	
	/**生效日期:TIMESTAMP**/
	private Date effectiveDate;
	
	/**过期日期:TIMESTAMP**/
	private Date expireDate;
	
	/**是否有效:INT(1)**/
	private Boolean effective;
	
	/**申请描述:VARCHAR(100)**/
	private String applyDescription;
}
