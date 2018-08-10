package com.sixliu.credit.product.dto;

import java.util.Date;

import com.sixliu.credit.common.dto.PageQueryRequest;
import com.sixliu.credit.product.ProductInnerDTO;

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
public class ProductQueryDTO extends PageQueryRequest<ProductInnerDTO>{

	private String name;
	private String type;
	private Date effectiveDate;
	private Date expireDate;
	private Boolean effective;
}
