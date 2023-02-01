package com.csis3275.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Class to hold all the other entities related to the Room and to present the Room Schedule Timesheet List.

 * The attributes if this class are not stored in the database
 *  
 * @author Ricardo dos Santos Alves de Souza
 *
 */
public class RoomDetailScheduleReportComposite_rso_35 {
	
	static final long ONE_MINUTE_IN_MILLIS=60000;//milliseconds
	static final String ROOM_AVAILABLE_TEXT = "Available";
	static final String ROOM_UNAVAILABLE_TEXT = "Unavailable";
	static final String BASE_CALENDAR_UNAVAILABLE_TYPE_TEXT = "Base Calendar Restrictions";	
	static final String ROOM_RESERVED_TEXT = "Reserved";
	static final String NOT_APPLIED_TEXT = "n/a";	
	
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
	private SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");		
	
	private Date reportDate;
	private Room_sli_15 roomObj;
	private BaseCalendar_rso_35 baseCalendarObj;
	private List<BaseCalendarUnavailableDays_rso_35> baseCalendarUnavailableDaysList;
	private List<RoomAmenity_aca_58> roomAmenitiesList;
	private List<Reservation_sli_15> roomReservationList;
	
	private List<RoomScheduleTimeSlot_rso_35> roomScheduleTimeSlotList;
	
	private int timeSlotSize; //Minutes
	
	/**
	 * Default empty Constructor
	 */
	public RoomDetailScheduleReportComposite_rso_35() {
		
		reportDate = null;
		roomObj = new Room_sli_15();
		baseCalendarObj = new BaseCalendar_rso_35();
		baseCalendarUnavailableDaysList = new ArrayList<BaseCalendarUnavailableDays_rso_35>();
		roomAmenitiesList = new ArrayList<RoomAmenity_aca_58>();
		roomReservationList = new ArrayList<Reservation_sli_15>();

		roomScheduleTimeSlotList = new ArrayList<RoomScheduleTimeSlot_rso_35>();
		timeSlotSize = 30;
	}

	/**
	 * Complete constructor 
	 * @param pReportDate Desired date to generate the report
	 * @param pRoomObj Select Room Obj
	 * @param pBaseCalendarObj Related BaseCalendar Obj
	 * @param pBaseCalendarUnavailableDaysList List of BaseCalendarUnavailableDays related to the Base Calendar on the selected date
	 * @param pRoomAmenitiesList List of the Room Amenities
	 * @param pRoomReservationList List of the Room Reservations on the selected date
	 * @throws Exception Generic exception caught and thrown up
	 */
	public RoomDetailScheduleReportComposite_rso_35(Date pReportDate,
											Room_sli_15 pRoomObj,
											BaseCalendar_rso_35 pBaseCalendarObj,
											List<BaseCalendarUnavailableDays_rso_35> pBaseCalendarUnavailableDaysList,
											List<RoomAmenity_aca_58> pRoomAmenitiesList,
											List<Reservation_sli_15> pRoomReservationList) throws Exception {
		
		reportDate = pReportDate;
		roomObj = pRoomObj;
		baseCalendarObj = pBaseCalendarObj;
		baseCalendarUnavailableDaysList = pBaseCalendarUnavailableDaysList;
		roomAmenitiesList = pRoomAmenitiesList;
		roomReservationList = pRoomReservationList;
		
		roomScheduleTimeSlotList = new ArrayList<RoomScheduleTimeSlot_rso_35>();
		timeSlotSize = 30;
		
		//Compute TimeSlot List
		this.computeTimeSlotList();
	}
	
	
	// ### Logic Methods
	
