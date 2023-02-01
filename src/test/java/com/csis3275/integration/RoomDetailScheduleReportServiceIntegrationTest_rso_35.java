package com.csis3275.integration;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.csis3275.XD_DBS_Application;
import com.csis3275.controller.RoomDetailScheduleReportService_rso_35;
import com.csis3275.model.BaseCalendarUnavailableDays_rso_35;
import com.csis3275.model.Reservation_sli_15;
import com.csis3275.model.RoomAmenity_aca_58;
import com.csis3275.model.RoomDetailScheduleReportComposite_rso_35;

/**
 * Class to implement integrated test regarding RoomDetailSchedule Report and all the related other classes.
 * 
 * According to the definition provided for Iteration#2, this test may be consider a System Test as well due to involving DAO classes and database CRUD operations.

 * @author Ricardo dos Santos Alves de Souza
 *
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoomDetailScheduleReportServiceIntegrationTest_rso_35 {
	
	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	
	//Set Service class instance
	@Autowired
	RoomDetailScheduleReportService_rso_35 roomDetailScheduleReportServiceObj;
	

	/**
	 * Set a room and date then create a RoomDetailScheduleReportComposite_rso_35 class instance to check if the integrated functions are 
	 * correcting retrieving all the information regarding the selected room on the selected date, including Reservations, Base Calendar, and Unavailable Days
	 * @throws Exception Generic exception caught and thrown up
	 */
	@Test
	public void testRoomDetailScheduleReportCompositeCreation() throws Exception {
		
		//Set date to test 
		Date selectedDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-13");

		//Set room to test 
		int roomId = 2;
	
		//Get the RoomDetailScheduleReportComposite Obj
		RoomDetailScheduleReportComposite_rso_35 roomDetailScheduleReportCompositeObj = roomDetailScheduleReportServiceObj.getRoomDetailScheduleReport(roomId, selectedDate);
		
		assertEquals("RoomId should be " + roomId, roomId, roomDetailScheduleReportCompositeObj.getRoomObj().getRoomID());
		
		//Date
		System.out.println("Report Date: " + roomDetailScheduleReportCompositeObj.getReportDate());
		
		//Room Details
		System.out.println("Room Number: " + roomDetailScheduleReportCompositeObj.getRoomObj().getNumber());
		System.out.println("Room Building: " + roomDetailScheduleReportCompositeObj.getRoomObj().getBuilding());
		
		//Base Calendar
		System.out.println("Base Calendar Name: " + roomDetailScheduleReportCompositeObj.getBaseCalendarObj().getName());
		System.out.println("Base Calendar WeekdaysStartTime: " + roomDetailScheduleReportCompositeObj.getBaseCalendarObj().getWeekdaysStartTime());
		System.out.println("Base Calendar WeekdaysEndTime: " + roomDetailScheduleReportCompositeObj.getBaseCalendarObj().getWeekdaysEndTime());
		
		//Unavailable Days
		for(BaseCalendarUnavailableDays_rso_35 unavailableDayItem : roomDetailScheduleReportCompositeObj.getBaseCalendarUnavailableDaysList()) {
			System.out.println("Unavailable Day Name: " + unavailableDayItem.getName());
			System.out.println("Unavailable Day Date: " + unavailableDayItem.getDate());
			System.out.println("Unavailable Day Start Time: " + unavailableDayItem.getUnavailableStartTime());
			System.out.println("Unavailable Day End Time: " + unavailableDayItem.getUnavailableEndTime());				
		}

		//Room Amenities
		for(RoomAmenity_aca_58 amenityItem : roomDetailScheduleReportCompositeObj.getRoomAmenitiesList()) {
			System.out.println("Room Amenity Name: " + amenityItem.getName());	
			System.out.println("Room Amenity Type: " + amenityItem.getAmenitie_type());				
		}
		
		//Room Reservation
		for(Reservation_sli_15 reservationItem : roomDetailScheduleReportCompositeObj.getRoomReservationList()) {
			System.out.println("Room Reservation Title: " + reservationItem.getTitle());	
			System.out.println("Room Room Start: " + reservationItem.getStart());
			System.out.println("Room Room End: " + reservationItem.getEnd());				
		}		
		
	}

}
