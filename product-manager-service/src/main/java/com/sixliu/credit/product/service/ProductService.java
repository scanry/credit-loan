package com.sixliu.credit.product.service;

import java.util.List;

import com.sixliu.credit.product.dto.ProductDTO;
import com.sixliu.credit.product.dto.ProductQueryDTO;

/**
*@author:MG01867
*@date:2018年7月5日
*@E-mail:359852326@qq.com
*@version:
*@describe 产品服务
*/
public interface ProductService {

	List<ProductDTO> query(ProductQueryDTO productConfigQuery);
}
