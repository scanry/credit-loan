package com.sixliu.credit.product.api;

import com.sixliu.credit.product.ProductInnerDTO;

/**
 * @author:MG01867
 * @date:2018年7月9日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
public interface ProductManagerInnerClient {

	/**
	 * 通过产品id获取产品
	 * 
	 * @param productId
	 * @return
	 */
	ProductInnerDTO get(String productId);

	/**
	 * 生成产品快照
	 * 
	 * @param productId
	 *            产品id
	 * @return 返回产品快照id
	 */
	String generateProductSnapshot(String productId);
}
