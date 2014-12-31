package com.iems.core.dao.support;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {
	public Object mapRow(ResultSet rs, int index)
	        throws SQLException;
}
