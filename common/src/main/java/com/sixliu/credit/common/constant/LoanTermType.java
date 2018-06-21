package com.sixliu.credit.common.constant;

/**
 * @author:MG01867
 * @date:2018年6月21日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 贷款期限类型
 */
public class LoanTermType {

	/** 天 **/
	public static final int DAY = 1;
	/** 月 **/
	public static final int MONTH = 2;
	/** 季度 **/
	public static final int QUARTER = 3;
	/** 年 **/
	public static final int YEAR = 4;
	/** 无固定期 **/
	public static final int NON_FIXED_PERIOD = 5;

	/**
	 * 检查贷款期限类型值是否合法
	 * 
	 * @param loanTermType
	 * @return
	 */
	public static int check(int loanTermType) {
		if (loanTermType < DAY || loanTermType > NON_FIXED_PERIOD) {
			throw new IllegalArgumentException(String.format("the loanTermType[%s] is illegal", loanTermType));
		}
		return loanTermType;
	}
}
