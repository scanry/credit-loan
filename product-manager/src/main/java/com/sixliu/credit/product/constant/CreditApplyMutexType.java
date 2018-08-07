package com.sixliu.credit.product.constant;

/**
 * @author:MG01867
 * @date:2018年8月6日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 授信申请互斥
 */
public enum CreditApplyMutexType {

	/** 跟所有产品互斥 **/
	FOR_ALL(0),

	/** 跟同类型产品互斥 **/
	FOR_TYPE(1),

	/** 跟自己互斥 **/
	FOR_SELF(2);

	private final int value;

	private CreditApplyMutexType(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}

	public static CreditApplyMutexType valueOf(int value) {
		if (0 == value) {
			return FOR_ALL;
		} else if (1 == value) {
			return FOR_TYPE;
		} else if (2 == value) {
			return FOR_SELF;
		} else {
			throw new IllegalArgumentException();
		}
	}
}
