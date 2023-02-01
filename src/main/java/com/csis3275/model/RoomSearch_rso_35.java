package com.csis3275.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Class to hold the fields and methods used to searching for Rooms.
 * The attributes if this class are not stored in the database
 *  
 * @author Ricardo dos Santos Alves de Souza
 *
 */
public class RoomSearch_rso_35 {

	//Room attributes
	private String number;
	private String floor;
	private String building;
	private int capacity;
	private String roomType;
	
	//Room Amenities
	private List<String> amenitie_typeList;
	
	//Availability
	private Date desiredStartDatetime;
	private Date desiredEndDatetime;
	private boolean onlyAvailable; 
	
	/**
	 * Default Constructor
	 */
	public RoomSearch_rso_35() {

		number = "";
		floor = "";
		building = "";
		capacity = 1;
		roomType = "";
		amenitie_typeList = null;
		desiredStartDatetime = null;
		desiredEndDatetime = null;
	
	}
	
	/**
	 * Constructor filling Amenity Type List
	 * 
	 * @param pAmenity_typeList Minimum list of amenities that the rooms should have 
	 */
	public RoomSearch_rso_35(List<String> pAmenity_typeList) {
		
		RoomSearch_rso_35 newRoomSearchObj = new RoomSearch_rso_35();
		
		newRoomSearchObj.amenitie_typeList = pAmenity_typeList;
	}
	
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * @return the floor
	 */
	public String getFloor() {
		return floor;
	}
	/**
	 * @param floor the floor to set
	 */
	public void setFloor(String floor) {
		this.floor = floor;
	}
	/**
	 * @return the building
	 */
	public String getBuilding() {
		return building;
	}
	/**
	 * @param building the building to set
	 */
	public void setBuilding(String building) {
		this.building = building;
	}
	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	/**
	 * @return the roomType
	 */
	public String getRoomType() {
		return roomType;
	}
	/**
	 * @param roomType the roomType to set
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	/**
	 * @return the amenitie_typeList
	 */
	public List<String> getAmenitie_typeList() {
		return amenitie_typeList;
	}
	/**
	 * @param amenitie_typeList the amenitie_typeList to set
	 */
	public void setAmenitie_typeList(List<String> amenitie_typeList) {
		this.amenitie_typeList = amenitie_typeList;
	}

	/**
	 * @return the desiredStartDatetime
	 */
	public Date getDesiredStartDatetime() {
		return desiredStartDatetime;
	}

	/**
	 * @param desiredStartDatetime the desiredStartDatetime to set
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	public void setDesiredStartDatetime(Date desiredStartDatetime) {
		this.desiredStartDatetime = desiredStartDatetime;
	}

	/**
	 * @return the desiredEndDatetime
	 */
	public Date getDesiredEndDatetime() {
		return desiredEndDatetime;
	}

	/**
	 * @param desiredEndDatetime the desiredEndDatetime to set
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")	
	public void setDesiredEndDatetime(Date desiredEndDatetime) {
		this.desiredEndDatetime = desiredEndDatetime;
	}

	/**
	 * @return the onlyAvailable
	 */
	public boolean isOnlyAvailable() {
		return onlyAvailable;
	}

	/**
	 * @param onlyAvailable the onlyAvailable to set
	 */
	public void setOnlyAvailable(boolean onlyAvailable) {
		this.onlyAvailable = onlyAvailable;
	}

}
