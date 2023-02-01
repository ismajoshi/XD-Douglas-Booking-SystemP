package com.csis3275.integration;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.csis3275.dao.ReservationDAOImpl_sli_15;
import com.csis3275.dao.RoomDAOImpl_sli_15;
import com.csis3275.dao.RoomSearchDAOImpl_rso_35;
import com.csis3275.model.Reservation_sli_15;
import com.csis3275.model.RoomSearch_rso_35;
import com.csis3275.model.Room_sli_15;

/**
 * Class to implement test integrating RoomSearch feature and Reservation feature.
 * According to the definition provided for Iteration#2, this test may be consider a System Test as well due to involving DAO classes and database CRUD operations.
 *  
 * @author Ricardo dos Santos Alves de Souza
 *
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoomSearchAndReservationIntegrationTest_rso_35 {
	
	SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	//Set DAO classes instances
	@Autowired
	RoomSearchDAOImpl_rso_35 roomSearchDaoImpl;
	@Autowired
	ReservationDAOImpl_sli_15 reservationDaoImpl;
	@Autowired
	RoomDAOImpl_sli_15 roomDAOImpl;
	
	//Objects to use in the tests
	Room_sli_15 testRoomObj;
	Room_sli_15 createdRoomObj;
	Reservation_sli_15 testReservationObj;
	Reservation_sli_15 createdReservationObj;
	
	/**
	 * Create a Room and a Reservation to be used in the tests
	 * 
	 * @throws Exception General Exception resultant
	 */
	@Before
	public void setUpData() throws Exception {
		
		//Create a Room Obj
		testRoomObj = new Room_sli_15();
		testRoomObj.setNumber("TST201");
		testRoomObj.setFloor("2");
		testRoomObj.setBuilding("Test Building");
		testRoomObj.setCapacity(40);
		testRoomObj.setRoomType("classroom");
		testRoomObj.setBaseCalendar(1); //Using default Base Calendar
		
		//Create Test Room in the database
		if(!roomDAOImpl.createNewRoom(testRoomObj)) {
			throw new Exception("Fail to create Test Room!");
		}
		
		//Get created Room
		createdRoomObj = roomDAOImpl.getLastRoom();
		
		//Create a Reservation Obj
		testReservationObj = new Reservation_sli_15();
		testReservationObj.setRoomID(createdRoomObj.getRoomID());
		testReservationObj.setUserID(1); //Using default first one
		testReservationObj.setTitle("Reservation for RoomSearch Integration Test");
		testReservationObj.setType("Class");
		testReservationObj.setAteendees(20);
		testReservationObj.setStart(datetimeFormatter.parse("2020-11-12 13:00"));
		testReservationObj.setEnd(datetimeFormatter.parse("2020-11-12 14:00"));
		testReservationObj.setStatus("Active");
		
		//Create Test Reservation in the database
		if(!reservationDaoImpl.createReservation(testReservationObj)) {
			throw new Exception("Fail to create Test Reservation!");
		}

	}
	
	/**
	 * Delete from the database the Room and Reservation records created to be used in the test
	 */
	@After
	public void cleanUsedData() {
		
		//Delete the Reservation
		createdReservationObj = reservationDaoImpl.getLastReservation();
		reservationDaoImpl.deleteReservation(createdReservationObj.getReservationID());
		
		//Delete the Room
		createdRoomObj = roomDAOImpl.getLastRoom();
		roomDAOImpl.deleteRoom(createdRoomObj.getRoomID());
		
	}
	
