package com.csis3275.unit;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import com.csis3275.model.Reservation_sli_15;
/**
 * 
 * @author Scott Linden 
 * 
 * Junit Test for the Reservation Model
 *
 */
public class ReservationModelUnitTest_sli_15 {

	Reservation_sli_15 reservation;
	
	SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Before
	public void setUp()throws Exception{
		
		reservation = new Reservation_sli_15();
		
		reservation.setReservationID(1);
		reservation.setTitle("test");
		reservation.setAteendees(20);
		reservation.setType("class");
		reservation.setStart(datetimeFormatter.parse("2020-11-11 08:00"));
		reservation.setEnd(datetimeFormatter.parse("2020-11-11 09:00"));
		reservation.setStatus("Active");
		reservation.setUserID(1);
		reservation.setRoomID(1);
	}
	
	//Testing the getters and setters of the model
	@Test
	public void testReservationGetSet() {
		assertEquals(1, reservation.getReservationID());
		assertEquals("test", reservation.getTitle());
		assertEquals(20, reservation.getAteendees());
		assertEquals("class", reservation.getType());
		assertEquals("2020-11-11 08:00", datetimeFormatter.format(reservation.getStart()));
		assertEquals("2020-11-11 09:00", datetimeFormatter.format(reservation.getEnd()));
		assertEquals("Active", reservation.getStatus());
		assertEquals(1, reservation.getUserID());
		assertEquals(1, reservation.getRoomID());
	}
	
	//Testing

}