	/**
	 * Based on the retrieved attributes, compute the Room Schedule Time Slot List
	 */
	private void computeTimeSlotList() throws Exception {
		
		Date startTime; //Start Time Slot
		Date endTime; //Start Time Slot
		Date currentTimeSlot;

		//Check Day of Week and get Start and End Schedule time based on the Room Base Calendar
		if(isSelectedDayWeekend()) {
			
			//Mount Schedule Start time - Report Date + Base Calendar Weekend Start time
			startTime = datetimeFormatter.parse(dateFormatter.format(reportDate)
					+ " "
					+ timeFormatter.format(baseCalendarObj.getWeekenddaysStartTime()));
			
			//Mount Schedule Start time - Report Date + Base Calendar Weekend Start time
			endTime = datetimeFormatter.parse(dateFormatter.format(reportDate)
					+ " "
					+ timeFormatter.format(baseCalendarObj.getWeekenddaysEndTime()));
		}
		else {

			//Mount Schedule Start time - Report Date + Base Calendar Weekday Start time			
			startTime = datetimeFormatter.parse(dateFormatter.format(reportDate)
																		+ " "
																		+ timeFormatter.format(baseCalendarObj.getWeekdaysStartTime()));
			
			//Mount Schedule Start time - Report Date + Base Calendar Weekday Start time			
			endTime = datetimeFormatter.parse(dateFormatter.format(reportDate)
																		+ " "
																		+ timeFormatter.format(baseCalendarObj.getWeekdaysEndTime()));
		}
			
		//Set currentTimeSlot to the start time
		currentTimeSlot = startTime;
		
		//Loop through the Time slots and set the availability or reservation info		
		while(currentTimeSlot.before(endTime)) {
			
			//Create a new timeSlot
			RoomScheduleTimeSlot_rso_35 newTimeSlotItem = new RoomScheduleTimeSlot_rso_35();
			
			//Check Unavailable Days
			BaseCalendarUnavailableDays_rso_35 currentSlotUnavailableDayObj = getTimeSlotBaseCalendarRestriction(currentTimeSlot);
			if(currentSlotUnavailableDayObj != null) {

				//Update time slot info if restrictions data 
				newTimeSlotItem.setTimeSlotStartDate(currentTimeSlot);
				newTimeSlotItem.setRoomAvailability(ROOM_UNAVAILABLE_TEXT);
				newTimeSlotItem.setReservationTitle(currentSlotUnavailableDayObj.getName());
				newTimeSlotItem.setReservationType(BASE_CALENDAR_UNAVAILABLE_TYPE_TEXT);
				newTimeSlotItem.setReservationAttendees(0);
				
				//Add timeSlot to the List
				roomScheduleTimeSlotList.add(newTimeSlotItem);
				
				//Increment Current Time
				currentTimeSlot = incrementTimeSlot(currentTimeSlot);
				
				//Go to next time slot
				continue;
			}
			
			//Check Reservations
			Reservation_sli_15 currentSlotReservationObj = getTimeSlotReservation(currentTimeSlot);
			if(currentSlotReservationObj != null) {

				//Update time slot info if restrictions data 
				newTimeSlotItem.setTimeSlotStartDate(currentTimeSlot);
				newTimeSlotItem.setRoomAvailability(ROOM_RESERVED_TEXT);
				newTimeSlotItem.setReservationTitle(currentSlotReservationObj.getTitle());
				newTimeSlotItem.setReservationType(currentSlotReservationObj.getType());
				newTimeSlotItem.setReservationAttendees(currentSlotReservationObj.getAteendees());
				
				//Add timeSlot to the List
				roomScheduleTimeSlotList.add(newTimeSlotItem);
				
				//Increment Current Time
				currentTimeSlot = incrementTimeSlot(currentTimeSlot);
				
				//Go to next time slot
				continue;
			}			
			
			//Time Slot Available - Set availability info
			newTimeSlotItem.setTimeSlotStartDate(currentTimeSlot);
			newTimeSlotItem.setRoomAvailability(ROOM_AVAILABLE_TEXT);
			newTimeSlotItem.setReservationTitle(NOT_APPLIED_TEXT);
			newTimeSlotItem.setReservationType(NOT_APPLIED_TEXT);
			newTimeSlotItem.setReservationAttendees(0);
			
			//Add timeSlot to the List
			roomScheduleTimeSlotList.add(newTimeSlotItem);
			
			//Increment Current Time
			currentTimeSlot = incrementTimeSlot(currentTimeSlot);
		}
		
	}
	
	/**
	 * Check if the passed Room Schedule time slot has a Base Calendar Unavailable Day restriction. If yes, return the Unavailable Day obj
	 * @param pTimeSlot time slot to check
	 * @return Unavailable Day Obj or Null if not reserved
	 */
	private BaseCalendarUnavailableDays_rso_35 getTimeSlotBaseCalendarRestriction(Date pTimeSlot) throws Exception {
		
		//Loop Unavailable Days List and check if the time slot in include in some restricted day
		for (BaseCalendarUnavailableDays_rso_35 unavailableDayItem : this.baseCalendarUnavailableDaysList) {
			
			if( (pTimeSlot.compareTo(unavailableDayItem.getFullUnavailableStartDateTime()) >= 0) 
					&& (pTimeSlot.compareTo(unavailableDayItem.getFullUnavailableEndDateTime()) < 0) ) {
			
				return unavailableDayItem;
			}
		}
		
		//If not found, return null
		return null;
	}	
	
