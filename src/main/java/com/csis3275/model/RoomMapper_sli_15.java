package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author Scott Linden - 300327715
 *This is the mapper for the Room Object
 */
public class RoomMapper_sli_15 implements RowMapper<Room_sli_15> {

	@Override
	public Room_sli_15 mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Room_sli_15 roomObj = new Room_sli_15();
		
		roomObj.setBuilding(rs.getString("BUILDING"));
		roomObj.setRoomType(rs.getString("ROOM_TYPE"));
		roomObj.setRoomID(rs.getInt("ROOMID"));
		roomObj.setNumber(rs.getString("NUMBER"));
		roomObj.setFloor(rs.getString("FLOOR"));
		roomObj.setCapacity(rs.getInt("CAPACITY"));
		roomObj.setBaseCalendar(rs.getInt("BASE_CALENDARID"));
		
		return roomObj;
	}

}
