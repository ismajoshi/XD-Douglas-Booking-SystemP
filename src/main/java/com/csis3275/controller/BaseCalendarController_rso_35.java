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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.dao.BaseCalendarDAOImpl_rso_35;
import com.csis3275.dao.BaseCalendarUnavailableDaysDAOImpl_rso_35;
import com.csis3275.model.BaseCalendarUnavailableDays_rso_35;
import com.csis3275.model.BaseCalendar_rso_35;
import com.csis3275.validator.BaseCalendarFormValidator_rso_35;
import com.csis3275.validator.BaseCalendarUnavailableDaysFormValidator_rso_35;

/**
 * This class processes all the listing, creation, modification and deleting functions regarding BaseCalendar and BaseCalendarUnavailableDays. 
 * 
 * @author Ricardo dos Santos Alves de Souza
 *
 */
@Controller
@RequestMapping("/base_calendar")
public class BaseCalendarController_rso_35 {
	
	@Autowired
	UserSessionService_imo_65 userSessionServiceObj;
	
	@Autowired
	BaseCalendarDAOImpl_rso_35 baseCalendarDaoImpl;
	
	@Autowired
	BaseCalendarUnavailableDaysDAOImpl_rso_35 baseCalendarUnavailableDaysDaoImpl;
	
	@Autowired
	BaseCalendarFormValidator_rso_35 baseCalendarValidator;

	@Autowired
	BaseCalendarUnavailableDaysFormValidator_rso_35 baseCalendarUnavailableDaysValidator;	

	@InitBinder("baseCalendarObj")
	private void initBinderBaseCalendar(WebDataBinder binder) {
		binder.addValidators(baseCalendarValidator);
	}
	
	@InitBinder("baseCalendarUnavailableDayObj")
	private void initBinderBaseCalendarUnvavailableDays(WebDataBinder binder) {
		binder.addValidators(baseCalendarUnavailableDaysValidator);
	}
	
	@ModelAttribute("baseCalendarObj")
	public BaseCalendar_rso_35 setupAddForm() {
		
		return new BaseCalendar_rso_35();
	}
	
	// ---------------- Pages Call methods ----------------		
	
	/**
	 * Thats a GET request from the browser to the URL below
	 * @param session opened by client browser
	 * @param model passed by View
	 * @return forwarded View page
	 */
	@GetMapping("/showAll")
	public String showBaseCalendars(HttpSession session, Model model) {
		
		//Check necessary privilege
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

		// Get a list of Base Calendars from the controller
		List<BaseCalendar_rso_35> baseCalendarList = baseCalendarDaoImpl.getAllRows();

		// Add the results to the model
		model.addAttribute("baseCalendarList", baseCalendarList);

		return "baseCalendarList";
	}
	
	/**
	 * Thats a GET request from the browser to the URL below
	 * @param session opened by client browser
	 * @param model passed by View
	 * @return forwarded View page
	 */
	@GetMapping("/new")
	public String createBaseCalendar(HttpSession session, Model model) {

		//Add attributes to View
		model.addAttribute("baseCalendarObj", new BaseCalendar_rso_35());
		model.addAttribute("crudFunction", "create");		
		model.addAttribute("headerMessage", "Create New Base Calendar");
		
		//Base Calendar Unavailable Days
		model.addAttribute("baseCalendarUnavailableDayObj", new BaseCalendarUnavailableDays_rso_35());
		
		return "baseCalendarForm";
	}	

	/**
	 * Thats a GET request from the browser to the URL below
	 * @param pId object ID been modified
	 * @param model passed by View
	 * @return forwarded View page
	 */
	@GetMapping("/edit")
	public String editBaseCalendar(@RequestParam(name="id", required = true) int pId, Model model) {

		//Get Base Calendar by ID
		BaseCalendar_rso_35 baseCalendarObj = baseCalendarDaoImpl.getRowById(pId);

		//Add attributes to View		
		model.addAttribute("baseCalendarObj", baseCalendarObj);
		model.addAttribute("crudFunction", "update");		
		model.addAttribute("headerMessage", "Edit Base Calendar");
		
		//Base Calendar Unavailable Days
		model.addAttribute("baseCalendarUnavailableDayObj", new BaseCalendarUnavailableDays_rso_35(baseCalendarObj.getId()));			
		model.addAttribute("baseCalendarUnavailableDayList", baseCalendarUnavailableDaysDaoImpl.getRowsByBaseCalendarId(baseCalendarObj.getId()));			

		return "baseCalendarForm";
	}
	
	// ---------------- CRUD methods ----------------	
	