// ###################### Test Cases ######################

	/**
	 * Test the room list obtained using only the minimum required filer (capacity). Check if the created room is in the list.
	 * 
	 * @throws Exception General Exception resultant
	 */
	@Test
	public void test1_CheckRoomSearchResultIncludingRoomCreated() throws Exception {
		
		//Create a basic Room Search
		RoomSearch_rso_35 simpleRoomSearch = new RoomSearch_rso_35();
		
		simpleRoomSearch.setCapacity(35); //Set minimum capacity to 35
		
		//Run the searching
		List<Room_sli_15> resultRoomList = roomSearchDaoImpl.getFilteredRooms(simpleRoomSearch);
		
		printRoomListContent("test1_CheckRoomSearchResultIncludingRoomCreated", resultRoomList);
		
		//Check if the returned list has many items
		assertTrue("Basic room search should return many rooms", (resultRoomList.size() > 0));
		
		//Check if the created room is included
		assertTrue("Returned search list should containt the created test room", hasRoom(resultRoomList, createdRoomObj)); 
		
	}
	
	/**
	 * Create a filter based on the Room location and check if the created room is not in the list.
	 * 
	 * @throws Exception General Exception resultant
	 */
	@Test
	public void test2_FilterinfByLocationNotIncludingRoomCreated() throws Exception {
		
		//Create a basic Room Search
		RoomSearch_rso_35 simpleRoomSearch = new RoomSearch_rso_35();
		
		simpleRoomSearch.setCapacity(1); //Set minimum capacity to 5
		simpleRoomSearch.setBuilding("Concourse");
		
		//Run the searching
		List<Room_sli_15> resultRoomList = roomSearchDaoImpl.getFilteredRooms(simpleRoomSearch);
		
		printRoomListContent("test2_FilterinfByLocationNotIncludingRoomCreated", resultRoomList);
		
		//Check if the returned list has many items
		assertTrue("Filtered room search should return many rooms", (resultRoomList.size() > 0));
		
		//Check if the created room is included
		assertFalse("Returned search list should NOT containt the created test room", hasRoom(resultRoomList, createdRoomObj)); 
		
	}
	
	/**
	 * Create a filter using Only Available Rooms and check if the created room is in the list.
	 * 
	 * @throws Exception General Exception resultant
	 */
	@Test
	public void test3_FilteringOnlyAvailableIncludingRoomCreated() throws Exception {
		
		//Create a basic Room Search
		RoomSearch_rso_35 simpleRoomSearch = new RoomSearch_rso_35();
		
		simpleRoomSearch.setCapacity(35); //Set minimum capacity to 5
		
		simpleRoomSearch.setOnlyAvailable(true); //Set Only Available Room on
		simpleRoomSearch.setDesiredStartDatetime(datetimeFormatter.parse("2020-11-12 10:00")); //Set period off the test reservation one
		simpleRoomSearch.setDesiredEndDatetime(datetimeFormatter.parse("2020-11-12 12:00"));		
		
		//Run the searching
		List<Room_sli_15> resultRoomList = roomSearchDaoImpl.getFilteredRooms(simpleRoomSearch);
		
		printRoomListContent("test3_FilteringOnlyAvailableIncludingRoomCreated", resultRoomList);
		
		//Check if the returned list has many items
		assertTrue("Only available filtered room search should return many rooms", (resultRoomList.size() > 0));
		
		//Check if the created room is included
		assertTrue("Returned search list should containt the created test room", hasRoom(resultRoomList, createdRoomObj)); 
		
	}		
	
	/**
	 * Create a filter using Only Available Rooms and check if the created room is not in the list.
	 * 
	 * @throws Exception General Exception resultant
	 */
	@Test
	public void test4_FilteringOnlyAvailableNotIncludingRoomCreated() throws Exception {
		
		//Create a basic Room Search
		RoomSearch_rso_35 simpleRoomSearch = new RoomSearch_rso_35();
		
		simpleRoomSearch.setCapacity(1); //Set minimum capacity to 5
		
		simpleRoomSearch.setOnlyAvailable(true); //Set Only Available Room on
		simpleRoomSearch.setDesiredStartDatetime(datetimeFormatter.parse("2020-11-12 10:00")); //Set period including the test reservation one
		simpleRoomSearch.setDesiredEndDatetime(datetimeFormatter.parse("2020-11-12 14:00"));		
		
		//Run the searching
		List<Room_sli_15> resultRoomList = roomSearchDaoImpl.getFilteredRooms(simpleRoomSearch);
		
		printRoomListContent("test4_FilteringOnlyAvailableNotIncludingRoomCreated", resultRoomList);
		
		//Check if the returned list has many items
		assertTrue("Only available filtered room search should return many rooms", (resultRoomList.size() > 0));
		
		//Check if the created room is included
		assertFalse("Returned search list should NOT containt the created test room", hasRoom(resultRoomList, createdRoomObj)); 
		
	}	
	
// ###################### Support Methods ######################
	
	/**
	 * Check if a List has a specific Room based on the Room attributes number, floor, building, capacity, roomType  
	 * 
	 * @param pRoomList List containing Rooms
	 * @param pRoomObj Room to check 
	 * @return true if the Room is contained in the List
	 */
	private boolean hasRoom(List<Room_sli_15> pRoomList, Room_sli_15 pRoomObj) {
		
		//Loop the list
		for(Room_sli_15 roomItem : pRoomList) {
			
			//If the attributes are equal, return true
			if( roomItem.getNumber().equals(pRoomObj.getNumber())
				&& 	roomItem.getFloor().equals(pRoomObj.getFloor())
				&& 	roomItem.getBuilding().equals(pRoomObj.getBuilding())
				&& 	roomItem.getCapacity() == pRoomObj.getCapacity()
				&& 	roomItem.getRoomType().equals(pRoomObj.getRoomType())				
			) {
				return true;
			}
			
		}
		
		return false;
	}
	
	/**
	 * Print to console the content of the result list
	 * @param pRoomList result list
	 */
	private void printRoomListContent(String title, List<Room_sli_15> pRoomList) {
		
		System.out.println();
		System.out.println(title);
		System.out.println("Result List size: " + pRoomList.size());
		//Loop the list
		for(Room_sli_15 roomItem : pRoomList) {
			
			System.out.println("Room ID: " + roomItem.getRoomID() + ", number: " 
						+ roomItem.getNumber() + ", Floor: " 
						+ roomItem.getFloor() + ", Building: " 
						+ roomItem.getBuilding());
		}
		System.out.println();
		
	}
	
}
