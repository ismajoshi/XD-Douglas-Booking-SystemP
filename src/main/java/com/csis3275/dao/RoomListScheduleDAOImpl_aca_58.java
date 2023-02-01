package com.csis3275.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model.RoomListScheduleMapper_aca_58;
import com.csis3275.model.RoomListSchedule_aca_58;

import java.util.List;

@Component
public class RoomListScheduleDAOImpl_aca_58 {
	
	
	JdbcTemplate jdbcTemplate; 
	
	
	
	private final String SQL_ROOM_ID = "Select distinct ROOMID from ROOM_RESERVATION ORDER BY ROOMID ASC";
	private final String SQL_ROOM_NUM = "Select distinct NUMBER from Room ORDER BY NUMBER ASC"; 
	private final String SQL_ROOM_TYPE = "Select distinct RESERVATION_TYPE from ROOM_RESERVATION";
	private final String SQL_GET_ALL_ROOMS = "Select * from ROOM_RESERVATION";
	private final String SQL_GET_DATE = "Select reservation_start_datetime from ROOM_RESERVATION WHERE reservation_start_datetime = ?  "; 
	
	private final String SQL_GET_ROOM_RESERVATIONS = "select * from Room_RESERVATION where ROOMID = ";
	
	@Autowired
	public RoomListScheduleDAOImpl_aca_58(DataSource dataSource) 	{
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<RoomListSchedule_aca_58> getRoomReservations(String roomID) {
		
		String SQL_GET_ROOM_RESERVATIONS_FROM_ID = new String();
		SQL_GET_ROOM_RESERVATIONS_FROM_ID += SQL_GET_ROOM_RESERVATIONS;
		SQL_GET_ROOM_RESERVATIONS_FROM_ID += roomID;
		
		return jdbcTemplate.query(SQL_GET_ROOM_RESERVATIONS_FROM_ID, new RoomListScheduleMapper_aca_58()); 
	}
	
	public List<String> getListRoomID()	{
		
		return jdbcTemplate.queryForList(SQL_ROOM_ID, String.class); 
	}
	
	public List<String> getListRoomNumber()	{
		
		return jdbcTemplate.queryForList(SQL_ROOM_NUM, String.class); 
	}
	
	public List<String> getListReservationType()	{
		 
		return jdbcTemplate.queryForList(SQL_ROOM_TYPE, String.class);
	}
	
	public List<RoomListSchedule_aca_58> getAllRooms() {
		
		return jdbcTemplate.query(SQL_GET_ALL_ROOMS, new RoomListScheduleMapper_aca_58()); 
	}
	
	public List<String> getListDate()	{
		 
		return jdbcTemplate.queryForList(SQL_GET_DATE, String.class);
	}
	
	
}
