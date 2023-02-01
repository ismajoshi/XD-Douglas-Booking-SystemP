/**
 * 
 */
package com.csis3275.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Scott Linden - 300327715
 *
 */
public class Reservation_sli_15 {
	
	private int reservationID;
	private String title;
	private int ateendees; 
	private String type;
	private Date start;
	private Date end;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the attendees
	 */
	public int getAteendees() {
		return ateendees;
	}
	/**
	 * @param ateendees the ateendees to set
	 */
	public void setAteendees(int ateendees) {
		this.ateendees = ateendees;
	}
	/**
	 * @return the reservationCol
	 */
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the start
	 */
	public Date getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	public void setStart(Date start) {
		this.start = start;
	}
	/**
	 * @return the end
	 */
	public Date getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	public void setEnd(Date end) {
		this.end = end;
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
