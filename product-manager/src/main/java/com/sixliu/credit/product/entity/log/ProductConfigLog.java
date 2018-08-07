package com.sixliu.credit.product.entity.log;


import com.sixliu.credit.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:MG01867
 * @date:2018年6月15日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 产品变更日志
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductConfigLog extends BaseEntity {

	/**原始产品编码**/
	private String originalId;
	
	/**原始产品版本**/
	private Integer originalVersion;
}
