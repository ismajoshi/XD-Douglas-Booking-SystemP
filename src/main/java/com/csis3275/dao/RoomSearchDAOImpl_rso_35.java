package com.csis3275.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model.RoomAmenityMapper_aca_58;
import com.csis3275.model.RoomAmenity_aca_58;
import com.csis3275.model.RoomMapper_sli_15;
import com.csis3275.model.RoomSearch_rso_35;
import com.csis3275.model.Room_sli_15;


/**
 * DAO class to handle database interface regarding the RoomSearch class.
 * @author Ricardo dos Santos Alves de Souza
 *
 */
@Component
public class RoomSearchDAOImpl_rso_35 {
	
	JdbcTemplate jdbcTemplate;
	
	//Mapping queries
	private final String SQL_GET_ROOM_AMENITY_TYPES = "SELECT DISTINCT AMENITIE_TYPE FROM ROOM_AMENITIE ORDER BY AMENITIE_TYPE;";	

	private final String SQL_GET_ROOM_TYPES = "SELECT DISTINCT ROOM_TYPE FROM ROOM ORDER BY ROOM_TYPE;";
	
	private final String SQL_GET_ROOM_BUILDINGS = "SELECT DISTINCT BUILDING  FROM ROOM ORDER BY BUILDING;";
	
	private final String SQL_GET_AMENITIES_BY_ROOM = "SELECT * FROM ROOM_AMENITIE WHERE roomid = ? ORDER BY name;";		
	