	/**
	 * Handle Form Create Base Calendar
	 * @param pBaseCalendarObj object been created
	 * @param bindingResult View x Validator binding result
	 * @param model passed by View
	 * @return forwarded View page
	 */
	@PostMapping("/create")
	public String saveNewBaseCalendar(@ModelAttribute("baseCalendarObj") @Validated @RequestBody BaseCalendar_rso_35 pBaseCalendarObj, BindingResult bindingResult, Model model) {

		//Check validation
		if(bindingResult.hasErrors()) {
			System.out.println("Base Calendar Validation error identified: error count = " + bindingResult.getErrorCount());

			//Add attributes to View
			model.addAttribute("baseCalendarObj", pBaseCalendarObj);
			model.addAttribute("crudFunction", "update");		
			model.addAttribute("headerMessage", "Edit Base Calendar");
			
			//Base Calendar Unavailable Days
			model.addAttribute("baseCalendarUnavailableDayObj", new BaseCalendarUnavailableDays_rso_35());

			return "baseCalendarForm";
		}			
		
		//Call the DAO creation method passing the new object to be inserted and check the result
		if(baseCalendarDaoImpl.createNewRow(pBaseCalendarObj)) 
		{
			//Get last existing Row inserted and return the new ID
			BaseCalendar_rso_35 lastInsertBaseCalendar = baseCalendarDaoImpl.getLastInsertedRow();
			//Set return message
			model.addAttribute("successMessage", "Created new Base Calendar with ID " + lastInsertBaseCalendar.getId());
		}
		else
		{
			//Set return message
			model.addAttribute("errorMessage", "Error saving new Base Calendar!");			
		}		

		// Get a list of Base Calendars from the controller
		List<BaseCalendar_rso_35> baseCalendarList = baseCalendarDaoImpl.getAllRows();
		model.addAttribute("baseCalendarList", baseCalendarList);

		return "baseCalendarList";
	}
	
	/**
	 * Handle Form Edit Base Calendar
	 * @param pBaseCalendarObj object been modified
	 * @param bindingResult View x Validator binding result
	 * @param model passed by View
	 * @return forwarded View page
	 */
	@PostMapping("/update")
	public String updateBaseCalendar(@ModelAttribute("baseCalendarObj") @Validated @RequestBody BaseCalendar_rso_35 pBaseCalendarObj, BindingResult bindingResult, Model model) {
		
		//Check validation
		if(bindingResult.hasErrors()) {

			System.out.println("Base Calendar Validation error identified: error count = " + bindingResult.getErrorCount());

			//Add attributes to View
			model.addAttribute("crudFunction", "update");		
			model.addAttribute("headerMessage", "Edit Base Calendar");
			model.addAttribute("baseCalendarObj", pBaseCalendarObj);
			
			//Base Calendar Unavailable Days
			model.addAttribute("baseCalendarUnavailableDayObj", new BaseCalendarUnavailableDays_rso_35());
			model.addAttribute("baseCalendarUnavailableDayList", baseCalendarUnavailableDaysDaoImpl.getRowsByBaseCalendarId(pBaseCalendarObj.getId()));			
			
			return "baseCalendarForm";
		}			

		//Call DAO update method passing the respective object to be updated and check the result
		if(baseCalendarDaoImpl.updateRow(pBaseCalendarObj)) 
		{
			//Set return message
			model.addAttribute("successMessage", "Base Calendar with ID " + pBaseCalendarObj.getId() + " updated!");			
		}
		else
		{
			//Set return message
			model.addAttribute("errorMessage", "Error updating Base Calendar with ID " + pBaseCalendarObj.getId() + "!");		
		}	

		// Get a list of Base Calendars from the controller
		List<BaseCalendar_rso_35> baseCalendarList = baseCalendarDaoImpl.getAllRows();
		model.addAttribute("baseCalendarList", baseCalendarList);

		return "baseCalendarList";
	}
	
	/**
	 * Delete an existing Base Calendar
	 * @param pId object ID been deleted
	 * @param model passed by View
	 * @return forwarded View page
	 */
	@GetMapping("/delete")
	public String deleteBaseCalendar(@RequestParam(name="id", required = true) int pId, Model model) {
		
		//Check if there are Rooms using the Base Calendar. If so, the record cannot be deleted.
		int countRelatedRooms = baseCalendarDaoImpl.getCountRelatedRooms(pId);
		if(countRelatedRooms > 0)
		{
			//Set return message
			model.addAttribute("errorMessage", "There are " + countRelatedRooms + " Rooms using the Base Calendar with ID" + pId +
												". To delete it, first change all the Rooms to use another Base Calendar!");
		}
		//Call DAO delete method passing the respective object to be deleted  and check the result
		else if(baseCalendarDaoImpl.deleteRow(pId)) 
		{
			//Set return message
			model.addAttribute("successMessage", "Base Calendar with ID " + pId + " deleted!");				
		}
		else
		{
			//Set return message
			model.addAttribute("errorMessage", "Error deleting Base Calendar with ID " + pId + "!");					
		}
			
		
		// Get a list of Base Calendars from the controller
		List<BaseCalendar_rso_35> baseCalendarList = baseCalendarDaoImpl.getAllRows();
		model.addAttribute("baseCalendarList", baseCalendarList);

		return "baseCalendarList";
	}
	
