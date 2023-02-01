package com.csis3275.unit;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import com.csis3275.model.RoomSearch_rso_35;

/**
 * Class to implement Unit tests for the feature RoomSearch model using JUnit
 * This model class does not have complex computed methods, only basic getter/setter methods.
 * 
 * @author Ricardo dos Santos Alves de Souza
 *
 */
public class RoomSearchModelUnitTest_rso_35 {
	
	RoomSearch_rso_35 roomSearchObj;
	
	SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	/**
	 * Create a new instance to be used in the tests.
	 * 
	 * @throws Exception General exception 
	 */
	@Before
	public void setUp() throws Exception {
		
		//Create instance
		roomSearchObj = new RoomSearch_rso_35();
		
		//Set attributes
		roomSearchObj.setNumber("S101");
		roomSearchObj.setFloor("Level 1");
		roomSearchObj.setBuilding("Main Building");
		roomSearchObj.setCapacity(5);
		roomSearchObj.setRoomType("Study Room");
		
		//Create and set an Amenities List
		List<String> initialAmenitiesList = new ArrayList<String>();
		initialAmenitiesList.add("TV");
		initialAmenitiesList.add("Laptop");
		
		roomSearchObj.setAmenitie_typeList(initialAmenitiesList);
		
		//Set Availability Fields
		roomSearchObj.setOnlyAvailable(true);
		roomSearchObj.setDesiredStartDatetime(datetimeFormatter.parse("2020-11-09 08:00"));
		roomSearchObj.setDesiredEndDatetime(datetimeFormatter.parse("2020-11-09 09:00"));		
		
	}
	
	/**
	 * Simple test to check the String attributes Getter methods
	 */
	@Test
	public void testSimpleStringAttributesGetters() {
		
		assertEquals("Searching Room number must be 'S101'", "S101", roomSearchObj.getNumber()); //Number
		
		assertEquals("Searching Room floor must be 'Level 1'", "Level 1", roomSearchObj.getFloor()); //Floor
		
		assertEquals("Searching Room building must be 'Main Building'", "Main Building", roomSearchObj.getBuilding()); //Capacity
		
		assertEquals("Searching Room capacity must be 5", 5, roomSearchObj.getCapacity()); //Capacity
		
		assertEquals("Searching Room type must be 'Study Room'", "Study Room", roomSearchObj.getRoomType()); //Room Type
		
	}
	
	/**
	 * Simple that to check the content of the Amenities List attribute 
	 */
	@Test
	public void testAmenitiesTypeList() {
		
		assertThat("Amenities list does not containt the correct values", roomSearchObj.getAmenitie_typeList(), hasItems("TV", "Laptop"));
		
	}
	
	/**
	 * Simple test to check the content of the searching fields regarding room availability
	 */
	@Test
	public void testRoomAvailabilityAttibutes() {
		
		assertTrue("Is Available Attribute must be true", roomSearchObj.isOnlyAvailable()); //isOnlyAvailable
		
		assertEquals("Desired start date/time must be '2020-11-09 08:00'", "2020-11-09 08:00", 
					datetimeFormatter.format(roomSearchObj.getDesiredStartDatetime())); //Start Datetime
		
		assertEquals("Desired end date/time must be '2020-11-09 08:00'", "2020-11-09 09:00", 
				datetimeFormatter.format(roomSearchObj.getDesiredEndDatetime())); //End Datetime		
		
	}

}
