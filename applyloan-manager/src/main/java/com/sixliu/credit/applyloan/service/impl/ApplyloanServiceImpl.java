package com.sixliu.credit.applyloan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sixliu.credit.applyloan.component.ApplyloanPrecheckPipelineFactory;
import com.sixliu.credit.applyloan.precheck.ApplyloanPrecheckPipeline;
import com.sixliu.credit.applyloan.service.ApplyloanService;
import com.sixliu.credit.applyloan.service.dto.OrderApplyFormRequest;
import com.sixliu.credit.applyloan.service.dto.OrderApplyFormResponse;

/**
 * @author: sixliu
 * @email: 359852326@qq.com
 * @date: 2018年6月16日 下午2:13:09
 * @version V1.0
 * @Description:TODO
 */
@Component
public class ApplyloanServiceImpl implements ApplyloanService {

	@Autowired
	private ApplyloanPrecheckPipelineFactory applyloanPrecheckPipelineFactory;

	@Override
	public OrderApplyFormResponse applyloan(OrderApplyFormRequest form) {
		ApplyloanPrecheckPipeline applyloanPrecheckPipeline = applyloanPrecheckPipelineFactory
				.newFromConfig(form.getProductId());
		applyloanPrecheckPipeline.process(form);
		return null;
	}

}
