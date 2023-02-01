package com.csis3275.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model.*;

/**
 * DAO class to map the MVC model BaseCalendar class and the associated database table data using SQL.
 * @author Ricardo dos Santos Alves de Souza
 *
 */
@Component
public class BaseCalendarDAOImpl_rso_35 {
	
	JdbcTemplate jdbcTemplate;
	
	//Mapping queries
	private final String SQL_GET_ALL = "SELECT * FROM base_calendar;";
	
	private final String SQL_GET_ONE = "SELECT * FROM base_calendar WHERE base_calendarId = ?;";

	private final String SQL_GET_LAST_ONE = "SELECT * FROM base_calendar ORDER BY base_calendarId DESC LIMIT 1;";	
	
	private final String SQL_INSERT_ONE = "INSERT INTO base_calendar(BASE_CALENDAR_NAME, WEEK_DAYS_START_TIME, WEEK_DAYS_END_TIME, WEEKEND_DAYS_START_TIME, WEEKEND_DAYS_END_TIME, BASE_CALENDAR_NOTES)"
											+ " VALUES(?,?,?,?,?,?);";
	
	private final String SQL_UPDATE_ONE = "UPDATE base_calendar SET BASE_CALENDAR_NAME = ?, WEEK_DAYS_START_TIME = ?, WEEK_DAYS_END_TIME = ?, WEEKEND_DAYS_START_TIME = ?, WEEKEND_DAYS_END_TIME = ?, BASE_CALENDAR_NOTES = ?"
			+ " WHERE base_calendarId = ?;";	
	
	private final String SQL_DELETE_ONE = "DELETE FROM base_calendar WHERE base_calendarId = ?;";
	
	private final String SQL_GET_COUNT_REFERENCED_ROOMS = "SELECT COUNT (*) FROM room WHERE base_calendarId = ?;";
	

	/**
	 * Default constructor
	 * 
	 * @param dataSource Database source object
	 */
	@Autowired
	public BaseCalendarDAOImpl_rso_35(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);
		
	}	
	
	/**
	 * Get all the data
	 * 
	 * @return List of class objects containing the data stored in the database
	 */
	public List<BaseCalendar_rso_35> getAllRows() {
		
		return jdbcTemplate.query(SQL_GET_ALL, new BaseCalendarMapper_rso_35());
	}
	
	/**
	 * Get only the last row inserted
	 * 
	 * @return one class object containing the respective data stored in the database
	 */
	public BaseCalendar_rso_35 getLastInsertedRow() {
		
		return (BaseCalendar_rso_35) jdbcTemplate.queryForObject(SQL_GET_LAST_ONE, new BaseCalendarMapper_rso_35());

	}	
	
	/**
	 * Get only one object by ID
	 * 
	 * @param objId id of the desired row 
	 * @return one class object containing the respective data stored in the database
	 */
	public BaseCalendar_rso_35 getRowById(int objId) {
		
		return (BaseCalendar_rso_35) jdbcTemplate.queryForObject(SQL_GET_ONE, new Object[]{objId}, new BaseCalendarMapper_rso_35());

	}
	
	/**
	 * Create a new row containing the respective data
	 * 
	 * @param newObj class object containing the data
	 * @return true if the query ran ok
	 */
	public boolean createNewRow(BaseCalendar_rso_35 newObj) {
		
		//Insert new row
		return (jdbcTemplate.update(SQL_INSERT_ONE,
									newObj.getName(),
									newObj.getWeekdaysStartTime(),
									newObj.getWeekdaysEndTime(),
									newObj.getWeekenddaysStartTime(),
									newObj.getWeekenddaysEndTime(),
									newObj.getNotes()
									) > 0);
	}	
	
	/**
	 * Update a row containing the respective data
	 * 
	 * @param pObj class object containing the data
	 * @return true if the query ran ok
	 */
	public boolean updateRow(BaseCalendar_rso_35 pObj) {
		
		return (jdbcTemplate.update(SQL_UPDATE_ONE,
									pObj.getName(),
									pObj.getWeekdaysStartTime(),
									pObj.getWeekdaysEndTime(),
									pObj.getWeekenddaysStartTime(),
									pObj.getWeekenddaysEndTime(),
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
	public boolean deleteRow(BaseCalendar_rso_35 pObj) {
		
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
	
	/**
	 * Get the number of Rooms currently related to a Base Calendar
	 * @param baseCalendarId Base Calendar Id 
	 * @return number of related Rooms
	 */
	public int getCountRelatedRooms(int baseCalendarId) {
		
		return (int) jdbcTemplate.queryForObject(SQL_GET_COUNT_REFERENCED_ROOMS, new Object[]{baseCalendarId}, Integer.class);

	}	

}