	/**
	 * Check if the passed Room Schedule time slot has a Reservation. If yes, return the Reservation obj
	 * @param pTimeSlot time slot to check
	 * @return Reservation Obj or Null if not reserved
	 */
	private Reservation_sli_15 getTimeSlotReservation(Date pTimeSlot) {
		
		//Loop Reservation List and check if the time slot in include in some reservation
		for (Reservation_sli_15 reservationItem : this.roomReservationList) {
			
			if( (pTimeSlot.compareTo(reservationItem.getStart()) >= 0) 
					&& (pTimeSlot.compareTo(reservationItem.getEnd()) < 0) ) {
				
				return reservationItem;
			}
		}
		
		//If not found, return null
		return null;
	}
	
	/**
	 * Check if the report selected day is weekend or weekday
	 * @return true if Weekend
	 */
	private boolean isSelectedDayWeekend() {
		
		//Get a LocalCalendar Obj
		Calendar calendarObj = Calendar.getInstance();
		
		//Set calendarObj to Report Date
		calendarObj.setTime(this.reportDate);
		
		if(calendarObj.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendarObj.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	/**
	 * Increment current time slot considering the defined Time Slot Size in minutes
	 * @param pCurrentTimeSlot
	 * @return next time slot
	 */
	private Date incrementTimeSlot(Date pCurrentTimeSlot) {
		
		//Get a LocalCalendar Obj
		Calendar calendarObj = Calendar.getInstance();
		
		//Increment Current Time
		calendarObj.setTime(pCurrentTimeSlot);
		calendarObj.add(Calendar.MINUTE, this.timeSlotSize);
		return calendarObj.getTime();			
	}


	// ### Getters/Setters Methods #####################
	
	/**
	 * @return the reportDate
	 */
	public Date getReportDate() {
		return reportDate;
	}

	/**
	 * @param reportDate the reportDate to set
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")		
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;

	}

	/**
	 * @return the roomObj
	 */
	public Room_sli_15 getRoomObj() {
		return roomObj;
	}

	/**
	 * @param roomObj the roomObj to set
	 */
	public void setRoomObj(Room_sli_15 roomObj) {
		this.roomObj = roomObj;
	}

	/**
	 * @return the baseCalendarObj
	 */
	public BaseCalendar_rso_35 getBaseCalendarObj() {
		return baseCalendarObj;
	}

	/**
	 * @param baseCalendarObj the baseCalendarObj to set
	 */
	public void setBaseCalendarObj(BaseCalendar_rso_35 baseCalendarObj) {
		this.baseCalendarObj = baseCalendarObj;
	}

	/**
	 * @return the baseCalendarUnavailableDaysList
	 */
	public List<BaseCalendarUnavailableDays_rso_35> getBaseCalendarUnavailableDaysList() {
		return baseCalendarUnavailableDaysList;
	}

	/**
	 * @param baseCalendarUnavailableDaysList the baseCalendarUnavailableDaysList to set
	 */
	public void setBaseCalendarUnavailableDaysList(
			List<BaseCalendarUnavailableDays_rso_35> baseCalendarUnavailableDaysList) {
		this.baseCalendarUnavailableDaysList = baseCalendarUnavailableDaysList;
	}

	/**
	 * @return the roomAmenitiesList
	 */
	public List<RoomAmenity_aca_58> getRoomAmenitiesList() {
		return roomAmenitiesList;
	}

	/**
	 * @param roomAmenitiesList the roomAmenitiesList to set
	 */
	public void setRoomAmenitiesList(List<RoomAmenity_aca_58> roomAmenitiesList) {
		this.roomAmenitiesList = roomAmenitiesList;
	}

	/**
	 * @return the roomReservationList
	 */
	public List<Reservation_sli_15> getRoomReservationList() {
		return roomReservationList;
	}

	/**
	 * @param roomReservationList the roomReservationList to set
	 */
	public void setRoomReservationList(List<Reservation_sli_15> roomReservationList) {
		this.roomReservationList = roomReservationList;
	}

	/**
	 * @return the roomScheduleTimeSlotList
	 */
	public List<RoomScheduleTimeSlot_rso_35> getRoomScheduleTimeSlotList() {
		return roomScheduleTimeSlotList;
	}

	/**
	 * @param roomScheduleTimeSlotList the roomScheduleTimeSlotList to set
	 */
	public void setRoomScheduleTimeSlotList(List<RoomScheduleTimeSlot_rso_35> roomScheduleTimeSlotList) {
		this.roomScheduleTimeSlotList = roomScheduleTimeSlotList;
	}

	/**
	 * @return the timeSlotSize
	 */
	public int getTimeSlotSize() {
		return timeSlotSize;
	}

	/**
	 * @param timeSlotSize the timeSlotSize to set
	 */
	public void setTimeSlotSize(int timeSlotSize) {
		this.timeSlotSize = timeSlotSize;
	}

}
