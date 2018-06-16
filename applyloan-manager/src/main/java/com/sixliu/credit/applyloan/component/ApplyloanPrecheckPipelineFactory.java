package com.sixliu.credit.applyloan.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.sixliu.credit.applyloan.client.ProductManagerClient;
import com.sixliu.credit.applyloan.client.dto.ProductApplyPrecheckConfig;
import com.sixliu.credit.applyloan.precheck.ApplyloanPrecheckPipeline;
import com.sixliu.credit.common.dto.Response;

/**
 * @author: sixliu
 * @email: 359852326@qq.com
 * @date: 2018年6月16日 下午2:17:46
 * @version V1.0
 * @Description:TODO
 */
@Component
public class ApplyloanPrecheckPipelineFactory {

	@Autowired
	private ConfigurableApplicationContext ConfigurableApplicationContext;
	
	@Autowired
	private ProductManagerClient productManagerClient;

	public ApplyloanPrecheckPipeline newFromConfig(String productId) {
		Response<ProductApplyPrecheckConfig> response = productManagerClient
				.getProductApplyPrecheckConfigById(productId);
		ProductApplyPrecheckConfig applyPrecheckConfig = response.getResult();
		System.out.println(applyPrecheckConfig);
		return null;
	}
}
