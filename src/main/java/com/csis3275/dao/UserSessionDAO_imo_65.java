package com.csis3275.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model.UserSessionMapper_imo_65;
import com.csis3275.model.UserSession_imo_65;

@Component
public class UserSessionDAO_imo_65 {

	JdbcTemplate jdbcTemplate;

	// Mapping Queries

	/**
	 * String to select all entries in user_session table joining with user table
	 */
	private final String SQL_GET_ALL = "SELECT us.*, u.email AS user_email, u.last_name AS user_last_name, u.first_name AS user_first_name, u.user_type AS user_type "
			+ "FROM USER_SESSION AS us INNER JOIN USER AS u ON u.userid = us.userid ORDER BY us.start_datetime";

	/**
	 * sql string used to insert a new user
	 */
	private final String SQL_INSERT_ONE = "INSERT INTO USER_SESSION (SESSION_CODE, START_DATETIME, "
			+ " STATUS, USERID) VALUES(?,?,?,?)";

	/**
	 * sql used to delete one user_session based on its id
	 */
	private final String SQL_INVALIDATE_ONE = "UPDATE USER_SESSION SET STATUS = 'inactive' WHERE SESSIONID = ?";

	/**
	 * sql used to retrieve one user_session based on its id
	 */
	private final String SQL_SELECT_ONE = "SELECT us.*, u.email AS user_email, u.last_name AS user_last_name, u.first_name AS user_first_name, u.user_type AS user_type "
			+ "FROM USER_SESSION AS us INNER JOIN USER AS u ON u.userid = us.userid WHERE us.sessionid = ?";

	/**
	 * sql used to retrieve one user_session based on its id
	 */
	private final String SQL_SELECT_ONE_BY_SESSION_CODE_AND_STATUS = "SELECT us.*, u.email AS user_email, u.last_name AS user_last_name, u.first_name AS user_first_name, u.user_type AS user_type "
			+ "FROM USER_SESSION AS us INNER JOIN USER AS u ON u.userid = us.userid WHERE us.session_code = ? AND us.status = ? ORDER BY us.start_datetime DESC LIMIT 1";

	/**
	 * sql used to inactive one user_session based on its id
	 */
	private final String SQL_INACTIVE_ONE_BY_SESSIONID = "UPDATE USER_SESSION SET status = ?, end_datetime = ? WHERE sessionid = ?";
	private final String SQL_SELECT_ONEID = "SELECT * FROM USER_SESSION WHERE STATUS = 'active' AND SESSION_CODE = '";

	/**
	 * 
	 * @param dataSource object used to initialize database connection
	 */
	@Autowired
	public UserSessionDAO_imo_65(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 
	 * @return submits sql query for list all user_session entries
	 */
	public List<UserSession_imo_65> getAllUserSession() {
		return jdbcTemplate.query(SQL_GET_ALL, new UserSessionMapper_imo_65());
	}

	/**
	 * 
	 * @param newUserSession current user session
	 * @return submits sql query for insert one row
	 */
	public boolean createNewUserSession(UserSession_imo_65 newUserSession) {

		return jdbcTemplate.update(SQL_INSERT_ONE, newUserSession.getSession_code(), newUserSession.getStart_datetime(),
				newUserSession.getStatus(), newUserSession.getUserid()) > 0;
	}

	/**
	 * 
	 * @param id used session to delete a user from table
	 * @return true if an entry was modified
	 */
	public boolean InvalidateUserSession(int id) {

		return (jdbcTemplate.update(SQL_INVALIDATE_ONE, id) > 0);

	}

	/**
	 * @param objId session id
	 * @return return one User Session by ID
	 */
	public UserSession_imo_65 getBySessionId(int objId) {

		return (UserSession_imo_65) jdbcTemplate.queryForObject(SQL_SELECT_ONE, new Object[] { objId },
				new UserSessionMapper_imo_65());
	}

	/**
	 * @param sessionCode current session code
	 * @param sessionState current session state
	 * @return return one User Session by ID
	 */
	public UserSession_imo_65 getBySessionCodeAndStatus(String sessionCode, String sessionState) {
		try {
			return jdbcTemplate.queryForObject(SQL_SELECT_ONE_BY_SESSION_CODE_AND_STATUS,
					new Object[] { sessionCode, sessionState }, new UserSessionMapper_imo_65());

		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	/**
	 * 
	 * @param userSession the user session object that contains the modified
	 *                    information
	 * @param newStatus new status
	 * @param endDatetime end date
	 * @return true if a user session entry was modified on the database
	 */
	public boolean updateInactive(UserSession_imo_65 userSession, String newStatus, Date endDatetime) {
		boolean val=false;
		try{
			if((jdbcTemplate.update(SQL_INACTIVE_ONE_BY_SESSIONID, newStatus, endDatetime, userSession.getSessionid()) > 0)) {
				val=true;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		

		return val;
	}

	public UserSession_imo_65 getBySessionbyId(String objId) {
		UserSession_imo_65 reruser;
		if(jdbcTemplate.query(SQL_SELECT_ONEID + objId + "'", new UserSessionMapper_imo_65()).size()>0) {
			reruser=jdbcTemplate.query(SQL_SELECT_ONEID + objId + "'", new UserSessionMapper_imo_65()).get(0);
		}else {
			reruser=null;
			
		}
		return reruser;
	}

}
