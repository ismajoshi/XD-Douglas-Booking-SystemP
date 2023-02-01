package com.csis3275.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csis3275.dao.BaseCalendarDAOImpl_rso_35;
import com.csis3275.dao.BaseCalendarUnavailableDaysDAOImpl_rso_35;
import com.csis3275.dao.ReservationDAOImpl_sli_15;
import com.csis3275.dao.RoomAmenityDAOImpl_aca_58;
import com.csis3275.dao.RoomDAOImpl_sli_15;
import com.csis3275.model.BaseCalendarUnavailableDays_rso_35;
import com.csis3275.model.BaseCalendar_rso_35;
import com.csis3275.model.Reservation_sli_15;
import com.csis3275.model.RoomAmenity_aca_58;
import com.csis3275.model.RoomDetailScheduleReportComposite_rso_35;
import com.csis3275.model.Room_sli_15;

/**
 * Component class to implement the business logic regarding the Room DetailSchedule Report Feature

 * The attributes if this class are not stored in the database
 *  
 * @author Ricardo dos Santos Alves de Souza
 *
 */
@Service
public class RoomDetailScheduleReportService_rso_35 {
	
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");	
	
	@Autowired
	RoomDAOImpl_sli_15 roomDAOImplObj;

	@Autowired
	BaseCalendarDAOImpl_rso_35 baseCalendarDAOImplObj;

	@Autowired
	BaseCalendarUnavailableDaysDAOImpl_rso_35 baseCalendarUnavailableDaysDAOImplObj;

	@Autowired
	RoomAmenityDAOImpl_aca_58 roomAmenityDAOImplObj;

	@Autowired
	ReservationDAOImpl_sli_15 reservationDAOImplObj;
	

	/**
	 * Default empty Constructor
	 */
	@Autowired
	public RoomDetailScheduleReportService_rso_35() {
	}
	
	
	/**
	 * Build the RoomDetailScheduleReportComposite Obj regarding the Room Id and Selected Date passed
	 * @param pRoomId selected Room Id
	 * @param pSelectedDate selected Date
	 * @return RoomDetailScheduleReportComposite Obj
	 * @throws Exception Generic exception caught and thrown up
	 */
	public RoomDetailScheduleReportComposite_rso_35 getRoomDetailScheduleReport(int pRoomId, Date pSelectedDate) throws Exception {
		
		try {
			
			//Get only Date part of Select Date
			pSelectedDate = dateFormatter.parse(dateFormatter.format(pSelectedDate));
			
			//Get the selected Room
			Room_sli_15 roomObj = roomDAOImplObj.getRoom(pRoomId);
			
			//Get Base Calendar related to the Room
			BaseCalendar_rso_35 baseCalendarObj = baseCalendarDAOImplObj.getRowById(roomObj.getBaseCalendar());
			
			//Get Base Calendar Unavailable Days on the selected Date
			List<BaseCalendarUnavailableDays_rso_35> baseCalendarUnavailableDaysList = baseCalendarUnavailableDaysDAOImplObj.getRowsByBaseCalendarIdAndDate(baseCalendarObj.getId(), pSelectedDate);
		
			//Get the Room Amenities
			List<RoomAmenity_aca_58> roomAmenitiesList = roomAmenityDAOImplObj.getAmenitiesByRoomId(pRoomId);
			
			//Get Room Reservations on the selected Date
			List<Reservation_sli_15> roomReservationList = reservationDAOImplObj.getReservationsByRoomIdAndDate(pRoomId, pSelectedDate);
					
			//Create the RoomDetailScheduleReportComposite_rso_35
			return new RoomDetailScheduleReportComposite_rso_35(pSelectedDate,
																roomObj,
																baseCalendarObj,
																baseCalendarUnavailableDaysList,
																roomAmenitiesList,
																roomReservationList);
		} catch(Exception ex) {
			throw new Exception("Error running getRoomDetailScheduleReport: " + ex.getMessage());
		}
		
	}

}
