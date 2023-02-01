package com.csis3275.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.dao.RoomAmenityDAOImpl_aca_58;
import com.csis3275.dao.RoomDAOImpl_sli_15;
import com.csis3275.model.RoomAmenity_aca_58;
import com.csis3275.model.Room_sli_15;


@Controller
public class RoomAmenityController_aca_58 {
	@Autowired
	UserSessionService_imo_65 userSessionServiceObj;

	@Autowired
	RoomAmenityDAOImpl_aca_58 roomAmenityDaoImpl;
	
	@Autowired
	RoomDAOImpl_sli_15 roomDAOImpl_sli_15; 
	
	//We need a blank roomAmenity for the add form
	@ModelAttribute("roomAmenity")
	public RoomAmenity_aca_58 setupAddForm() {
		return new RoomAmenity_aca_58();
	}
	
	//Thats a GET request from the browser to the URL below
	@GetMapping("/showRoomAmenities")
	public String showRoomAmenities(HttpSession session, Model model) {
		
		String errorMessage;
		switch(userSessionServiceObj.validateType(session.getId(), UserSessionService_imo_65.Privileges.professor)) {
			case INVALID_SESSION:
				errorMessage = "User Session expired or is invalid! Please, login again.";
				model.addAttribute("userSessionErrorMessage", errorMessage);
				return "userSessionError";
				
			case INSUFFICIENT_PRIVILEGE:
				errorMessage = "User does not have sufficient privilege to access this feature!";
				model.addAttribute("userSessionErrorMessage", errorMessage);
				return "dbsHome";
		
			case PRIVILEGE_OK:
		}

		//Get a list of room amenities from the controller
		List<RoomAmenity_aca_58> roomAmenities = roomAmenityDaoImpl.getAllAmenities();
	    
		
		//Add the results to the model
		model.addAttribute("roomAmenities", roomAmenities);

	    return "showRoomAmenities";
	}
	
	//Handle Form Post
	@PostMapping("/createRoomAmenities")
	public String createRoomAmenities(@ModelAttribute("roomAmenity") RoomAmenity_aca_58 createRoomAmenities, Model model, HttpSession session)	{
		
		String errorMessage;
		switch(userSessionServiceObj.validateType(session.getId(), UserSessionService_imo_65.Privileges.professor)) {
			case INVALID_SESSION:
				errorMessage = "User Session expired or is invalid! Please, login again.";
				model.addAttribute("userSessionErrorMessage", errorMessage);
				return "userSessionError";
				
			case INSUFFICIENT_PRIVILEGE:
				errorMessage = "User does not have sufficient privilege to access this feature!";
				model.addAttribute("userSessionErrorMessage", errorMessage);
				return "dbsHome";
		
			case PRIVILEGE_OK:
		}
		
		//Create the roomAmenity pass the object in.
		roomAmenityDaoImpl.createRoomAmenity(createRoomAmenities);
		
		//Get a list of room Amenities from the controller
		List<RoomAmenity_aca_58> roomAmenities = roomAmenityDaoImpl.getAllAmenities();
		model.addAttribute("roomAmenities", roomAmenities);
		
		return "showRoomAmenities";
	}
	
	//Get the roomAmenity and display the form
		@GetMapping("/deleteRoomAmenity")
		public String deleteRoomAmenity(@RequestParam(required = true) int id, Model model, HttpSession session)	{
			
			String errorMessage;
			switch(userSessionServiceObj.validateType(session.getId(), UserSessionService_imo_65.Privileges.professor)) {
				case INVALID_SESSION:
					errorMessage = "User Session expired or is invalid! Please, login again.";
					model.addAttribute("userSessionErrorMessage", errorMessage);
					return "userSessionError";
					
				case INSUFFICIENT_PRIVILEGE:
					errorMessage = "User does not have sufficient privilege to access this feature!";
					model.addAttribute("userSessionErrorMessage", errorMessage);
					return "dbsHome";
			
				case PRIVILEGE_OK:
			}
					
			//Get the RoomAmenity
			roomAmenityDaoImpl.deleteRoomAmenity(id);
			
			//Get a list of roomAmenity from the controller
			List<RoomAmenity_aca_58> roomAmenities = roomAmenityDaoImpl.getAllAmenities();
			model.addAttribute("roomAmenities", roomAmenities);
		
			model.addAttribute("message", "Deleted Room Amenities " + id); 
				
			return "showRoomAmenities";
		}	
	
	
	//Get the roomAmenity and display the form
	@GetMapping("/editRoomAmenity")
	public String editRoomAmenity(@RequestParam(required = true) int id, Model model, HttpSession session)	{
		
		String errorMessage;
		switch(userSessionServiceObj.validateType(session.getId(), UserSessionService_imo_65.Privileges.professor)) {
			case INVALID_SESSION:
				errorMessage = "User Session expired or is invalid! Please, login again.";
				model.addAttribute("userSessionErrorMessage", errorMessage);
				return "userSessionError";
				
			case INSUFFICIENT_PRIVILEGE:
				errorMessage = "User does not have sufficient privilege to access this feature!";
				model.addAttribute("userSessionErrorMessage", errorMessage);
				return "dbsHome";
		
			case PRIVILEGE_OK:
		}
				
		//Get the room amenity
		RoomAmenity_aca_58 editRoomAmenity = roomAmenityDaoImpl.getRoomAmenityByRoomAmenityId(id);
		model.addAttribute("roomAmenities", editRoomAmenity);
		
		return "editRoomAmenity";
	}
	

	@PostMapping("/editRoomAmenity")
	public String updateRoomAmenities(@ModelAttribute("roomAmenity") RoomAmenity_aca_58 updatedRoomAmenity, Model model)	{
		
		roomAmenityDaoImpl.updateRoomAmenity(updatedRoomAmenity);
		
		//Get a list of room amenities from the controller
		List<RoomAmenity_aca_58> roomAmenities = roomAmenityDaoImpl.getAllAmenities();
		model.addAttribute("roomAmenities", roomAmenities);
	
		model.addAttribute("message","Edited Room Amenity " + updatedRoomAmenity.getRoom_amentieId());
		
		//We are redirecting to show room amenities so that the GETMapping is executed again because our edit did not add the list of room amenities to the model
		return "showRoomAmenities";
		
	}
	
	
	@ModelAttribute("amenityTypeList") 
    public List<String> initializeAmenityTypeList() { 
 
        List<String> amenityTypeList = new ArrayList<String>();
        
        amenityTypeList.add(""); 
        amenityTypeList.add("Computer");  
        amenityTypeList.add("Lap Top"); 
        amenityTypeList.add("Flipchart");         
        amenityTypeList.add("Projector_Kit"); 
        amenityTypeList.add("Table_Chart_Kit"); 
        amenityTypeList.add("TV"); 
        
        return amenityTypeList; 
    }
			
	@ModelAttribute("currentRoomList")
	public List<Room_sli_15> initializeRoomList() {
		List<Room_sli_15> currentRoomList = roomDAOImpl_sli_15.getAllRooms();
		
		return currentRoomList;
		
	}
	
}
