package com.csis3275.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.csis3275.controller.loginController_imo_65;
import com.csis3275.dao.UserMGMTDAO_imo_65;
import com.csis3275.dao.JDBCLoginCheck_imo_65;
import com.csis3275.model.loginUser_imo_65;
import com.csis3275.controller.loginController_imo_65;

/**
 * Spring MVC controller to handle GroupDescription Model
 * 
 * @author Ricardo dos Santos Alves de Souza
 *
 */
@Controller
@RequestMapping("/GroupDesc")
public class GroupDescriptionController {
	
	
	@Autowired
	UserSessionService_imo_65 userSessionServiceObj;
	
	@Autowired
	public static HttpSession session() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true); // true == allow create
	}
	

	

	/**
	 * Instantiate a GroupDescription object and add it to be display on the View
	 * 
	 * @param request View Servelt request object
	 * @param model used form model
	 * @param session Http Session object
	 * @return forward page to show
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String createGroupDescription(HttpServletRequest request,ModelMap model, HttpSession session) {
		String errorMessage;
		switch(userSessionServiceObj.validateType(session.getId(), UserSessionService_imo_65.Privileges.student)) {
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
		
		model.addAttribute("lsuccess", "Logged as " + request.getSession().getAttribute("luser"));
		

		// return "xdTeamDescription";
		return "dbsHome";
		// return "accountPage";
	}

	@RequestMapping("/xdTeamDescription")
	public String displayTeam() {
		return "xdTeamDescription";
	}

	@GetMapping("/dbsHome")
	public String displayHome(HttpServletRequest request, Model model, HttpSession session) {
		
		
		JDBCLoginCheck_imo_65 logcheck = new JDBCLoginCheck_imo_65();
		if( logcheck.logincheck(request)) {
			loginUser_imo_65 luser = new loginUser_imo_65();
			model.addAttribute("login", luser);
			return "login";
		}
		
		model.addAttribute("lsuccess", "Logged as " + request.getSession().getAttribute("luser"));
		return "dbsHome";
	}

	/**
	 * Just call the createGroupDescription created to RequestMethod GET
	 * 
	 * @param result View x Controller binding
	 * @param model  Model passed by View
	 * @return model object
	 */
	

}
