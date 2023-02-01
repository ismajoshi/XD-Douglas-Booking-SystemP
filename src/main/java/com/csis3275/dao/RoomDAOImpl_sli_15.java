package com.csis3275.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model.RoomMapper_sli_15;
import com.csis3275.model.Room_sli_15;

/**
 * @author Scott Linden 300327715
 * This is the Room DAO Implementation.
 *
 */
@Component
public class RoomDAOImpl_sli_15 {

	JdbcTemplate jdbcTemplate;
	
	private final String SQL_GET_ALL = "SELECT * FROM ROOM ORDER BY NUMBER ASC, FLOOR ASC, BUILDING ASC;";
	private final String SQL_INSERT_ONE = "INSERT INTO ROOM ( NUMBER, FLOOR, BUILDING, "
			+ "CAPACITY, ROOM_TYPE, BASE_CALENDARID) VALUES(?,?,?,?,?,?);";
	
	private final String SQL_DELETE_ONE = "DELETE FROM ROOM WHERE ROOMID = ?;";	
	private final String SQL_GET_ONE = "SELECT * FROM ROOM WHERE ROOMID =?;";
	private final String SQL_GET_LAST_ONE = "SELECT * FROM ROOM ORDER BY roomID desc LIMIT 1;";
	private final String SQL_UPDATE_ROOM = "UPDATE ROOM SET number = ?, floor = ?, building = ?, capacity = ?, room_type = ?, base_calendarid = ? WHERE ROOMID =?";
	
	private final String SQL_GET_ONE_BY_LOCATION = "SELECT * FROM ROOM WHERE number = ? AND floor = ? AND building = ? LIMIT 1;";
	
	
	@Autowired
	public RoomDAOImpl_sli_15(DataSource dataSource){
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * Creates a list of all room objects in the database
	 * @return all of the rooms
	 */
	public List<Room_sli_15> getAllRooms(){
		return jdbcTemplate.query(SQL_GET_ALL, new RoomMapper_sli_15());
	}
	
	/**
	 * @param id Object ID to be retrieved 
	 * @return a room from ID
	 */
	public Room_sli_15 getRoom(int id) {
		return (Room_sli_15) jdbcTemplate.queryForObject(SQL_GET_ONE, new RoomMapper_sli_15(), id);
	}
	
	/**
	 * @param newRoom new object passed by View to be created
	 * @return a boolean to indicate if a room has been created
	 */
	public boolean createNewRoom(Room_sli_15 newRoom) {
		return (jdbcTemplate.update(SQL_INSERT_ONE, newRoom.getNumber(), newRoom.getFloor(),
				newRoom.getBuilding(), newRoom.getCapacity(), newRoom.getRoomType(), newRoom.getBaseCalendar()) > 0);
	}
	
	/**
	 * @param id Object ID to be deleted
	 * @return a boolean to indicate if a room has been deleted
	 */
	public boolean deleteRoom(int id) {
		
		return (jdbcTemplate.update(SQL_DELETE_ONE, id) > 0); 
		
	}

	/**
	 * @param room object passed by View been updated
	 * @return boolean to indicate if a room has been updated
	 */
	public boolean updateRoom(Room_sli_15 room) {
		return (jdbcTemplate.update(SQL_UPDATE_ROOM, room.getNumber(), room.getFloor(),
				room.getBuilding(), room.getCapacity(), room.getRoomType(), room.getBaseCalendar(), room.getRoomID())> 0);
	}
	
	/**
	 * @return returns the last made room in the list. 
	 */
	public Room_sli_15 getLastRoom() {
		return (Room_sli_15) jdbcTemplate.queryForObject(SQL_GET_LAST_ONE,  new RoomMapper_sli_15());
	}
	
	/**
	 * Check if there is another room using the same location attributes (number, floor, building)
	 * 
	 * @param pRoom room to check location
	 * @return returns the last made room in the list. 
	 */
	public boolean hasRoomWithSameLocation(Room_sli_15 pRoom) {
		
		Room_sli_15 foundRoom = null;
		
		//Get Room by Location
		try {
			
			foundRoom = (Room_sli_15) jdbcTemplate.queryForObject(SQL_GET_ONE_BY_LOCATION, new Object[]{pRoom.getNumber(), pRoom.getFloor(), pRoom.getBuilding()}, new RoomMapper_sli_15());
			
		} catch(EmptyResultDataAccessException ex) {
			return false;			
		}
		
		//Check if it is a different room
		if( (foundRoom != null) && (foundRoom.getRoomID() != pRoom.getRoomID()) )
		{
			return true;
			
		} else {
			return false;
		}
		
	}
	
}
