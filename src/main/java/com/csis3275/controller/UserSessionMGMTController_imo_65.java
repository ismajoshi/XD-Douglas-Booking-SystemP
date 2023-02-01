
package com.csis3275.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.dao.UserMGMTDAO_imo_65;
import com.csis3275.dao.UserSessionDAO_imo_65;
import com.csis3275.dao.JDBCLoginCheck_imo_65;
import com.csis3275.model.UserSession_imo_65;
import com.csis3275.model.User_imo_65;
import com.csis3275.model.loginUser_imo_65;

/**
 * 
 * @author Ismael Moreno
 *
 */
@Controller
@RequestMapping("/SessionMGMT")

public class UserSessionMGMTController_imo_65 {
	/**
	 * DAO used to obtain users from database
	 */
	@Autowired
	UserSessionDAO_imo_65 sessionDAO;

	@Autowired
	UserSessionService_imo_65 userSessionServiceObj;

	/**
	 * 
	 * @return empty user object used as a temporary object on forms.
	 */

	/**
	 * @param request View Servelt request object
	 * @param model   model object, for show users it will be empty but the users
	 *                list will be added.
	 * @param session Http Session object
	 * @return string to show the User MGMT page displaying users
	 */
	@GetMapping("/ShowSessions")
	public String showUsers(Model model, HttpServletRequest request, HttpSession session) {

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

		List<UserSession_imo_65> sessionList = sessionDAO.getAllUserSession();

		model.addAttribute("SessionList", sessionList);

		return "SessionMGMT";
	}

	@GetMapping("/InvalidateSession")
	public String deleteUser(@RequestParam(name = "id", required = true) int id, Model model, HttpSession session) {
		
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

		if (sessionDAO.InvalidateUserSession(id))
			model.addAttribute("success", "Session " + id + " has been set to inactive");
		else
			model.addAttribute("error", "There was an error changing Session " + id + " Status");

		List<UserSession_imo_65> sessionList = sessionDAO.getAllUserSession();

		model.addAttribute("SessionList", sessionList);

		return "SessionMGMT";

	}

}
