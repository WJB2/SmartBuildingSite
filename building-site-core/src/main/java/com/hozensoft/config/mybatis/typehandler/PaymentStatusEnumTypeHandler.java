package com.hozensoft.config.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hozensoft.smartsite.enumeration.PaymentStatusEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

/**
 * @author JRabbit
 *
 */
@MappedTypes(PaymentStatusEnum.class)
@MappedJdbcTypes({ JdbcType.INTEGER, JdbcType.BIGINT, JdbcType.NUMERIC })
public class PaymentStatusEnumTypeHandler implements TypeHandler<PaymentStatusEnum> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.type.TypeHandler#setParameter(java.sql.
	 * PreparedStatement, int, java.lang.Object,
	 * org.apache.ibatis.type.JdbcType)
	 */
	@Override
	public void setParameter(PreparedStatement ps, int i, PaymentStatusEnum status, JdbcType jdbcType) throws SQLException {

		ps.setInt(i, status.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet,
	 * java.lang.String)
	 */
	@Override
	public PaymentStatusEnum getResult(ResultSet rs, String columnName) throws SQLException {

		int result = rs.getInt(columnName);

		return PaymentStatusEnum.valueOf(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public PaymentStatusEnum getResult(ResultSet rs, int columnIndex) throws SQLException {

		int result = rs.getInt(columnIndex);

		return PaymentStatusEnum.valueOf(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.ibatis.type.TypeHandler#getResult(java.sql.CallableStatement,
	 * int)
	 */
	@Override
	public PaymentStatusEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {

		int result = cs.getInt(columnIndex);

		return PaymentStatusEnum.valueOf(result);
	}
}

