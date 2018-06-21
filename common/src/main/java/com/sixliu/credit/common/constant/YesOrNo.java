package com.sixliu.credit.common.constant;

/**
 * @author:MG01867
 * @date:2018年6月21日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
public class YesOrNo {

	public static final int YES = 1;
	public static final int NO = 0;

	public static int check(int value) {
		if (value == YES || value == NO) {
			throw new IllegalArgumentException(String.format("the YesOrNo[%s] is illegal", value));
		}
		return value;
	}

	public static boolean isYes(int value) {
		return YES == check(value);
	}

	public static boolean isNo(int value) {
		return NO == check(value);
	}
}
