package com.csis3275.model;

import java.util.Date;

/**
 * Class to hold the data regarding each Room Schedule Time Slot.

 * The attributes if this class are not stored in the database
 *  
 * @author Ricardo dos Santos Alves de Souza
 *
 */
public class RoomScheduleTimeSlot_rso_35 {
	
	private Date timeSlotStartDate;
	private String roomAvailability;
	private String reservationTitle;
	private String reservationType;
	private int reservationAttendees;
	/**
	 * @return the timeSlotStartDate
	 */
	public Date getTimeSlotStartDate() {
		return timeSlotStartDate;
	}
	/**
	 * @param timeSlotStartDate the timeSlotStartDate to set
	 */
	public void setTimeSlotStartDate(Date timeSlotStartDate) {
		this.timeSlotStartDate = timeSlotStartDate;
	}
	/**
	 * @return the roomAvailability
	 */
	public String getRoomAvailability() {
		return roomAvailability;
	}
	/**
	 * @param roomAvailability the roomAvailability to set
	 */
	public void setRoomAvailability(String roomAvailability) {
		this.roomAvailability = roomAvailability;
	}
	/**
	 * @return the reservationTitle
	 */
	public String getReservationTitle() {
		return reservationTitle;
	}
	/**
	 * @param reservationTitle the reservationTitle to set
	 */
	public void setReservationTitle(String reservationTitle) {
		this.reservationTitle = reservationTitle;
	}
	/**
	 * @return the reservationType
	 */
	public String getReservationType() {
		return reservationType;
	}
	/**
	 * @param reservationType the reservationType to set
	 */
	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}
	/**
	 * @return the reservationAttendees
	 */
	public int getReservationAttendees() {
		return reservationAttendees;
	}
	/**
	 * @param reservationAttendees the reservationAttendees to set
	 */
	public void setReservationAttendees(int reservationAttendees) {
		this.reservationAttendees = reservationAttendees;
	}		

	
	
}
