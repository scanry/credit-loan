package com.sixliu.credit.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author:MG01867
 * @date:2018年7月9日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
public class ValidationUtils {

	private static String ID_REGEX_15 = "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$";
	private static String ID_REGEX_18 = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
	private static Pattern ID_PATTERN_15 = Pattern.compile(ID_REGEX_15);
	private static Pattern ID_PATTERN_18 = Pattern.compile(ID_REGEX_18);

	/**
	 * 检查中国公民身份证号码是否有效
	 * @param ID
	 * @return
	 */
	public static boolean validateChineseID(String ID) {
		if (null == ID || "".equals(ID)) {
			return false;
		}
		if (15 == ID.length()) {
			return doValidateChineseID(ID_PATTERN_15, ID);
		} else if (18 == ID.length()) {
			return doValidateChineseID(ID_PATTERN_18, ID);
		} else {
			return false;
		}
	}

	private static boolean doValidateChineseID(Pattern pattern, String id) {
		Matcher matcher = pattern.matcher(id);
		if (!matcher.find()) {
			return false;
		} else {
			return true;
		}
	}
}
