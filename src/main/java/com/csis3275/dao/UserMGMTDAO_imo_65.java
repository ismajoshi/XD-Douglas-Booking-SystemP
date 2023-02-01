package com.csis3275.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model.BaseCalendarMapper_rso_35;
import com.csis3275.model.BaseCalendar_rso_35;
import com.csis3275.model.UserMapper_imo_65;
import com.csis3275.model.User_imo_65;
/**
 * 
 * @author ismael Moreno
 *
 */
@Component
public class UserMGMTDAO_imo_65 {

	JdbcTemplate jdbcTemplate;

	// Mapping Queries
	/**
	 * String to select all entries in user table
	 */
	private final String SQL_GET_ALL = "SELECT * FROM USER";
	/**
	 * sql string used to insert a new user
	 */
	private final String SQL_INSERT_ONE = "INSERT INTO USER (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER,"
			+ " REGISTRATION_DATE, PASSWORD, USER_TYPE, STATUS) VALUES(?,?,?,?,?,?,?,?)";
	/**
	 * sql used to delete one user based on its id
	 */
	private final String SQL_DELETE_ONE = "DELETE FROM USER WHERE USERID = ?";
	/**
	 * sql used to retrieve one used based on its id
	 */
	private final String SQL_SELECT_ONE = "SELECT * FROM USER WHERE USERID = ";
	/**
	 * sql used to get the last user added
	 */
	private final String SQL_GET_LAST_ONE = "SELECT * FROM USER ORDER BY USERID desc LIMIT 1";
	/**
	 * sql string used to update an user entry
	 */
	private final String SQL_UPDATE_USER = "UPDATE USER SET FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, PHONE_NUMBER = ?, PASSWORD = ?, USER_TYPE = ? WHERE USERID = ?";
	
	/**
	 * sql used to retrieve one used based on its email
	 */
	private final String SQL_SELECT_ONE_BY_EMAIL = "SELECT * FROM USER WHERE EMAIL = ? LIMIT 1";
	

	/**
	 * 
	 * @param dataSource object used to initialize database connection
	 */
	@Autowired
	public UserMGMTDAO_imo_65(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	/**
	 * 
	 * @return submits sql query for list all user entries
	 */
	public List<User_imo_65> getAllUsers() {
		return jdbcTemplate.query(SQL_GET_ALL, new UserMapper_imo_65());
	}

	public boolean createNewUser(User_imo_65 newuser) {
		return jdbcTemplate.update(SQL_INSERT_ONE, newuser.getfName(), newuser.getLName(), newuser.getEmail(),
				newuser.getPhone(), newuser.getRegDate(), newuser.getPassword(), newuser.getType(),
				newuser.getStatus()) > 0;
	}
	/**
	 * 
	 * @param id used to delete a user from table
	 * @return true if an entry was modified
	 */
	public boolean deleteUser(int id) {

		return (jdbcTemplate.update(SQL_DELETE_ONE, id) > 0);

	}
/**
 * 
 * @return user mapper object with last user information 
 */
	public User_imo_65 getLastUser() {
		return jdbcTemplate.query(SQL_GET_LAST_ONE, new UserMapper_imo_65()).get(0);
	}
	/**
	 * 
	 * @param id user id user to select the user thats going be modified
	 * @return user mapper object with the selected user information
	 */
	public User_imo_65 getUserbyId(int id) {
		return jdbcTemplate.query(SQL_SELECT_ONE+id, new UserMapper_imo_65()).get(0);
	}
	/**
	 * 
	 * @param user the user object that contains the modified information
	 * @return true if a user entry was modified on the database
	 */
	public boolean updateUser(User_imo_65 user) {
		return (jdbcTemplate.update(SQL_UPDATE_USER,user.getfName(),user.getLName(),user.getEmail(),user.getPhone(),user.getPassword(),user.getType(),user.getId())> 0);
	}
	
	/**
	 * 
	 * @param email user email 
	 * @return user mapper object with the selected user information
	 */
	public User_imo_65 getUserbyEmail(String email) {
		
		return (User_imo_65) jdbcTemplate.queryForObject(SQL_SELECT_ONE_BY_EMAIL, new Object[]{email}, new UserMapper_imo_65());		
	}
	

}
