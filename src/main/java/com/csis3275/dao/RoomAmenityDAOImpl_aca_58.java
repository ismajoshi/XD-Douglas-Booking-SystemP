package com.csis3275.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model.RoomAmenityMapper_aca_58;
import com.csis3275.model.RoomAmenity_aca_58;

@Component
public class RoomAmenityDAOImpl_aca_58 {

	JdbcTemplate jdbcTemplate;
	//ROOM_AMENITIEID, NAME, SERIAL_NUMBER, MANUFACTURER,	MODEL, DETAILNOTES,	AMENITIE_TYPE, ROOMID

	
	private final String SQL_GET_ROOM_AMENITY = "select ROOM_AMENITIEID, NAME, SERIAL_NUMBER, MANUFACTURER,	MODEL, DETAILNOTES,	AMENITIE_TYPE, ROOMID from Room_amenitie where ROOM_AMENITIEID = ? ";
	private final String SQL_GET_ALL = "select * from room_amenitie";
	private final String SQL_CREATE_ROOM_AMENITY = "INSERT INTO Room_amenitie (NAME, SERIAL_NUMBER, MANUFACTURER, MODEL, DETAILNOTES, AMENITIE_TYPE, ROOMID) values(?,?,?,?,?,?,?)";
	private final String SQL_DELETE_ROOM_AMENITY = "DELETE from Room_amenitie WHERE ROOM_AMENITIEID = ?";
	private final String SQL_UPDATE_ROOM_AMENITY = "UPDATE Room_amenitie set NAME = ?, SERIAL_NUMBER = ?, MANUFACTURER = ?, MODEL = ?, DETAILNOTES = ?, AMENITIE_TYPE = ?, ROOMID = ? where ROOM_AMENITIEID = ? ";
	private final String SQL_GET_BY_ROOMID = "SELECT * FROM Room_amenitie WHERE roomid = ?";	
	

	@Autowired
	public RoomAmenityDAOImpl_aca_58(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<RoomAmenity_aca_58> getAllAmenities() {
		return jdbcTemplate.query(SQL_GET_ALL, new RoomAmenityMapper_aca_58());
	}

	/**
	 * Get all the Amenities associated to a Room
	 * 
	 * @author Ricardo dos Santos Alves de Souza
	 * @param pRoomId id of the desired Room 
	 * @return List of Amenities associated to a Room 
	 */
	public List<RoomAmenity_aca_58> getAmenitiesByRoomId(int pRoomId) {
		
		return jdbcTemplate.query(SQL_GET_BY_ROOMID, new Object[] { pRoomId }, new RoomAmenityMapper_aca_58());
	}
	
	
	public RoomAmenity_aca_58 getRoomAmenityByRoomAmenityId(int roomAmenitieID) {
		return jdbcTemplate.queryForObject(SQL_GET_ROOM_AMENITY, new Object[] { roomAmenitieID }, new RoomAmenityMapper_aca_58());
	}

	public boolean createRoomAmenity(RoomAmenity_aca_58 roomAmenity) {
		return jdbcTemplate.update(SQL_CREATE_ROOM_AMENITY, roomAmenity.getName(), roomAmenity.getSerialNumber(), roomAmenity.getManufacturer(), 
				roomAmenity.getModel(), roomAmenity.getDetailsNotes(), roomAmenity.getAmenitie_type(), roomAmenity.getRoomId()) > 0;
	}

	public boolean deleteRoomAmenity(int roomAmenieID) {
		return jdbcTemplate.update(SQL_DELETE_ROOM_AMENITY, roomAmenieID) > 0;

	}

	public boolean updateRoomAmenity(RoomAmenity_aca_58 roomAmenity) {
		return jdbcTemplate.update(SQL_UPDATE_ROOM_AMENITY, roomAmenity.getName(), roomAmenity.getSerialNumber(), roomAmenity.getManufacturer(),
				roomAmenity.getModel(), roomAmenity.getDetailsNotes(), roomAmenity.getAmenitie_type(), roomAmenity.getRoomId(), roomAmenity.getRoom_amentieId()) > 0;
	}
	
}
