package com.csis3275.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Class to hold the filter fields used in the Room Detail Schedule Report Form.

 * The attributes if this class are not stored in the database
 *  
 * @author Ricardo dos Santos Alves de Souza
 *
 */
public class RoomDetailScheduleReportFilter_rso_35 {
	
	private int roomId;
	private Date selectedDate;
	/**
	 * @return the roomId
	 */
	public int getRoomId() {
		return roomId;
	}
	/**
	 * @param roomId the roomId to set
	 */
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	/**
	 * @return the selectedDate
	 */
	public Date getSelectedDate() {
		return selectedDate;
	}
	/**
	 * @param selectedDate the selectedDate to set
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}
	
	
}
