package com.csis3275.integration;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.csis3275.dao.ReservationDAOImpl_sli_15;
import com.csis3275.dao.RoomDAOImpl_sli_15;
import com.csis3275.dao.UserMGMTDAO_imo_65;
import com.csis3275.model.Reservation_sli_15;
import com.csis3275.model.Room_sli_15;
import com.csis3275.model.User_imo_65;

/**
 * Class to implement integrated test regarding Room Feature, User Feature and Reservation Feature.
 * 
 * According to the definition provided for Iteration#2, this test may be consider a System Test as well due to involving DAO classes and database CRUD operations.

 * @author Ricardo dos Santos Alves de Souza
 *
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReservationAndRoomIntegrationTest_rso_35 {
	
	SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	//Titles to be used and tracked
	String createdReservationTitle = "Reservation for Integration Test";
	String updatedReservationTitle = "Updated Title for Integration Test";	

	//Set DAO classes instances
	@Autowired
	UserMGMTDAO_imo_65 userDaoImpl;
	@Autowired
	RoomDAOImpl_sli_15 roomDAOImpl;
	@Autowired
	ReservationDAOImpl_sli_15 reservationDaoImpl;

// ###################### Test Cases ######################	
	
	/**
	 * Create a new Reservation record in the database
	 * 
	 * @throws Exception General Exception resultant
	 */
	@Test
	public void test1_CreateNewReservation() throws Exception {
		
		//Create a Mock User
		User_imo_65 testUserObj = new User_imo_65();
		testUserObj.setLName("Integrated");
		testUserObj.setfName("Test");
		testUserObj.setEmail("test@test.com");
		testUserObj.setPhone("888-888-0000");
		testUserObj.setType("student");
		testUserObj.setPassword("test");
		testUserObj.setRegDate(new Date());
		testUserObj.setStatus("Logged off");
		
		//Create Test User in the database
		if(!userDaoImpl.createNewUser(testUserObj)) {
			throw new Exception("Fail to create Test Room!");
		}

		//Get created User
		User_imo_65 createdUserObj = userDaoImpl.getLastUser();
		
		//Create a Mock Room Obj
		Room_sli_15 testRoomObj = new Room_sli_15();
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
		Room_sli_15 createdRoomObj = roomDAOImpl.getLastRoom();
		
		//Prepare Reservation Obj to create
		Reservation_sli_15 testReservationObj = new Reservation_sli_15();
		
		testReservationObj.setRoomID(createdRoomObj.getRoomID());
		testReservationObj.setUserID(createdUserObj.getId());
		testReservationObj.setTitle(createdReservationTitle);
		testReservationObj.setType("Class");
		testReservationObj.setAteendees(5);
		testReservationObj.setStart(datetimeFormatter.parse("2020-12-14 09:00"));
		testReservationObj.setEnd(datetimeFormatter.parse("2020-12-14 10:00"));
		testReservationObj.setStatus("Active");		
		
		//Run creation method
		assertTrue("Fail to persist Reservation Test in the database", reservationDaoImpl.createReservation(testReservationObj));

		//Get created Reservation
		Reservation_sli_15 createdReservationObj = reservationDaoImpl.getLastReservation();
		assertNotNull("Fail to get created Reservation Test from the database", createdReservationObj);

		//Check if it has the same attributes
		assertEquals("Persited Reservation Title does not match correctly", testReservationObj.getTitle(), createdReservationObj.getTitle());
		assertEquals("Persited Reservation Type does not match correctly", testReservationObj.getType(), createdReservationObj.getType());
		assertEquals("Persited Reservation Ateendees number does not match correctly", testReservationObj.getAteendees(), createdReservationObj.getAteendees());		
		assertEquals("Persited Reservation Status does not match correctly", testReservationObj.getStatus(), createdReservationObj.getStatus());
		
	}
	
	/**
	 * Get all the Reservations and check if the created one is on the list 
	 * @throws Exception General Exception resultant
	 */
	@Test
	public void test2_ReadAllReservationAndCheckCreated() throws Exception {

		//Get all from the database
		List<Reservation_sli_15> allReservationsList = reservationDaoImpl.getAllReservations();
		
		printReservationListContent("test2_ReadAllReservationAndCheckCreated", allReservationsList);
		
		//Check if the returned list has many items
		assertTrue("The list of all Reservations should return at least one record", (allReservationsList.size() > 0));
		
		//Get created Reservation
		Reservation_sli_15 createdReservationObj = reservationDaoImpl.getLastReservation();
		assertNotNull("Fail to get created Reservation Test from the database", createdReservationObj);		
		
		//Check if the created reservation is included
		assertTrue("Returned list should containt the created test Reservation", hasReservation(allReservationsList, createdReservationObj)); 		
	}
	
	
	/**
	 * Change some attributes and check if it was correctly updated in the database  
	 * @throws Exception General Exception resultant
	 */
	@Test
	public void test3_UpdateCreatedReservation() throws Exception {
		
		//Get created Reservation
		Reservation_sli_15 createdReservationObj = reservationDaoImpl.getLastReservation();
		assertNotNull("Fail to get created Reservation Test from the database", createdReservationObj);
		//Check if last one is the created for the test
		assertEquals("Last created Reservation does not match", createdReservationTitle, createdReservationObj.getTitle());

		//Change some attributes
		createdReservationObj.setTitle(updatedReservationTitle);
		createdReservationObj.setType("Meeting");
		createdReservationObj.setAteendees(3);
		createdReservationObj.setStatus("Canceled");
		
		//Update Reservation in the database 
		assertTrue("Fail to persist Reservation Test in the database", reservationDaoImpl.updateReservation(createdReservationObj));

		//Get updated Reservation
		Reservation_sli_15 updatedReservationObj = reservationDaoImpl.getReservation(createdReservationObj.getReservationID());
		assertNotNull("Fail to get updated Reservation Test from the database", updatedReservationObj);		

		//Check if it has the same attributes
		assertEquals("Updated Reservation Title does not match correctly", updatedReservationObj.getTitle(), createdReservationObj.getTitle());
		assertEquals("Updated Reservation Type does not match correctly", updatedReservationObj.getType(), createdReservationObj.getType());
		assertEquals("Updated Reservation Ateendees number does not match correctly", updatedReservationObj.getAteendees(), createdReservationObj.getAteendees());		
		assertEquals("Updated Reservation Status does not match correctly", updatedReservationObj.getStatus(), createdReservationObj.getStatus());
		
	}
	
	/**
	 * Delete the created Reservation and check if it was really removed
	 * 
	 * @throws Exception General Exception resultant
	 */
	@Test
	public void test4_DeleteCreatedReservation() throws Exception {
		
		//Get created Reservation
		Reservation_sli_15 createdReservationObj = reservationDaoImpl.getLastReservation();
		assertNotNull("Fail to get created Reservation Test from the database", createdReservationObj);
		//Check if last one is the created for the test
		assertTrue("Last created Reservation does not match", ( createdReservationObj.getTitle().equals(createdReservationTitle) || createdReservationObj.getTitle().equals(updatedReservationTitle) ) );		
		
		//Delete Reservation from the database 
		assertTrue("Fail to delete Reservation Test from the database", reservationDaoImpl.deleteReservation(createdReservationObj.getReservationID()));
		
		//Get all from the database
		List<Reservation_sli_15> allReservationsList = reservationDaoImpl.getAllReservations();
		
		//Confirm that the created reservation is included
		assertFalse("Returned list should NOT containt the created test Reservation", hasReservation(allReservationsList, createdReservationObj)); 		
		
		//Delete Mocks User and Room
		roomDAOImpl.deleteRoom(createdReservationObj.getRoomID());
		userDaoImpl.deleteUser(createdReservationObj.getUserID());	

	}
	
	// ###################### Support Methods ######################
	
	/**
	 * Check if a List has a specific Reservation based on the Reservation attributes 
	 * 
	 * @param pReservationList List containing Reservation
	 * @param pReservationObj Reservation to check 
	 * @return true if the Reservation is contained in the List
	 */
	private boolean hasReservation(List<Reservation_sli_15> pReservationList, Reservation_sli_15 pReservationObj) {
		
		//Loop the list
		for(Reservation_sli_15 reservationItem : pReservationList) {
			
			//If the attributes are equal, return true
			if( reservationItem.getReservationID() == pReservationObj.getReservationID()
				&& reservationItem.getTitle().equals(pReservationObj.getTitle())
				&& 	reservationItem.getType().equals(pReservationObj.getType())
				&& 	reservationItem.getAteendees() == pReservationObj.getAteendees()
				&& 	reservationItem.getStatus().equals(pReservationObj.getStatus())
				&& 	reservationItem.getUserID() == pReservationObj.getUserID()
				&& 	reservationItem.getRoomID() == pReservationObj.getRoomID()					
			) {
				return true;
			}
			
		}
		
		return false;
	}
	
	/**
	 * Print to console the content of the result list
	 * @param pReservationList result list
	 */
	private void printReservationListContent(String title, List<Reservation_sli_15> pReservationList) {
		
		System.out.println();
		System.out.println(title);
		System.out.println("Result List size: " + pReservationList.size());
		//Loop the list
		for(Reservation_sli_15 reservationItem : pReservationList) {
			
			System.out.println("Reservation ID: " + reservationItem.getReservationID() 
						+ ", Title: " + reservationItem.getTitle()
						+ ", Type: " + reservationItem.getType() 
						+ ", Attendees: " + reservationItem.getAteendees());
		}
		System.out.println();
		
	}	
	

}
