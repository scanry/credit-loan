package com.sixliu.credit.product.entity;

import java.util.Date;

import com.sixliu.credit.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:MG01867
 * @date:2018年6月15日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 贷款产品配置
 * 
 * 贷款产品配置:
 * 	1.基础配置
 * 	2.申请配置
 * 	3.账户配置
 * 
 *  已经贷款申请拒绝后策略:同类产品永久拒绝，同类产品拒绝后n天(黑名单管理)
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductConfig extends BaseEntity {

	/**产品编码:VARCHAR(20)**/
	private String code;
	
	/**产品名称:VARCHAR(20)**/
	private String name;
	
	/**产品类型:VARCHAR(36)**/
	private String typeId;
	
	/**产品优先级:INT(4)**/
	private Integer priority;
	
	/**是否跟所有产品互斥:INT(1)**/
	private Integer applyMutexForAll;
	
	/**是否跟同类型产品互斥:INT(1)**/
	private Integer applyMutexForSimilar;
	
	/**是否支持申请多次:INT(1)**/
	private Integer applyMultiple;
	
	/**是否循环额度:INT(1)**/
	private Integer loopQuota;
	
	/**是否支持提高额度:INT(1)**/
	private Integer increaseQuota;
	
	/**是否支持降低额度:INT(1)**/
	private Integer decreaseQuota;
	
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