	// ---------------- Base Calendar Unavailable Days ----------------
	
	/**
	 * Handle Form Add Base Calendar Unavailable Day
	 * @param bindingResult View x Validator binding result
	 * @param newUnavailableDay object been added
	 * @param model passed by View
	 * @return forwarded View page
	 */
	@PostMapping("/addUnavailableDay")
	public String addUnavailableDay(@ModelAttribute("baseCalendarUnavailableDayObj") @Validated @RequestBody BaseCalendarUnavailableDays_rso_35 newUnavailableDay,
									BindingResult bindingResult, Model model) {
		
		//Check validation
		if(bindingResult.hasErrors()) {
			System.out.println("Unavailable Day Validation error identified: error count = " + bindingResult.getErrorCount());
			
			//Add attributes to View		
			model.addAttribute("baseCalendarObj", baseCalendarDaoImpl.getRowById(newUnavailableDay.getBaseCalendarId()));
			model.addAttribute("crudFunction", "update");		
			model.addAttribute("headerMessage", "Edit Base Calendar");
			
			//Base Calendar Unavailable Days
			model.addAttribute("baseCalendarUnavailableDayObj", newUnavailableDay);
			model.addAttribute("baseCalendarUnavailableDayList", baseCalendarUnavailableDaysDaoImpl.getRowsByBaseCalendarId(newUnavailableDay.getBaseCalendarId()));			

			return "baseCalendarForm";
		}			

		//Call the DAO creation method passing the new object to be inserted and check the result
		if(baseCalendarUnavailableDaysDaoImpl.createNewRow(newUnavailableDay)) 
		{
			//Get last existing Row inserted and return the new ID
			BaseCalendarUnavailableDays_rso_35 lastInserted = baseCalendarUnavailableDaysDaoImpl.getLastInsertedRow();
			//Set return message
			model.addAttribute("successMessage", "Added new Unavailable Day with ID " + lastInserted.getId());
		}
		else
		{
			//Set return message
			model.addAttribute("errorMessage", "Error adding new Unavailable Day!");			
		}		

		//Add attributes to View		
		model.addAttribute("baseCalendarObj", baseCalendarDaoImpl.getRowById(newUnavailableDay.getBaseCalendarId()));
		model.addAttribute("crudFunction", "update");		
		model.addAttribute("headerMessage", "Edit Base Calendar");
		
		//Base Calendar Unavailable Days
		model.addAttribute("baseCalendarUnavailableDayObj", new BaseCalendarUnavailableDays_rso_35(newUnavailableDay.getBaseCalendarId()));
		model.addAttribute("baseCalendarUnavailableDayList", baseCalendarUnavailableDaysDaoImpl.getRowsByBaseCalendarId(newUnavailableDay.getBaseCalendarId()));			

		return "baseCalendarForm";
	}
	
	/**
	 * Delete an existing Base Calendar Unavailable Day
	 * @param pId object ID been removed
	 * @param pBaseCalendarId parent object ID associated to the object been removed
	 * @param model passed by View
	 * @return forwarded View page
	 */
	@GetMapping("/removeUnavailableDay")
	public String removeUnavailableDay(@RequestParam(name="id", required = true) int pId,
										@RequestParam(name="baseCalendarId", required = true) int pBaseCalendarId,			
										Model model) {

		//Call DAO delete method passing the respective object to be deleted  and check the result
		if(baseCalendarUnavailableDaysDaoImpl.deleteRow(pId)) 
		{
			//Set return message
			model.addAttribute("successMessage", "Unavailable Day with ID " + pId + " removed!");				
		}
		else
		{
			//Set return message
			model.addAttribute("errorMessage", "Error removing Unavailable Day with ID " + pId + "!");					
		}	
		
		//Add attributes to View		
		model.addAttribute("baseCalendarObj", baseCalendarDaoImpl.getRowById(pBaseCalendarId));
		model.addAttribute("crudFunction", "update");		
		model.addAttribute("headerMessage", "Edit Base Calendar");
		
		//Base Calendar Unavailable Days
		model.addAttribute("baseCalendarUnavailableDayObj", new BaseCalendarUnavailableDays_rso_35(pBaseCalendarId));
		model.addAttribute("baseCalendarUnavailableDayList", baseCalendarUnavailableDaysDaoImpl.getRowsByBaseCalendarId(pBaseCalendarId));			

		return "baseCalendarForm";
	}	

}
