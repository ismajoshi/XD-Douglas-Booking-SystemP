package com.csis3275.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class RoomListSchedule_aca_58 {
	
	private int reservationID; 
	private String reservation_title;
	private String expected_attendees;
	private String reservation_type;
	private Date reservation_dateTime; 
	private Date reservation_end_DateTimeDate; 
	private String status;
	private int userID;
	private int roomID;
	
	
	/**
	 * @return the reservationID
	 */
	public int getReservationID() {
		return reservationID;
	}
	/**
	 * @param reservationID the reservationID to set
	 */
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	} 
	
	/**
	 * @return the reservation_title
	 */
	public String getReservation_title() {
		return reservation_title;
	}
	/**
	 * @param reservation_title the reservation_title to set
	 */
	public void setReservation_title(String reservation_title) {
		this.reservation_title = reservation_title;
	}
	/**
	 * @return the expected_attendees
	 */
	public String getExpected_attendees() {
		return expected_attendees;
	}
	/**
	 * @param expected_attendees the expected_attendees to set
	 */
	public void setExpected_attendees(String expected_attendees) {
		this.expected_attendees = expected_attendees;
	}
	
	/**
	 * @return the reservation_type
	 */
	public String getReservation_type() {
		return reservation_type;
	}
	/**
	 * @param reservation_type the reservation_type to set
	 */
	public void setReservation_type(String reservation_type) {
		this.reservation_type = reservation_type;
	}
	/**
	 * @return the reservation_start_dateTimeDate
	 */
	
	public Date getReservation_dateTime() {
		return reservation_dateTime;
	}
	/**
	 * @param reservation_start_dateTimeDate the reservation_start_dateTimeDate to set
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
		
	public void setReservation_dateTimeDate(Date reservation_start_dateTimeDate) {
		this.reservation_dateTime = reservation_start_dateTimeDate;
	}
	/**
	 * @return the reservation_end_DateTimeDate
	 */
	public Date getReservation_end_DateTimeDate() {
		return reservation_end_DateTimeDate;
	}
	/**
	 * @param reservation_end_DateTimeDate the reservation_end_DateTimeDate to set
	 */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	public void setReservation_end_DateTimeDate(Date reservation_end_DateTimeDate) {
		this.reservation_end_DateTimeDate = reservation_end_DateTimeDate;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	/**
	 * @return the roomID
	 */
	public int getRoomID() {
		return roomID;
	}
	/**
	 * @param roomID the roomID to set
	 */
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	
	
	
	
	
	
	
	
	
}
