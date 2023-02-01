package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RoomAmenityMapper_aca_58 implements RowMapper<RoomAmenity_aca_58> {

	public RoomAmenity_aca_58 mapRow(ResultSet resultSet, int i) throws SQLException {

		RoomAmenity_aca_58 roomAmenities = new RoomAmenity_aca_58();
		
		//ROOM_AMENITIEID, NAME, SERIAL_NUMBER, MANUFACTURER, MODEL, DETAILNOTES, AMENITIE_TYPE, ROOMID
		
		roomAmenities.setRoom_amentieId(resultSet.getInt("room_amenitieId"));
		roomAmenities.setName(resultSet.getString("name"));
		roomAmenities.setSerialNumber(resultSet.getString("serial_Number"));
		roomAmenities.setManufacturer(resultSet.getString("manufacturer"));
		roomAmenities.setModel(resultSet.getString("model"));
		roomAmenities.setDetailsNotes(resultSet.getString("detailNotes"));
		roomAmenities.setAmenitie_type(resultSet.getString("amenitie_type"));
		roomAmenities.setRoomId(resultSet.getInt("roomId"));
		
		return roomAmenities;
	}
}