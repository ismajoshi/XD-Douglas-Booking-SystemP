package com.csis3275.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.dao.RoomDAOImpl_sli_15;
import com.csis3275.dao.RoomListScheduleDAOImpl_aca_58;
import com.csis3275.model.RoomListSchedule_aca_58;
import com.csis3275.model.Room_sli_15;
import com.csis3275.model.SearchRoomID_sli_15;

@Controller
public class RoomListScheduleController_aca_58 {

	@Autowired
	RoomListScheduleDAOImpl_aca_58 roomListScheduleDAOImpl;
	@Autowired
	RoomDAOImpl_sli_15 roomDAOImpl;
	@Autowired
	UserSessionService_imo_65 userSessionServiceObj;

	@ModelAttribute("roomListSchedule")
	public RoomListSchedule_aca_58 setupInitialForm() {
		return new RoomListSchedule_aca_58();
	}

	@GetMapping("/showRoomListSchedule")
	public String showRoomListSchedule(HttpSession session, Model model) {

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

		RoomListSchedule_aca_58 roomListSchedule = setupInitialForm();
		model.addAttribute("roomListSchedule", roomListSchedule);

		List<RoomListSchedule_aca_58> roomsList = roomListScheduleDAOImpl.getAllRooms();
		model.addAttribute("roomsList", roomsList);

		// get a list of Rooms
		List<String> IDroomsList = roomListScheduleDAOImpl.getListRoomID();
		// Add the results to the model
		model.addAttribute("IDroomsList", IDroomsList);

		// Get list of RoomNumbers -sli-15
		List<String> roomNumList = roomListScheduleDAOImpl.getListRoomNumber();
		model.addAttribute("roomNumList", roomNumList);
		// Add Header Message -sli-15
		model.addAttribute("headerMessage", "Show Room Reservations");
		model.addAttribute("roomID", new SearchRoomID_sli_15());

		return "showRoomListSchedule";
	}

	@PostMapping("/showRoomListSchedule")
	public String showRoomListSchedulePost(HttpSession session, Model model) {
		
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

		RoomListSchedule_aca_58 roomListSchedule = setupInitialForm();
		model.addAttribute("roomListSchedule", roomListSchedule);

		List<RoomListSchedule_aca_58> roomsList = roomListScheduleDAOImpl.getAllRooms();
		model.addAttribute("roomsList", roomsList);

		// get a list of Rooms
		List<String> IDroomsList = roomListScheduleDAOImpl.getListRoomID();
		// Add the results to the model
		model.addAttribute("IDroomsList", IDroomsList);

		// Get list of RoomNumbers -sli-15
		List<String> roomNumList = roomListScheduleDAOImpl.getListRoomNumber();
		model.addAttribute("roomNumList", roomNumList);
		// Add Header Message -sli-15
		model.addAttribute("headerMessage", "Show Room Reservations");
		model.addAttribute("roomID", new SearchRoomID_sli_15());

		return "showRoomListSchedule";
	}

	// Display the reservations for the listed room
	@PostMapping("/displayRoomReservation")
	public String displayRoomReservation(@RequestParam(name = "id", required = true) String id, Model model) {
		
		

		List<RoomListSchedule_aca_58> roomsList = roomListScheduleDAOImpl.getRoomReservations(id);
		model.addAttribute("roomsList", roomsList);

		// Add Header Message -sli-15
		model.addAttribute("headerMessage", "Showing Reservations for Room " + id);

		// get a list of Rooms
		List<String> IDroomsList = roomListScheduleDAOImpl.getListRoomID();
		// Add the results to the model
		model.addAttribute("IDroomsList", IDroomsList);

		return "showRoomListSchedule";
	}

	// Display the reservations for the listed room
	@GetMapping("/displayRoomReservation")
	public String displayRoomReservationGET(@RequestParam(name = "id", required = true) String id, Model model) {

		return displayRoomReservation(id, model);

	}

	@PostMapping("/searchRooms")
	public String searchRoomsByDay(@ModelAttribute("roomListSchedule") BindingResult bindingResult, Model model) {

		// get a list of Rooms
		List<String> IDroomsList = roomListScheduleDAOImpl.getListRoomID();

		// Add the results to the model
		model.addAttribute("IDroomsList", IDroomsList);

		// get a list of registered dates
		List<String> dateList = roomListScheduleDAOImpl.getListDate();

		// add the result to the model
		model.addAttribute("dateList", dateList);

		return "showRoomListSchedule";
	}

}
