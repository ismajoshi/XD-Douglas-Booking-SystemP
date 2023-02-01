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
public class BaseCalendarUnavailableDaysMapper_rso_35  implements RowMapper<BaseCalendarUnavailableDays_rso_35> {
	
	public BaseCalendarUnavailableDays_rso_35 mapRow(ResultSet resultSet, int i) throws SQLException {
		
		BaseCalendarUnavailableDays_rso_35 baseCalendarUnavailableDayObj = new BaseCalendarUnavailableDays_rso_35();
		
		baseCalendarUnavailableDayObj.setId(resultSet.getInt("UNAVAILABLE_DAYID"));
		baseCalendarUnavailableDayObj.setBaseCalendarId(resultSet.getInt("BASE_CALENDARID"));	
		baseCalendarUnavailableDayObj.setDate(resultSet.getDate("UNVAILABLE_DATE"));
		baseCalendarUnavailableDayObj.setUnavailableStartTime(resultSet.getTime("UNVAILABLE_START_TIME"));
		baseCalendarUnavailableDayObj.setUnavailableEndTime(resultSet.getTime("UNVAILABLE_END_TIME"));
		baseCalendarUnavailableDayObj.setName(resultSet.getString("UNVAILABLE_DAY_NAME"));
		baseCalendarUnavailableDayObj.setNotes(resultSet.getString("UNVAILABLE_DAY_NOTES"));		
		
		return baseCalendarUnavailableDayObj;
		
	}

}
