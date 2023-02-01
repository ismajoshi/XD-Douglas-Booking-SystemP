package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RoomListScheduleMapper_aca_58 implements RowMapper<RoomListSchedule_aca_58> {

	@Override
	public RoomListSchedule_aca_58 mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		RoomListSchedule_aca_58 roomsList= new RoomListSchedule_aca_58();
		
		roomsList.setReservationID(rs.getInt("RESERVATIONID"));
		roomsList.setReservation_title(rs.getString("RESERVATION_TITLE"));
		roomsList.setExpected_attendees(rs.getString("EXPECTED_ATEENDEES"));
		roomsList.setReservation_type(rs.getNString("RESERVATION_TYPE"));
		roomsList.setReservation_dateTimeDate(rs.getTimestamp("RESERVATION_START_DATETIME"));
		roomsList.setReservation_end_DateTimeDate(rs.getTimestamp("RESERVATION_END_DATETIME"));
		roomsList.setStatus(rs.getString("STATUS"));
		roomsList.setUserID(rs.getInt("USERID"));
		roomsList.setRoomID(rs.getInt("ROOMID"));
		
		
		
		return roomsList;
	}

}
