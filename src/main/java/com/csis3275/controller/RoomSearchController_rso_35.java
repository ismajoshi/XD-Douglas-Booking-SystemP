package com.csis3275.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csis3275.dao.RoomSearchDAOImpl_rso_35;
import com.csis3275.model.RoomSearch_rso_35;
import com.csis3275.model.Room_sli_15;
import com.csis3275.validator.RoomSearchFormValidator_rso_35;

@Controller
@RequestMapping("/room_search")
public class RoomSearchController_rso_35 {

	@Autowired
	RoomSearchDAOImpl_rso_35 roomSearchDaoImpl;

	@Autowired
	RoomSearchFormValidator_rso_35 roomSearchValidator;
	@Autowired
	UserSessionService_imo_65 userSessionServiceObj;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(roomSearchValidator);
	}

	@ModelAttribute("roomSearchObj")
	public RoomSearch_rso_35 setupInitialForm() {

		return new RoomSearch_rso_35();
	}

	// ---------------- Pages Call methods ----------------

	/**
	 * Initialize a default Room search form
	 * 
	 * @param session current HTTP Session
	 * @param model   model object passed by View page
	 * @return forwarded View page
	 */
	@GetMapping("/initialize")
	public String initialize(HttpSession session, Model model) {

		String errorMessage;
		switch (userSessionServiceObj.validateType(session.getId(), UserSessionService_imo_65.Privileges.student)) {
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

		// Get a default RoomSearch
		RoomSearch_rso_35 roomSearchObj = setupInitialForm();
		model.addAttribute("roomSearchObj", roomSearchObj);

		return "roomSearchForm";
	}

	/**
	 * Get the filled search form and process the searching function
	 * 
	 * @param bindingResult  View x Validator binding result
	 * @param pRoomSearchObj filled RoomSearch object
	 * @param model          model instance passed by View page
	 * @return forwarded View page
	 */
	@PostMapping("/search")
	public String searchRooms(@ModelAttribute("roomSearchObj") @Validated RoomSearch_rso_35 pRoomSearchObj,
			BindingResult bindingResult, Model model) {

		// Add the filled form back to view
		model.addAttribute("roomSearchObj", pRoomSearchObj);

		// Check validation
		if (bindingResult.hasErrors()) {
			System.out.println("Validation error identified: error count = " + bindingResult.getErrorCount());
			return "roomSearchForm";
		}

		// Get a list of Rooms considering the searching filter
		List<Room_sli_15> filteredRoomsList = roomSearchDaoImpl.getFilteredRooms(pRoomSearchObj);

		// Add the results to the model
		model.addAttribute("filteredRoomsList", filteredRoomsList);

		return "roomSearchForm";
	}

	// ---------------- List Attributes methods ----------------

	/**
	 * Initialize the List of Rooms Amenities on the system
	 * 
	 * @return List of Rooms to select
	 */
	@ModelAttribute("roomAmenitiesTypeList")
	public List<String> initializeRoomAmenitiesTypeList() {

		return roomSearchDaoImpl.getRoomAmenityTypes();
	}

	/**
	 * Initialize the List of Room Types
	 * 
	 * @return List of Rooms to select
	 */
	@ModelAttribute("roomTypeList")
	public List<String> initializeRoomTypeList() {

		List<String> roomTypeList = roomSearchDaoImpl.getRoomTypes();
		roomTypeList.add(0, "");

		return roomTypeList;
	}

	/**
	 * Initialize the List of Room Buildings
	 * 
	 * @return List of Rooms to select
	 */
	@ModelAttribute("roomBuildingsList")
	public List<String> initializeRoomBuildingsList() {

		List<String> roomBuildingsList = roomSearchDaoImpl.getRoomBuildings();
		roomBuildingsList.add(0, "");

		return roomBuildingsList;
	}

}
