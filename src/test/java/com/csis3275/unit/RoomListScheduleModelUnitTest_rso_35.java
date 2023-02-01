package com.csis3275.unit;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import com.csis3275.model.RoomListSchedule_aca_58;

/**
 * Class to implement Unit tests for the feature Room Schedule model using JUnit
 * This model class does not have complex computed methods, only basic getter/setter methods.
 * 
 * @author Ricardo dos Santos Alves de Souza
 *
 */
public class RoomListScheduleModelUnitTest_rso_35 {

	RoomListSchedule_aca_58 roomListScheduleObj;
	
	SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Before
	public void setUp()throws Exception{
		
		roomListScheduleObj = new RoomListSchedule_aca_58();
		
		roomListScheduleObj.setReservationID(1);
		roomListScheduleObj.setReservation_title("Room Reservation Mock Test");
		roomListScheduleObj.setExpected_attendees("10");
		roomListScheduleObj.setReservation_type("Class");
		roomListScheduleObj.setReservation_dateTimeDate(datetimeFormatter.parse("2020-11-11 08:00"));
		roomListScheduleObj.setReservation_end_DateTimeDate(datetimeFormatter.parse("2020-11-11 09:00"));
		roomListScheduleObj.setStatus("Active");
		roomListScheduleObj.setUserID(1);
		roomListScheduleObj.setRoomID(1);
	}
	
	//Testing the getters and setters of the model

	@Test
	public void test_ReservationID_Attribute() {
		assertEquals(1, roomListScheduleObj.getReservationID());
	}
	
	@Test
	public void test_Reservation_title_Attribute() {
		assertEquals("Room Reservation Mock Test", roomListScheduleObj.getReservation_title());
	}
	
	@Test
	public void test_Expected_attendees_Attribute() {
		assertEquals("10", roomListScheduleObj.getExpected_attendees());
	}	

	@Test
	public void test_Reservation_type_Attribute() {
		assertEquals("Class", roomListScheduleObj.getReservation_type());

	}		

	@Test
	public void test_Reservation_dateTime_Attribute() {
		assertEquals("2020-11-11 08:00", datetimeFormatter.format(roomListScheduleObj.getReservation_dateTime()));

	}		
	
	@Test
	public void test_Reservation_end_DateTimeDate_Attribute() {
		assertEquals("2020-11-11 09:00", datetimeFormatter.format(roomListScheduleObj.getReservation_end_DateTimeDate()));

	}	

	@Test
	public void test_Status_Attribute() {
		assertEquals("Active", roomListScheduleObj.getStatus());

	}		

	@Test
	public void test_UserID_Attribute() {
		assertEquals(1, roomListScheduleObj.getUserID());

	}		
	
	@Test
	public void test_RoomID_Attribute() {
		assertEquals(1, roomListScheduleObj.getRoomID());

	}	

}
