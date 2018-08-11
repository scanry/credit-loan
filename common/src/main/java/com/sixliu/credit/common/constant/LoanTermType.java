package com.sixliu.credit.common.constant;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;


/**
 * @author:MG01867
 * @date:2018年6月21日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 贷款期限类型
 */
public enum LoanTermType {

	/** 无固定期 **/
	NON_FIXED_PERIOD(0),
	/** 天 **/
	DAY(1),
	/** 月 **/
	MONTH(2),
	/** 季度 **/
	QUARTER(3),
	/** 年 **/
	YEAR(4);

	private int value;

	LoanTermType(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}

	public static LoanTermType valueOf(int value) {
		if (0 == value) {
			return NON_FIXED_PERIOD;
		} else if (1 == value) {
			return DAY;
		} else if (2 == value) {
			return MONTH;
		} else if (3 == value) {
			return QUARTER;
		} else if (4 == value) {
			return YEAR;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 检查贷款期限类型值是否合法
	 * 
	 * @param loanTermType
	 * @return
	 */
	public static boolean check(int loanTermType) {
		if (loanTermType < NON_FIXED_PERIOD.value || loanTermType > YEAR.value) {
			return false;
		}
		return true;
	}
	
	@MappedTypes(value=LoanTermType.class) 
	@MappedJdbcTypes(value= {JdbcType.INTEGER})
	public static class LoanTermTypeHandler extends BaseTypeHandler<LoanTermType> {

		@Override
		public void setNonNullParameter(PreparedStatement ps, int i, LoanTermType parameter, JdbcType jdbcType)
				throws SQLException {
			ps.setInt(i, parameter.value());
		}

		@Override
		public LoanTermType getNullableResult(ResultSet rs, String columnName) throws SQLException {
			int value = rs.getInt(columnName);
			return LoanTermType.valueOf(value);
		}

		@Override
		public LoanTermType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
			int value = rs.getInt(columnIndex);
			return LoanTermType.valueOf(value);
		}

		@Override
		public LoanTermType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
			int value = cs.getInt(columnIndex);
			return LoanTermType.valueOf(value);
		}

	}
}
