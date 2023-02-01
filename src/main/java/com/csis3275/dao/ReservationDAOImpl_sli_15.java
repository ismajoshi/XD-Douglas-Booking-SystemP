/**
 * 
 */
package com.csis3275.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model.ReservationMapper_sli_15;
import com.csis3275.model.Reservation_sli_15;

/**
 * @author someone
 *
 */
@Component
public class ReservationDAOImpl_sli_15 {
	
	JdbcTemplate jdbcTemplate;
	
	private final String SQL_GET_ALL = "SELECT * FROM ROOM_RESERVATION";
	private final String SQL_DELETE_ONE = "DELETE FROM ROOM_RESERVATION  WHERE RESERVATIONID = ?;";	
	private final String SQL_GET_ONE = "SELECT * FROM ROOM_RESERVATION  WHERE RESERVATIONID  = ?;";
	private final String SQL_GET_LAST_ONE = "SELECT * FROM ROOM_RESERVATION  ORDER BY RESERVATIONID desc LIMIT 1;";
	
	private final String SQL_INSERT_ONE = "INSERT INTO ROOM_RESERVATION  (RESERVATION_TITLE, EXPECTED_ATEENDEES, "
			+ "RESERVATION_TYPE, RESERVATION_START_DATETIME, RESERVATION_END_DATETIME, "
			+ "STATUS, USERID, ROOMID) VALUES(?,?,?,?,?,?,?,?);";
	
	private final String SQL_UPDATE_ROOM = "UPDATE ROOM_RESERVATION  SET RESERVATION_TITLE = ?, EXPECTED_ATEENDEES = ?, "
			+ "RESERVATION_TYPE = ?, RESERVATION_START_DATETIME = ?, RESERVATION_END_DATETIME = ?, "
			+ "STATUS = ?, USERID = ?, ROOMID = ? WHERE RESERVATIONID =?";
	
	private final String SQL_GET_BY_ROOMID_AND_DATE = "SELECT * FROM ROOM_RESERVATION WHERE ROOMID = ? " + 
														"AND CAST(RESERVATION_START_DATETIME AS DATE) <= ?" + 
														"AND CAST(RESERVATION_END_DATETIME AS DATE) >= ?";

	
	@Autowired
	public ReservationDAOImpl_sli_15(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Reservation_sli_15> getAllReservations(){
		return jdbcTemplate.query(SQL_GET_ALL,  new ReservationMapper_sli_15());
	}
	
	public Reservation_sli_15 getReservation(int id) {
		return (Reservation_sli_15) jdbcTemplate.queryForObject(SQL_GET_ONE, new ReservationMapper_sli_15(), id);
	}
	
	/**
	 * Get all the reservations related to a Room that are included on a selected date
	 * @author Ricardo dos Santos Alves de Souza
	 * @param roomId selected Room Id
	 * @param selectedDate selected Date
	 * @return list of reservations
	 */
	public List<Reservation_sli_15> getReservationsByRoomIdAndDate(int roomId, Date selectedDate){
		return jdbcTemplate.query(SQL_GET_BY_ROOMID_AND_DATE, new Object[]{roomId, selectedDate, selectedDate}, new ReservationMapper_sli_15());
	}
	
	
	public boolean deleteReservation(int id) {
		return (jdbcTemplate.update(SQL_DELETE_ONE, id) > 0); 
	}
	
	public Reservation_sli_15 getLastReservation() {
		return (Reservation_sli_15) jdbcTemplate.queryForObject(SQL_GET_LAST_ONE,  new ReservationMapper_sli_15());
	}
	
	public boolean createReservation(Reservation_sli_15 newRes) {
		return (jdbcTemplate.update(SQL_INSERT_ONE, newRes.getTitle(),
				newRes.getAteendees(), newRes.getType(), newRes.getStart(),
				newRes.getEnd(),newRes.getStatus(),newRes.getUserID(),newRes.getRoomID()) > 0);
	}
	
	public boolean updateReservation(Reservation_sli_15 reservation) {
		return (jdbcTemplate.update(SQL_UPDATE_ROOM, reservation.getTitle(),
				reservation.getAteendees(), reservation.getType(), reservation.getStart(),
				reservation.getEnd(),reservation.getStatus(),reservation.getUserID(),reservation.getRoomID(), reservation.getReservationID())> 0);
	}

}
