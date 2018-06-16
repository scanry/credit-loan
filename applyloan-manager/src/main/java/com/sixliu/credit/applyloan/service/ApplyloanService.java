package com.sixliu.credit.applyloan.service;

import com.sixliu.credit.applyloan.service.dto.OrderApplyFormRequest;
import com.sixliu.credit.applyloan.service.dto.OrderApplyFormResponse;

/**
 * @author: sixliu
 * @email: 359852326@qq.com
 * @date: 2018年6月16日 下午2:06:49
 * @version V1.0
 * @Description:申请贷款服务
 */

public interface ApplyloanService {

	OrderApplyFormResponse applyloan(OrderApplyFormRequest form);
}
