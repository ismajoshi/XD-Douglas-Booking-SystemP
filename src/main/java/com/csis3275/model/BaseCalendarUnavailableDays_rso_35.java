package com.csis3275.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Class to represent Base Calendar Unavailable Days entity model
 * 
 * @author Ricardo dos Santos Alves de Souza
 *
 */
public class BaseCalendarUnavailableDays_rso_35 {
	
	private int id;
	private int baseCalendarId;
	private Date date;
	private Date unavailableStartTime;
	private Date unavailableEndTime;
	private String name;
	private String notes;
	
	/**
	 * Default empty constructor
	 */
	public BaseCalendarUnavailableDays_rso_35() {
		
		try {
		
			id = 0;
			baseCalendarId = 0;
			date = new Date(System.currentTimeMillis());
			unavailableStartTime = new SimpleDateFormat("HH:mm").parse("00:00");
			unavailableEndTime = new SimpleDateFormat("HH:mm").parse("23:59");
			name = "";
			notes = "";
		
		} catch(Exception ex) {
			
		}
	}
	
	/**
	 * Constructor setting baseCalendarId FK
	 * @param pBaseCalendarId related BaseCalendar class Id
	 */
	public BaseCalendarUnavailableDays_rso_35(int pBaseCalendarId) {
		
		try {		
		
			id = 0;
			baseCalendarId = pBaseCalendarId;
			date = new Date(System.currentTimeMillis());
			unavailableStartTime = new SimpleDateFormat("HH:mm").parse("00:00");
			unavailableEndTime = new SimpleDateFormat("HH:mm").parse("23:59");
			name = "";
			notes = "";
		
		} catch(Exception ex) {
			
		}		
	}
	
	//#### Computed Get Methods #######

	/**
	 * Get the full datetime format of the Start Time 
	 * @return full StartDate datetime format
	 * @throws ParseException in case of date/time parse exception
	 */
	public Date getFullUnavailableStartDateTime() throws ParseException {
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
		SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");		
		
		return  datetimeFormatter.parse(dateFormatter.format(this.date)
				+ " "
				+ timeFormatter.format(this.unavailableStartTime));
	}
	
	/**
	 * Get the full datetime format of the End Time 
	 * @return full EndDate datetime format
	 * @throws ParseException in case of date/time parse exception
	 */
	public Date getFullUnavailableEndDateTime() throws ParseException {
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
		SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");		
		
		return  datetimeFormatter.parse(dateFormatter.format(this.date)
				+ " "
				+ timeFormatter.format(this.unavailableEndTime));
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
	 * @return the baseCalendarId
	 */
	public int getBaseCalendarId() {
		return baseCalendarId;
	}
	/**
	 * @param baseCalendarId the baseCalendarId to set
	 */
	public void setBaseCalendarId(int baseCalendarId) {
		this.baseCalendarId = baseCalendarId;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")		
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the unavailableStartTime
	 */
	public Date getUnavailableStartTime() {
		return unavailableStartTime;
	}
	/**
	 * @param unavailableStartTime the unavailableStartTime to set
	 */
	@DateTimeFormat(pattern = "HH:mm")		
	public void setUnavailableStartTime(Date unavailableStartTime) {
		this.unavailableStartTime = unavailableStartTime;
	}
	/**
	 * @return the unavailableEndTime
	 */
	public Date getUnavailableEndTime() {
		return unavailableEndTime;
	}
	/**
	 * @param unavailableEndTime the unavailableEndTime to set
	 */
	@DateTimeFormat(pattern = "HH:mm")			
	public void setUnavailableEndTime(Date unavailableEndTime) {
		this.unavailableEndTime = unavailableEndTime;
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
