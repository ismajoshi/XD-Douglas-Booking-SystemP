/**
 * 
 */
package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author Scott Linden - 300327715
 *
 */
public class ReservationMapper_sli_15 implements RowMapper<Reservation_sli_15> {

	@Override
	public Reservation_sli_15 mapRow(ResultSet rs, int rowNum) throws SQLException {

		Reservation_sli_15 reservation = new Reservation_sli_15();
		
		reservation.setReservationID(rs.getInt("RESERVATIONID"));
		reservation.setTitle(rs.getString("RESERVATION_TITLE"));
		reservation.setAteendees(rs.getInt("EXPECTED_ATEENDEES"));
		reservation.setType(rs.getString("RESERVATION_TYPE"));
		reservation.setStart(rs.getTimestamp("RESERVATION_START_DATETIME"));
		reservation.setEnd(rs.getTimestamp("RESERVATION_END_DATETIME"));
		reservation.setStatus(rs.getString("STATUS"));
		reservation.setUserID(rs.getInt("USERID"));
		reservation.setRoomID(rs.getInt("ROOMID"));
		
		return reservation;
	}
}
