package com.sixliu.credit.common.constant;
/**
*@author:MG01867
*@date:2018年6月21日
*@E-mail:359852326@qq.com
*@version:
*@describe 货币类型
*/
public class CurrencyType {

	public static final int RMB = 1;
	public static final int DOLLAR= 2;

	
	/**
	 * 检查贷款期限类型值是否合法
	 * @param loanTermType
	 * @return
	 */
	public static int check(int loanTermType) {
		if(loanTermType<1||loanTermType>4) {
			throw new IllegalArgumentException(String.format("the loanTermType[%s] is illegal", loanTermType));
		}
		return loanTermType;
	}
}
