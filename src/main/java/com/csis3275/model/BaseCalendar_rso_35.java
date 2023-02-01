package com.csis3275.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Class to represent Base Calendar entity model
 * 
 * @author Ricardo dos Santos Alves de Souza
 *
 */
public class BaseCalendar_rso_35 {
	
	private int id;
	private String name;
	private Date weekdaysStartTime;
	private Date weekdaysEndTime;
	private Date weekenddaysStartTime;
	private Date weekenddaysEndTime;
	private String notes;
	
	public BaseCalendar_rso_35() {

		try {
			id = 0;
			name = "";
			weekdaysStartTime = new SimpleDateFormat("HH:mm").parse("06:00");
			weekdaysEndTime = new SimpleDateFormat("HH:mm").parse("18:00");
			weekenddaysStartTime = new SimpleDateFormat("HH:mm").parse("09:00");
			weekenddaysEndTime = new SimpleDateFormat("HH:mm").parse("14:00");
			notes = "";
		} catch(Exception ex) {
			
		}
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the weekdaysStartTime
	 */
	public Date getWeekdaysStartTime() {
		return weekdaysStartTime;
	}

	/**
	 * @param weekdaysStartTime the weekdaysStartTime to set
	 */
	@DateTimeFormat(pattern = "HH:mm")		
	public void setWeekdaysStartTime(Date weekdaysStartTime) {
		this.weekdaysStartTime = weekdaysStartTime;
	}

	/**
	 * @return the weekdaysEndTime
	 */
	public Date getWeekdaysEndTime() {
		return weekdaysEndTime;
	}

	/**
	 * @param weekdaysEndTime the weekdaysEndTime to set
	 */
	@DateTimeFormat(pattern = "HH:mm")		
	public void setWeekdaysEndTime(Date weekdaysEndTime) {
		this.weekdaysEndTime = weekdaysEndTime;
	}

	/**
	 * @return the weekenddaysStartTime
	 */
	public Date getWeekenddaysStartTime() {
		return weekenddaysStartTime;
	}

	/**
	 * @param weekenddaysStartTime the weekenddaysStartTime to set
	 */
	@DateTimeFormat(pattern = "HH:mm")		
	public void setWeekenddaysStartTime(Date weekenddaysStartTime) {
		this.weekenddaysStartTime = weekenddaysStartTime;
	}

	/**
	 * @return the weekenddaysEndTime
	 */
	public Date getWeekenddaysEndTime() {
		return weekenddaysEndTime;
	}

	/**
	 * @param weekenddaysEndTime the weekenddaysEndTime to set
	 */
	@DateTimeFormat(pattern = "HH:mm")		
	public void setWeekenddaysEndTime(Date weekenddaysEndTime) {
		this.weekenddaysEndTime = weekenddaysEndTime;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	

}
