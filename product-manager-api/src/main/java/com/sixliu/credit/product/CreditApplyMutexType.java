package com.sixliu.credit.product;

/**
 * @author:MG01867
 * @date:2018年8月6日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 授信申请互斥
 */
public enum CreditApplyMutexType {

	/** 无互斥 **/
	NONE(0),
	
	/** 跟自己互斥 **/
	FOR_SELF(1),
	
	/** 跟同类型产品互斥 **/
	FOR_TYPE(2),

	/** 跟所有产品互斥 **/
	FOR_ALL(3);

	private final int value;

	private CreditApplyMutexType(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}

	public static CreditApplyMutexType valueOf(int value) {
		if (0 == value) {
			return NONE;
		} else if (1 == value) {
			return FOR_SELF;
		} else if (2 == value) {
			return FOR_TYPE;
		}else if (3 == value) {
			return FOR_ALL;
		} else {
			throw new IllegalArgumentException();
		}
	}
}
