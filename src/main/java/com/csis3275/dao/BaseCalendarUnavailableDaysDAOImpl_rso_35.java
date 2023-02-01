package com.csis3275.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model.*;

/**
 * DAO class to map the MVC model BaseCalendarUnavailableDays class and the associated database table data using SQL.
 * @author Ricardo dos Santos Alves de Souza
 *
 */
@Component
public class BaseCalendarUnavailableDaysDAOImpl_rso_35 {
	
	JdbcTemplate jdbcTemplate;
	
	//Mapping queries
	private final String SQL_GET_ALL = "SELECT * FROM base_calendar_unavailable_days;";
	
	private final String SQL_GET_ALL_BY_BASE_CALENDAR_ID = "SELECT * FROM base_calendar_unavailable_days WHERE base_calendarid = ?;";
	
	private final String SQL_GET_BY_BASE_CALENDAR_ID_AND_DATE = "SELECT * FROM base_calendar_unavailable_days WHERE base_calendarid = ? AND unvailable_date = ?;";	
	
	private final String SQL_GET_ONE = "SELECT * FROM base_calendar_unavailable_days WHERE unavailable_dayid = ?;";

	private final String SQL_GET_LAST_ONE = "SELECT * FROM base_calendar_unavailable_days ORDER BY unavailable_dayid DESC LIMIT 1;";	
	
	private final String SQL_INSERT_ONE = "INSERT INTO base_calendar_unavailable_days(base_calendarid, unvailable_date, unvailable_start_time, unvailable_end_time, unvailable_day_name, unvailable_day_notes)"
											+ " VALUES(?,?,?,?,?,?);";
	
	private final String SQL_UPDATE_ONE = "UPDATE base_calendar_unavailable_days SET unvailable_date = ?, unvailable_start_time = ?, unvailable_end_time = ?, unvailable_day_name = ?, unvailable_day_notes = ?"
			+ " WHERE unavailable_dayid = ?;";	
	
	private final String SQL_DELETE_ONE = "DELETE FROM base_calendar_unavailable_days WHERE unavailable_dayid = ?;";	
	

	/**
	 * Default constructor
	 * 
	 * @param dataSource Database source object
	 */
	@Autowired
	public BaseCalendarUnavailableDaysDAOImpl_rso_35(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);
		
	}	
	
	/**
	 * Get all the data
	 * 
	 * @return List of class objects containing the data stored in the database
	 */
	public List<BaseCalendarUnavailableDays_rso_35> getAllRows() {
		
		return jdbcTemplate.query(SQL_GET_ALL, new BaseCalendarUnavailableDaysMapper_rso_35());
	}
	
	/**
	 * Get only the last row inserted
	 * 
	 * @return one class object containing the respective data stored in the database
	 */
	public BaseCalendarUnavailableDays_rso_35 getLastInsertedRow() {
		
		return (BaseCalendarUnavailableDays_rso_35) jdbcTemplate.queryForObject(SQL_GET_LAST_ONE, new BaseCalendarUnavailableDaysMapper_rso_35());

	}	
	
	/**
	 * Get only one object by ID
	 * 
	 * @param objId id of the desired row 
	 * @return one class object containing the respective data stored in the database
	 */
	public BaseCalendarUnavailableDays_rso_35 getRowById(int objId) {
		
		return (BaseCalendarUnavailableDays_rso_35) jdbcTemplate.queryForObject(SQL_GET_ONE, new Object[]{objId}, new BaseCalendarUnavailableDaysMapper_rso_35());

	}
	
	/**
	 * Get the data by BaseCalendarId (FK)
	 * 
	 * @param objId id of the associated BaseCalendar obj
	 * @return List of class objects containing the data stored in the database
	 */
	public List<BaseCalendarUnavailableDays_rso_35> getRowsByBaseCalendarId(int objId) {
		
		return jdbcTemplate.query(SQL_GET_ALL_BY_BASE_CALENDAR_ID, new Object[]{objId}, new BaseCalendarUnavailableDaysMapper_rso_35());
	}
	
	/**
	 * Get the data by BaseCalendarId (FK)
	 * 
	 * @param objId id of the associated BaseCalendar obj
	 * @param pDate desired date to retrieve
	 * @return List of class objects containing the data stored in the database
	 */
	public List<BaseCalendarUnavailableDays_rso_35> getRowsByBaseCalendarIdAndDate(int objId, Date pDate) {
		
		return jdbcTemplate.query(SQL_GET_BY_BASE_CALENDAR_ID_AND_DATE, new Object[]{objId, pDate}, new BaseCalendarUnavailableDaysMapper_rso_35());
	}	
	
	/**
	 * Create a new row containing the respective data
	 * 
	 * @param newObj class object containing the data
	 * @return true if the query ran ok
	 */
	public boolean createNewRow(BaseCalendarUnavailableDays_rso_35 newObj) {
		
		//Insert new row
		return (jdbcTemplate.update(SQL_INSERT_ONE,
									newObj.getBaseCalendarId(),
									newObj.getDate(),
									newObj.getUnavailableStartTime(),
									newObj.getUnavailableEndTime(),
									newObj.getName(),
									newObj.getNotes()
									) > 0);
	}	
	
	/**
	 * Update a row containing the respective data
	 * 
	 * @param pObj class object containing the data
	 * @return true if the query ran ok
	 */
	public boolean updateRow(BaseCalendarUnavailableDays_rso_35 pObj) {
		
		return (jdbcTemplate.update(SQL_UPDATE_ONE,
									pObj.getDate(),
									pObj.getUnavailableStartTime(),
									pObj.getUnavailableEndTime(),
									pObj.getName(),
									pObj.getNotes(),
									pObj.getId()
									) > 0);
	}
	
	/**
	 * Delete a row containing the respective data
	 * 
	 * @param pObj class object containing the data
	 * @return true if the query ran ok
	 */
	public boolean deleteRow(BaseCalendarUnavailableDays_rso_35 pObj) {
		
		return (jdbcTemplate.update(SQL_DELETE_ONE, pObj.getId()) > 0); 
		
	}
	
	/**
	 * Delete a row containing the respective data
	 * 
	 * @param pId class object containing the data
	 * @return true if the query ran ok
	 */
	public boolean deleteRow(int pId) {
		
		return (jdbcTemplate.update(SQL_DELETE_ONE, pId) > 0); 
		
	}		
		
		

}
