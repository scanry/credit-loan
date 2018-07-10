package com.sixliu.credit.order.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sixliu.credit.order.BaseTest;

/**
 * @author:MG01867
 * @date:2018年7月10日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
public class OrderServiceTest extends BaseTest {

	@Autowired
	OrderService orderService;

	@Test
	public void testApply() {
		// int loopCount = 100000;
		//
		// long startTime = System.currentTimeMillis();
		// for (int i = 0; i < loopCount; i++) {
		// OrderApplyFormDTO orderApplyFormDTO = new OrderApplyFormDTO();
		// orderApplyFormDTO.setCustomerId("sixliu");
		// orderService.cancel("1122");
		// }
		// long endTime = System.currentTimeMillis();
		// System.out.println("apply lock time:" + (endTime - startTime));
		// startTime = System.currentTimeMillis();
		// for (int i = 0; i < loopCount; i++) {
		// OrderApplyFormDTO orderApplyFormDTO = new OrderApplyFormDTO();
		// orderApplyFormDTO.setCustomerId("sixliu");
		// orderService.cancel1("1122");
		// }
		// endTime = System.currentTimeMillis();
		// System.out.println("apply1 unlock time:" + (endTime - startTime));
	}
}
