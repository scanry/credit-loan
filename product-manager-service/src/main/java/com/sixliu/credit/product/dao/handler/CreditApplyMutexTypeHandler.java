package com.sixliu.credit.product.dao.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.sixliu.credit.product.CreditApplyMutexType;

/**
 * @author:MG01867
 * @date:2018年8月10日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
public class CreditApplyMutexTypeHandler extends BaseTypeHandler<CreditApplyMutexType> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, CreditApplyMutexType parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setInt(i, parameter.value());
	}

	@Override
	public CreditApplyMutexType getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int value = rs.getInt(columnName);
		return CreditApplyMutexType.valueOf(value);
	}

	@Override
	public CreditApplyMutexType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		int value = rs.getInt(columnIndex);
		return CreditApplyMutexType.valueOf(value);
	}

	@Override
	public CreditApplyMutexType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		int value = cs.getInt(columnIndex);
		return CreditApplyMutexType.valueOf(value);
	}

}
