package com.sixliu.credit.product.repository.po;

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
public class Product extends BasePo {

	/**产品名称:VARCHAR(20)**/
	private String name;
	
	/**产品类型:VARCHAR(36)**/
	private String productTypeId;
	
	/**产品优先级:INT(4)**/
	private Integer priority;
	
	/**是否跟所有产品互斥:INT(1)**/
	private Integer applyMutexForAll;
	
	/**是否跟同类型产品互斥:INT(1)**/
	private Integer applyMutexForType;
	
	/**产品有效期时间:TIMESTAMP**/
	private Date effectiveDate;
	
	/**产品过期时间:TIMESTAMP**/
	private Date expireDate;
}
