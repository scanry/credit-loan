package com.sixliu.credit.applyloan.precheck;

import com.sixliu.credit.applyloan.service.dto.OrderApplyFormRequest;

/**    
 * @author: sixliu
 * @email:  359852326@qq.com
 * @date:   2018年6月16日 下午2:11:52   
 * @version V1.0 
 * @Description:TODO
 */
public interface ApplyloanPrecheck {

	void precheck(OrderApplyFormRequest request,ApplyloanPrecheckPipeline pipeline);
}
