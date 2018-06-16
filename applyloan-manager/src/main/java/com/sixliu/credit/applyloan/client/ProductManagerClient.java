package com.sixliu.credit.applyloan.client;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sixliu.credit.applyloan.client.dto.AppliedProduct;
import com.sixliu.credit.applyloan.client.dto.ProductApplyPrecheckConfig;
import com.sixliu.credit.common.dto.Response;

/**    
 * @author: sixliu
 * @email:  359852326@qq.com
 * @date:   2018年6月16日 下午2:21:48   
 * @version V1.0 
 * @Description:TODO
 */
public interface ProductManagerClient {

	/**
	 * 根据产品id获取产品数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/productManager/getById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Response<AppliedProduct> getById(String id);
	
	@RequestMapping(value = "/productManager/getProductApplyPrecheckConfigById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Response<ProductApplyPrecheckConfig> getProductApplyPrecheckConfigById(String id);
}
