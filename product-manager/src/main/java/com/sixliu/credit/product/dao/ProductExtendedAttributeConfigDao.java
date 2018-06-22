package com.sixliu.credit.product.dao;

import java.util.List;

import com.sixliu.credit.product.po.ProductExtendedAttributeConfig;

/**
*@author:MG01867
*@date:2018年6月21日
*@E-mail:359852326@qq.com
*@version:
*@describe 产品扩展属性配置数据访问接口
*/
public interface ProductExtendedAttributeConfigDao {

	/**
	 * 通过产品id查询其扩展属性配置集合
	 * @param productId  产品id
	 * @return
	 */
	List<ProductExtendedAttributeConfig> listByProductId(String productId);
}
