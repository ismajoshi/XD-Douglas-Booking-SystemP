package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class UserSessionMapper_imo_65 implements RowMapper<UserSession_imo_65> {

	/**
	 * User mapper retrieves information from databases and assigns it to a user_session object
	 */
	@Override
	public UserSession_imo_65 mapRow(ResultSet rs, int rowNum) throws SQLException {

		UserSession_imo_65 userSession = new UserSession_imo_65();
		
		userSession.setSessionid(rs.getInt("sessionid"));
		userSession.setSession_code(rs.getString("session_code"));
		userSession.setStart_datetime(rs.getTimestamp("start_datetime"));
		userSession.setEnd_datetime(rs.getTimestamp("end_datetime"));
		userSession.setStatus(rs.getString("status"));		
		userSession.setUserid(rs.getInt("userid"));

		try {
			userSession.setUser();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return userSession;
		
	}
	
}