	/**
	 * Default constructor
	 * 
	 * @param dataSource Database source object
	 */
	@Autowired
	public RoomSearchDAOImpl_rso_35(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);
		
	}	
	
	/**
	 * Get a list of available Room Amenity types recorded in the database
	 *  
	 * @return String list of available Room Amenity types
	 */
	public List<String> getRoomAmenityTypes() {
		
		return jdbcTemplate.queryForList(SQL_GET_ROOM_AMENITY_TYPES, String.class);
	}
	
	/**
	 * Get a list of available Room Types recorded in the database
	 *  
	 * @return String list of available Room types
	 */
	public List<String> getRoomTypes() {
		
		return jdbcTemplate.queryForList(SQL_GET_ROOM_TYPES, String.class);
	}
	
	/**
	 * Get a list of available Room Building recorded in the database
	 *  
	 * @return String list of available Room types
	 */
	public List<String> getRoomBuildings() {
		
		return jdbcTemplate.queryForList(SQL_GET_ROOM_BUILDINGS, String.class);
	}
	
	/**
	 * 
	 * @param pRoomSearchObj Room Search Obj filled with searching fields 
	 * @return Filtered Room list 
	 */
	public List<Room_sli_15> getFilteredRooms(RoomSearch_rso_35 pRoomSearchObj) {
		
		//Define date and time formatters
		SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
		
		//SQL Select Clause 
		//Build the SQL based on the filled search fields
		String sqlSelectClause = "SELECT TOP 50 DISTINCT r.* FROM ROOM as r";

		//SQL Where Clause 
		String sqlWhereClause;
		
		//Room Capacity (required)
		if(pRoomSearchObj.getCapacity() > 1) {
			sqlWhereClause = " WHERE r.CAPACITY >=" + pRoomSearchObj.getCapacity() + "";
		}
		else
		{
			sqlWhereClause = " WHERE r.CAPACITY >= 1 ";			
		}
		
		//Room Type
		if(!pRoomSearchObj.getRoomType().isBlank()) {
			sqlWhereClause += " AND r.ROOM_TYPE = '" + pRoomSearchObj.getRoomType() + "'" ;
		}

		//Room Number
		if(!pRoomSearchObj.getNumber().isBlank()) {
			sqlWhereClause += " AND r.NUMBER like('%" + pRoomSearchObj.getNumber() + "%')";
		}
		
		//Room Floor
		if(!pRoomSearchObj.getFloor().isBlank()) {
			sqlWhereClause += " AND r.FLOOR like('%" + pRoomSearchObj.getFloor() + "%')";
		}
		
		//Room Building
		if(!pRoomSearchObj.getBuilding().isBlank()) {
			sqlWhereClause += " AND r.BUILDING like('%" + pRoomSearchObj.getBuilding() + "%')";
		}
		
		//Room Amenities Type
		if(pRoomSearchObj.getAmenitie_typeList() != null && pRoomSearchObj.getAmenitie_typeList().size() > 0) {
			sqlSelectClause += " INNER JOIN ROOM_AMENITIE as RA ON ra.ROOMID = r.ROOMID";
			sqlWhereClause += " AND (ra.AMENITIE_TYPE ='" + pRoomSearchObj.getAmenitie_typeList().get(0) + "'";
			
			for(int i=1; i < pRoomSearchObj.getAmenitie_typeList().size(); i++) {
				sqlWhereClause += " OR ra.AMENITIE_TYPE ='" + pRoomSearchObj.getAmenitie_typeList().get(i) + "'";
			}
			sqlWhereClause += ")";
		}
		
		//Desired Date
		if(pRoomSearchObj.isOnlyAvailable() && pRoomSearchObj.getDesiredStartDatetime() != null && pRoomSearchObj.getDesiredEndDatetime() != null) {
			
			//Check reservations
			sqlWhereClause += " AND r.ROOMID NOT IN (SELECT roomid FROM ROOM_RESERVATION AS res WHERE res.roomid = r.roomid";
			sqlWhereClause += " AND ((RESERVATION_START_DATETIME > '" + datetimeFormatter.format(pRoomSearchObj.getDesiredStartDatetime()) + "'";
			sqlWhereClause += " AND RESERVATION_START_DATETIME  < '" + datetimeFormatter.format(pRoomSearchObj.getDesiredEndDatetime()) + "')";
			sqlWhereClause += " OR (RESERVATION_END_DATETIME > '" + datetimeFormatter.format(pRoomSearchObj.getDesiredStartDatetime()) + "'";
			sqlWhereClause += " AND RESERVATION_END_DATETIME  < '" + datetimeFormatter.format(pRoomSearchObj.getDesiredEndDatetime()) + "'))";
			sqlWhereClause += " AND STATUS <> 'Canceled')";
			
			//Check Base Calendar based on the day of week
			Calendar localCalendarObj = Calendar.getInstance();
			
			//Check Start Datetime
			localCalendarObj.setTime(pRoomSearchObj.getDesiredStartDatetime());
			if(localCalendarObj.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || localCalendarObj.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				//Weekend
				sqlWhereClause += " AND r.ROOMID NOT IN (SELECT r.roomid FROM BASE_CALENDAR AS bc WHERE bc.BASE_CALENDARID = r.BASE_CALENDARID";
				sqlWhereClause += " AND (bc.WEEKEND_DAYS_START_TIME > '" + timeFormatter.format(pRoomSearchObj.getDesiredStartDatetime()) + "'";
				sqlWhereClause += " OR bc.WEEKEND_DAYS_END_TIME < '" + timeFormatter.format(pRoomSearchObj.getDesiredStartDatetime()) + "'))";
			} else {
				//Week day	
				sqlWhereClause += " AND r.ROOMID NOT IN (SELECT r.roomid FROM BASE_CALENDAR AS bc WHERE bc.BASE_CALENDARID = r.BASE_CALENDARID";
				sqlWhereClause += " AND (bc.WEEK_DAYS_START_TIME > '" + timeFormatter.format(pRoomSearchObj.getDesiredStartDatetime()) + "'";
				sqlWhereClause += " OR bc.WEEK_DAYS_END_TIME < '" + timeFormatter.format(pRoomSearchObj.getDesiredStartDatetime()) + "'))";
			}
			
			//Check End Datetime
			localCalendarObj.setTime(pRoomSearchObj.getDesiredEndDatetime());
			if(localCalendarObj.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || localCalendarObj.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				//Weekend
				sqlWhereClause += " AND r.ROOMID NOT IN (SELECT r.roomid FROM BASE_CALENDAR AS bc WHERE bc.BASE_CALENDARID = r.BASE_CALENDARID";
				sqlWhereClause += " AND (bc.WEEKEND_DAYS_START_TIME > '" + timeFormatter.format(pRoomSearchObj.getDesiredEndDatetime()) + "'";
				sqlWhereClause += " OR bc.WEEKEND_DAYS_END_TIME < '" + timeFormatter.format(pRoomSearchObj.getDesiredEndDatetime()) + "'))";
			} else {
				//Week day	
				sqlWhereClause += " AND r.ROOMID NOT IN (SELECT r.roomid FROM BASE_CALENDAR AS bc WHERE bc.BASE_CALENDARID = r.BASE_CALENDARID";
				sqlWhereClause += " AND (bc.WEEK_DAYS_START_TIME > '" + timeFormatter.format(pRoomSearchObj.getDesiredEndDatetime()) + "'";
				sqlWhereClause += " OR bc.WEEK_DAYS_END_TIME < '" + timeFormatter.format(pRoomSearchObj.getDesiredEndDatetime()) + "'))";
			}
			
			//Check Base Calendar Unavailable Days
			sqlWhereClause += " AND r.ROOMID NOT IN (SELECT r.roomid FROM BASE_CALENDAR AS bc INNER JOIN BASE_CALENDAR_UNAVAILABLE_DAYS AS bc_un ON bc_un.BASE_CALENDARID = bc.BASE_CALENDARID ";
			sqlWhereClause += " WHERE bc.BASE_CALENDARID = r.BASE_CALENDARID";
			sqlWhereClause += " AND ((bc_un.UNVAILABLE_DATE = '" + dateFormatter.format(pRoomSearchObj.getDesiredStartDatetime()) + "'";
			sqlWhereClause += " AND (bc_un.UNVAILABLE_START_TIME < '" + timeFormatter.format(pRoomSearchObj.getDesiredStartDatetime()) + "'";
			sqlWhereClause += " AND bc_un.UNVAILABLE_END_TIME > '" + timeFormatter.format(pRoomSearchObj.getDesiredStartDatetime()) + "'))";
			sqlWhereClause += " OR (bc_un.UNVAILABLE_DATE = '" + dateFormatter.format(pRoomSearchObj.getDesiredEndDatetime()) + "'";
			sqlWhereClause += " AND (bc_un.UNVAILABLE_START_TIME < '" + timeFormatter.format(pRoomSearchObj.getDesiredEndDatetime()) + "'";
			sqlWhereClause += " AND bc_un.UNVAILABLE_END_TIME > '" + timeFormatter.format(pRoomSearchObj.getDesiredEndDatetime()) + "'))))";
			
		}
		
		System.out.println("searchSQLString: " + sqlSelectClause + sqlWhereClause);
		
		return jdbcTemplate.query(sqlSelectClause + sqlWhereClause, new RoomMapper_sli_15());
	}
	
	/**
	 * Get a list containing all the Amenities associated to a room
	 * @param pRoomId selected Room Id
	 * @return List of Room Amenities
	 */
	public List<RoomAmenity_aca_58> getRoomAmenitiesByRoom(int pRoomId) {
		
		return (List<RoomAmenity_aca_58>) jdbcTemplate.query(SQL_GET_AMENITIES_BY_ROOM, new Object[]{pRoomId}, new RoomAmenityMapper_aca_58());
	}
	

}
