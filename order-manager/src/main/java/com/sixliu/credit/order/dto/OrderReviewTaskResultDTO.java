package com.sixliu.credit.order.dto;


import com.sixliu.credit.common.dto.BaseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:MG01867
 * @date:2018年7月7日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderReviewTaskResultDTO extends BaseDTO {
	
	private String taskId;
	private String userId;
}
