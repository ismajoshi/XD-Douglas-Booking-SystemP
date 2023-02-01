package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
/**
 * 
 * @author Ismael Moreno
 *
 */
public class UserMapper_imo_65 implements RowMapper<User_imo_65> {

	/**
	 * User mapper retrieves information from databases and assigns it to a user object
	 */
	@Override
	public User_imo_65 mapRow(ResultSet rs, int rowNum) throws SQLException {
		User_imo_65 user = new User_imo_65();
		user.setId(rs.getInt("USERID"));
		user.setfName(rs.getString("FIRST_NAME"));
		user.setLName(rs.getString("LAST_NAME"));
		user.setEmail(rs.getString("EMAIL"));
		user.setPhone(rs.getString("PHONE_NUMBER"));
		user.setRegDate(rs.getDate("REGISTRATION_DATE"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setType(rs.getString("USER_TYPE"));
		user.setStatus(rs.getString("STATUS"));
		return user;
	}

}
