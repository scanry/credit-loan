package com.sixliu.credit.product.repository.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:MG01867
 * @date:2018年6月15日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 产品申请流程节点输入配置
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductApplyFlowNodeInputConfig extends BasePo {

	/** 所属产品id:VARCHAR(36) **/
	private String productId;

	/** 输入名称:VARCHAR(20) **/
	private String inputName;
	
	/**
	 * 输入类型:INT(2)
	 * 1.姓名
	 * 2.证件类型
	 * 3.证件编号
	 * 4.出生日期
	 * 5.性别
	 * 6.手机号
	 * 7.学历
	 * 8.婚姻状态
	 * 9.
	 */
	private Integer type;
	private String inputValue;

}