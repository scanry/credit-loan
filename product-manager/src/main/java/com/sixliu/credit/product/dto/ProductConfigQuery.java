package com.sixliu.credit.product.dto;

import com.sixliu.credit.common.dto.PageQueryRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
*@author:MG01867
*@date:2018年6月22日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductConfigQuery extends PageQueryRequest<ProductConfigDto>{

	private String productName;
	private String productType;
}
