package com.sixliu.credit.product.entity;

import com.sixliu.credit.common.entity.BaseEntity;
import com.sixliu.credit.product.constant.QuotaUseageType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:MG01867
 * @date:2018年7月30日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 额度配置
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QuotaConfig extends BaseEntity {
	
	/**名称**/
	private String name;
	
	/**产品id**/
	private String productId;

	/**用途**/
	private QuotaUseageType type;
	
	/**占用授信额度比例**/
	private Double ratio;
	
	/**风险级**/
	private Integer riskLevel;
}
