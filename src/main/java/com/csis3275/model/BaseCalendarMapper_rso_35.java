package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


/**
 * DAO class to map the MVC model class and respective database table field
 * 
 * @author Ricardo dos Santos Alves de Souza
 *
 */
public class BaseCalendarMapper_rso_35  implements RowMapper<BaseCalendar_rso_35> {
	
	public BaseCalendar_rso_35 mapRow(ResultSet resultSet, int i) throws SQLException {
		
		BaseCalendar_rso_35 baseCalendarObj = new BaseCalendar_rso_35();
		
		baseCalendarObj.setId(resultSet.getInt("BASE_CALENDARID"));
		baseCalendarObj.setName(resultSet.getString("BASE_CALENDAR_NAME"));
		baseCalendarObj.setWeekdaysStartTime(resultSet.getTime("WEEK_DAYS_START_TIME"));	
		baseCalendarObj.setWeekdaysEndTime(resultSet.getTime("WEEK_DAYS_END_TIME"));
		baseCalendarObj.setWeekenddaysStartTime(resultSet.getTime("WEEKEND_DAYS_START_TIME"));
		baseCalendarObj.setWeekenddaysEndTime(resultSet.getTime("WEEKEND_DAYS_END_TIME"));
		baseCalendarObj.setNotes(resultSet.getString("BASE_CALENDAR_NOTES"));
		
		//BaseCalendarUnavailableDays Class composition
		//baseCalendarObj.setUnavaiableDays(resultSet.get);

		return baseCalendarObj;
		
	}

}
