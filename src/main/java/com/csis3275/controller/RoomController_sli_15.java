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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.dao.BaseCalendarDAOImpl_rso_35;
import com.csis3275.dao.RoomDAOImpl_sli_15;
import com.csis3275.model.BaseCalendar_rso_35;
import com.csis3275.model.Room_sli_15;

/**
 * @author Scott Linden 300327715 This is the controller for the Room Management
 *         feature.
 */
@Controller
@RequestMapping("/RoomMGMT")
public class RoomController_sli_15 {

	@Autowired
	RoomDAOImpl_sli_15 roomMGMTDaoImpl;
	@Autowired
	BaseCalendarDAOImpl_rso_35 baseCalendarDaoImpl;
	@Autowired
	UserSessionService_imo_65 userSessionServiceObj;

	@ModelAttribute("RoomMGMT")
	public Room_sli_15 roomMgmt() {
		return new Room_sli_15();
	}

	@GetMapping("/showAllRooms")
	public String showAllRooms(HttpSession session, Model model) {

		String errorMessage;
		switch (userSessionServiceObj.validateType(session.getId(), UserSessionService_imo_65.Privileges.admin)) {
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
		List<Room_sli_15> allRoomsList = roomMGMTDaoImpl.getAllRooms();

		model.addAttribute("allRoomsList", allRoomsList);
		model.addAttribute("room", new Room_sli_15());

		return "RoomMGMT";
	}

	@PostMapping("/createNewRoom")
	public String createNewRoom(@ModelAttribute("room") Room_sli_15 room, Model model, HttpSession session) {

		String errorMessage;
		switch (userSessionServiceObj.validateType(session.getId(), UserSessionService_imo_65.Privileges.admin)) {
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

		// Check if there is already a room with the same location attributes
		if (roomMGMTDaoImpl.hasRoomWithSameLocation(room)) {
			model.addAttribute("error",
					"Cannot create another Room with the same location attributes (number, floor, and building)!");

			List<Room_sli_15> allRoomsList = roomMGMTDaoImpl.getAllRooms();
			model.addAttribute("allRoomsList", allRoomsList);
			model.addAttribute("room", room);

			return "RoomMGMT";
		}

		if (roomMGMTDaoImpl.createNewRoom(room)) {
			Room_sli_15 newRoom = roomMGMTDaoImpl.getLastRoom();
			model.addAttribute("success", "Room " + newRoom.getRoomID() + " has been added!");
		} else
			model.addAttribute("error", "There was an error adding the Room!");

		List<Room_sli_15> allRoomsList = roomMGMTDaoImpl.getAllRooms();
		model.addAttribute("allRoomsList", allRoomsList);

		return "RoomMGMT";
	}

	@PostMapping("/updateRoom")
	public String updateRoom(@ModelAttribute("room") Room_sli_15 room, Model model, HttpSession session) {
		String errorMessage;
		switch (userSessionServiceObj.validateType(session.getId(), UserSessionService_imo_65.Privileges.admin)) {
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

		// Check if there is already a room with the same location attributes
		if (roomMGMTDaoImpl.hasRoomWithSameLocation(room)) {
			model.addAttribute("error",
					"Cannot save another Room with the same location attributes (number, floor, and building)!");

			List<Room_sli_15> allRoomsList = roomMGMTDaoImpl.getAllRooms();
			model.addAttribute("allRoomsList", allRoomsList);
			model.addAttribute("room", room);

			return "RoomMGMT";
		}

		if (roomMGMTDaoImpl.updateRoom(room))
			model.addAttribute("success", "Room " + room.getRoomID() + " has been updated!");
		else
			model.addAttribute("error", "There was an error updating the Room!");

		List<Room_sli_15> allRoomsList = roomMGMTDaoImpl.getAllRooms();
		model.addAttribute("allRoomsList", allRoomsList);

		return "RoomMGMT";
	}

	@GetMapping("update")
	public String updateForm(@RequestParam(name = "id", required = true) int id, Model model, HttpSession session) {
		
		String errorMessage;
		switch(userSessionServiceObj.validateType(session.getId(), UserSessionService_imo_65.Privileges.admin)) {
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

		Room_sli_15 room = roomMGMTDaoImpl.getRoom(id);

		model.addAttribute("room", room);

		return "RoomUpdate";
	}

	@GetMapping("/delete")
	public String deleteRoom(@RequestParam int id, @ModelAttribute("room") Room_sli_15 room, Model model) {

		if (roomMGMTDaoImpl.deleteRoom(id))
			model.addAttribute("success", "Room " + id + " has been deleted!");
		else
			model.addAttribute("error", "There was an error deleting Room " + id);

		List<Room_sli_15> allRoomsList = roomMGMTDaoImpl.getAllRooms();
		model.addAttribute("allRoomsList", allRoomsList);

		return "RoomMGMT";
	}

	@ModelAttribute("baseCalendarList")
	public List<String> getBaseCalendarList() {
		List<BaseCalendar_rso_35> baseCalendarList = baseCalendarDaoImpl.getAllRows();
		List<String> calendarList = new ArrayList<String>();

		for (int x = 0; x < baseCalendarList.size(); x++) {
			calendarList.add(Integer.toString(baseCalendarList.get(x).getId()));
		}

		return calendarList;
	}

}
