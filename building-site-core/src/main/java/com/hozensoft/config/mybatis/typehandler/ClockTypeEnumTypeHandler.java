package com.hozensoft.config.mybatis.typehandler;

import com.hozensoft.smartsite.enumeration.ClockTypeEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author JRabbit
 *
 */
@MappedTypes(ClockTypeEnum.class)
@MappedJdbcTypes({ JdbcType.INTEGER, JdbcType.BIGINT, JdbcType.NUMERIC })
public class ClockTypeEnumTypeHandler implements TypeHandler<ClockTypeEnum> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.type.TypeHandler#setParameter(java.sql.
	 * PreparedStatement, int, java.lang.Object,
	 * org.apache.ibatis.type.JdbcType)
	 */
	@Override
	public void setParameter(PreparedStatement ps, int i, ClockTypeEnum status, JdbcType jdbcType) throws SQLException {

		ps.setInt(i, status.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet,
	 * java.lang.String)
	 */
	@Override
	public ClockTypeEnum getResult(ResultSet rs, String columnName) throws SQLException {

		int result = rs.getInt(columnName);

		if(result==0){
			return null;
		}

		return ClockTypeEnum.valueOf(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public ClockTypeEnum getResult(ResultSet rs, int columnIndex) throws SQLException {

		int result = rs.getInt(columnIndex);

		if(result==0){
			return null;
		}

		return ClockTypeEnum.valueOf(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.ibatis.type.TypeHandler#getResult(java.sql.CallableStatement,
	 * int)
	 */
	@Override
	public ClockTypeEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {

		int result = cs.getInt(columnIndex);

		if(result==0){
			return null;
		}

		return ClockTypeEnum.valueOf(result);
	}
}

