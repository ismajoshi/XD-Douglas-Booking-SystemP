package com.csis3275.controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csis3275.dao.UserSessionDAO_imo_65;
import com.csis3275.dao.loginDAO_imo_65;
import com.csis3275.model.UserSession_imo_65;
import com.csis3275.model.User_imo_65;
import com.csis3275.model.loginUser_imo_65;

@Controller
@RequestMapping("/")
public class loginController_imo_65 {

	@Autowired
	loginDAO_imo_65 loginDAO;
	
	@Autowired
	UserSessionService_imo_65 userSessionServiceObj;

	@ModelAttribute("user")
	public User_imo_65 userlogin() {
		return new User_imo_65();
	}

	@GetMapping("")
	public String Login(Model model) {
		loginUser_imo_65 user = new loginUser_imo_65();
		model.addAttribute("login", user);

		return "login";
	}
	
	@GetMapping("/dbsHome")
	public String homePage(HttpServletRequest request, HttpSession session, Model model) {
		return "dbsHome";
	}

	@PostMapping("/login")
	private String postLogin(@ModelAttribute("login") loginUser_imo_65 user, HttpServletRequest request, HttpSession session, Model model) {
		
		String Result = "login";

		if ( authenticate(user.getLmail(), user.getLpassword(), request)) {

			//Register User Session
			if(userSessionServiceObj.registerUserSession(session.getId(), user.getLmail())) {

				model.addAttribute("lsuccess", "Logged as " + user.getLmail());
				Result = "dbsHome";
			}
			else {

				System.out.println("Error on User Session registration!");
				model.addAttribute("lerror", "Error on User Session registration.");
				Result = "login";
			}
			
		}
		else {
			model.addAttribute("lerror", "User Login failed! Please, check the entered data and try again.");
			Result = "login";
		}

		return Result;
	}

	@GetMapping("/login/out")
	public String Logout(HttpServletRequest request, HttpSession session, Model model) {
		
		
		loginUser_imo_65 user = new loginUser_imo_65();
		model.addAttribute("login", user);
		
		//Inactive User Session
		if(!userSessionServiceObj.inactiveUserSession(session.getId())) {

			System.out.println("Error inactiving User Session!");
			model.addAttribute("lerror", "Error on User Session registration!");
		}
		
		return "login";
	}

	public boolean authenticate(String email, String password, HttpServletRequest request) {
		loginUser_imo_65 user = new loginUser_imo_65();
		user.setLmail(email);
		;
		user.setLpassword(password);
		boolean auth = false;
		loginDAO_imo_65 dao = new loginDAO_imo_65();

		//TODO can be removed
		if (dao.UserLogin(user)) {
			request.getSession().setAttribute("luser", user.getLmail());
			request.getSession().setAttribute("lpassword", user.getLpassword());
			auth = true;
		}

		return auth;
	}

}
