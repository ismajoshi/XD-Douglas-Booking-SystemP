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
import com.csis3275.dao.JDBCLoginCheck_imo_65;
import com.csis3275.model.User_imo_65;
import com.csis3275.model.loginUser_imo_65;

/**
 * 
 * @author Ismael Moreno
 *
 */
@Controller
@RequestMapping("/UserMGMT")

public class UserMGMTController_imo_65 {
	/**
	 * DAO used to obtain users from database
	 */
	@Autowired
	UserMGMTDAO_imo_65 userDAO;
	
	@Autowired
	UserSessionService_imo_65 userSessionServiceObj;
	/**
	 * 
	 * @return empty user object used as a temporary object on forms.
	 */
	@ModelAttribute("user")
	public User_imo_65 usermgmtform() {
		return new User_imo_65();
	}
	/**
	 * @param request View Servelt request object
	 * @param model model object, for show users it will be empty but the users list will be added.
	 * @param session Http Session object
	 * @return string to show the User MGMT page displaying users
	 */
	@GetMapping("/showUsers")
	public String showUsers(Model model, HttpServletRequest request, HttpSession session) {
		
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
		
		List<User_imo_65> usersList = userDAO.getAllUsers();
		User_imo_65 user = new User_imo_65();
		model.addAttribute("user",user);
		
		

		model.addAttribute("UsersList", usersList);

		return "UserMGMT";
	}

	/**
	 * 
	 * @param user object received from previous form will be saved on the database.
	 * @param model model object received after creating a new user on the post form
	 * @return forwarding View page
	 */
	@PostMapping("/createNewUser")
	public String createNewUser(@ModelAttribute("user") User_imo_65 user, Model model) {
		Date date = new Date();
		user.setRegDate(date);
		user.setStatus("Logged off");

		if (userDAO.createNewUser(user)) {
			User_imo_65 lastUser = userDAO.getLastUser();
			model.addAttribute("success", "user " + lastUser.getId() + " has been added!");
		}
			
		else
			model.addAttribute("error", "There was an error adding the User!");
		
		user = new User_imo_65();
		model.addAttribute("user", user);

		List<User_imo_65> usersList = userDAO.getAllUsers();
		model.addAttribute("UsersList", usersList);

		return "UserMGMT";
	}
	/**
	 * 
	 * @param id  from the USERID table field, used to select and delete a user
	 * @param model, on deletion wont receive any extra attribute.
	 * @return forwarding View page
	 */
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam(name="id", required = true) int id, Model model) {
		
		if (userDAO.deleteUser(id))
			model.addAttribute("success", "User " + id + " has been deleted!");
		else
			model.addAttribute("error", "There was an error deleting User " + id);
		
		
		User_imo_65 user = new User_imo_65();
		model.addAttribute("user",user);
		List<User_imo_65> userList = userDAO.getAllUsers();
		model.addAttribute("UsersList", userList);		
		
		return "UserMGMT";
	}
	
	/**
	 * 
	 * @param id needed to load the user to be edited from database, it will be retrieved and its information will be on the form
	 * @param model Model passed by View
	 * @return edit user get type
	 */
	@GetMapping("/EditUser")
	public String editUser(@RequestParam(name="id", required = true) int id, Model model) {
				
		
		User_imo_65 user = new User_imo_65();
		user=userDAO.getUserbyId(id);
		model.addAttribute("user",user);
		return "EditUser";
	}
	/**
	 * 
	 * @param user receives the modified user information that will be updated on the user table
	 * @param model object model, used to display the user list
	 * @return forwarding View page
	 */
	@PostMapping("/EditUser")
	public String userEdited(@ModelAttribute("user") User_imo_65 user, Model model) {
		
		user.setStatus("Logged off");
		
		System.out.println(""+user.getId()+user.getfName()+user.getLName()+user.getEmail());
		
		
		if (userDAO.updateUser(user)) {
			
			model.addAttribute("success", "user" + user.getId() + " has been edited!");
		}
			
		else
			model.addAttribute("error", "There was an error editing the User! " + user.getId());

		List<User_imo_65> usersList = userDAO.getAllUsers();
		model.addAttribute("UsersList", usersList);
		
		return "UserMGMT";
	}
	

}
