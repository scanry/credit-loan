package com.sixliu.credit.product.dao;

import java.util.List;

import com.sixliu.credit.product.entity.ProductConfig;

/**
*@author:MG01867
*@date:2018年6月15日
*@E-mail:359852326@qq.com
*@version:
*@describe 产品配置数据访问接口
*/
public interface ProductConfigDao {

	/**
	 * 通过id获取数据
	 * @param id  数据id
	 * @return
	 */
	ProductConfig getById(String id);
	
	/**
	 * 通过产品code获取数据
	 * @param code 产品code
	 * @return
	 */
	ProductConfig getByCode(String code);
	
	/**
	 * 通过产品类型获取产品配置集合
	 * @param typeId 产品类型
	 * @return
	 */
	List<ProductConfig> listByType(Integer typeId);
	
	/**
	 * 插入产品配置数据
	 * @param product
	 * @return
	 */
	int insert(ProductConfig productConfig);
	
	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	int delById(String id);
	
	/**
	 * 通过code删除
	 * @param code
	 * @return
	 */
	int delByCode(String code);
}
