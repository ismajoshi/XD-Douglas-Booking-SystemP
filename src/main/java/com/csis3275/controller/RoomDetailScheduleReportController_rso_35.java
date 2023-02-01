package com.csis3275.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.dao.RoomDAOImpl_sli_15;
import com.csis3275.model.RoomDetailScheduleReportComposite_rso_35;
import com.csis3275.model.RoomDetailScheduleReportFilter_rso_35;
import com.csis3275.model.RoomSearch_rso_35;
import com.csis3275.model.Room_sli_15;
import com.csis3275.validator.RoomDetailScheduleReportFilterValidator_rso_35;

/**
 * Controller class to implement the interaction View x Controller associates to the Room Detail and Schedule Report Feature 
 * 
 * @author Ricardo dos Santos Alves de Souza
 *
 */
@Controller
@RequestMapping("/room_det_sch_report")
public class RoomDetailScheduleReportController_rso_35 {
	
	@Autowired
	RoomDetailScheduleReportService_rso_35 roomDetailScheduleReportServiceObj;
	
	@Autowired
	RoomDetailScheduleReportFilterValidator_rso_35 roomDetailScheduleReportFilterValidatorObj;	
	
	@Autowired
	RoomDAOImpl_sli_15 roomDAOImplObj;
	@Autowired
	UserSessionService_imo_65 userSessionServiceObj;
	
	/**
	 * Bind Validator to RoomDetailScheduleReportFilter form obj
	 * @param binder Binder passed by the View layer
	 */
	@InitBinder("roomDetailScheduleReportFilterObj")
	private void initBinderRoomDetailScheduleReportFilterForm(WebDataBinder binder) {
		binder.addValidators(roomDetailScheduleReportFilterValidatorObj);
	}

	/**
	 * Initialize the roomDetailScheduleReportFilterObj
	 * @return empty form obj
	 */
	@ModelAttribute("roomDetailScheduleReportFilterObj")
	public RoomDetailScheduleReportFilter_rso_35 setupInitialForm() {
		
		return new RoomDetailScheduleReportFilter_rso_35();
	}
	
	// ---------------- Pages Call methods ----------------	
	
	/**
	 * Initialize the Form
	 * @param session HTTP session obj
	 * @param model View Model Obj
	 * @return forward View Page
	 */
	@GetMapping("/initialize")
	public String initialize(HttpSession session, Model model) {
		
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
		
		
		return "roomDetailScheduleReportForm";
	}
	
	/**
	 * Run the report and return data
	 * @param roomDetailScheduleReportFilterObj report filter obj
	 * @param bindingResult View Bind obj
	 * @param model View Model Obj
	 * @return forward View Page
	 */
	@PostMapping(path="/showReport", params = "show_report")
	public String showReport(@ModelAttribute("roomDetailScheduleReportFilterObj") @Validated RoomDetailScheduleReportFilter_rso_35 roomDetailScheduleReportFilterObj, BindingResult bindingResult, Model model) {

		//Check validation
		if(bindingResult != null && bindingResult.hasErrors()) {

			System.out.println("RoomDetailScheduleReportForm validation error identified: error count = " + bindingResult.getErrorCount());
			model.addAttribute("roomDetailScheduleReportFilterObj", roomDetailScheduleReportFilterObj);
			return "roomDetailScheduleReportForm";
		}		
		
		//Get the Room Detail and Schedule complete data
		try {
			
			RoomDetailScheduleReportComposite_rso_35 roomDetailScheduleReportCompositeObj = roomDetailScheduleReportServiceObj.getRoomDetailScheduleReport(roomDetailScheduleReportFilterObj.getRoomId(), roomDetailScheduleReportFilterObj.getSelectedDate());
			
			//Set composite data to View page
			model.addAttribute("roomDetailScheduleReportCompositeObj", roomDetailScheduleReportCompositeObj);
			
			
		} catch(Exception ex) {
			//Set return message
			model.addAttribute("errorMessage", "Error getting Room detail and Schedule data!");			
		}
		
		//Set form again
		model.addAttribute("roomDetailScheduleReportFilterObj", roomDetailScheduleReportFilterObj);		

		return "roomDetailScheduleReportForm";
		
	}
	
	/**
	 * Run the report for the previous day of the select date
	 * @param roomDetailScheduleReportFilterObj report filter obj
	 * @param bindingResult View Bind obj
	 * @param model View Model Obj
	 * @return forward View Page

	 */
	@PostMapping(path="/showReport", params = "show_previous_day")
	public String showPreviousDayReport(@ModelAttribute("roomDetailScheduleReportFilterObj") @Validated RoomDetailScheduleReportFilter_rso_35 roomDetailScheduleReportFilterObj, BindingResult bindingResult, Model model) {

		//Check validation
		if(bindingResult.hasErrors()) {

			System.out.println("RoomDetailScheduleReportForm validation error identified: error count = " + bindingResult.getErrorCount());
			model.addAttribute("roomDetailScheduleReportFilterObj", roomDetailScheduleReportFilterObj);
			return "roomDetailScheduleReportForm";
		}		
		
		//Set Filter back One Day
		Calendar calendarObj = Calendar.getInstance();
		calendarObj.setTime(roomDetailScheduleReportFilterObj.getSelectedDate());
		calendarObj.add(Calendar.DATE, -1);
		roomDetailScheduleReportFilterObj.setSelectedDate(calendarObj.getTime());
		
		//Call Show Report Method using updated day
		return showReport(roomDetailScheduleReportFilterObj, bindingResult, model);
		
	}
	
	/**
	 * Run the report for the next day of the select date
	 * @param roomDetailScheduleReportFilterObj report filter obj
	 * @param bindingResult View Bind obj
	 * @param model View Model Obj
	 * @return forward View Page

	 */
	@PostMapping(path="/showReport", params = "show_next_day")
	public String showNextDayReport(@ModelAttribute("roomDetailScheduleReportFilterObj") @Validated RoomDetailScheduleReportFilter_rso_35 roomDetailScheduleReportFilterObj, BindingResult bindingResult, Model model) {

		//Check validation
		if(bindingResult.hasErrors()) {

			System.out.println("RoomDetailScheduleReportForm validation error identified: error count = " + bindingResult.getErrorCount());
			model.addAttribute("roomDetailScheduleReportFilterObj", roomDetailScheduleReportFilterObj);
			return "roomDetailScheduleReportForm";
		}		
		
		//Set Filter back One Day
		Calendar calendarObj = Calendar.getInstance();
		calendarObj.setTime(roomDetailScheduleReportFilterObj.getSelectedDate());
		calendarObj.add(Calendar.DATE, 1);
		roomDetailScheduleReportFilterObj.setSelectedDate(calendarObj.getTime());
		
		//Call Show Report Method using updated day
		return showReport(roomDetailScheduleReportFilterObj, bindingResult, model);
		
	}	
	
	/**
	 * Run  the report using the Room Id and Date passed
	 * @param pRoomId Selected Room ID
	 * @param pStartDatetime Date to use to run the report
	 * @param model View Model Obj
	 * @return forward View Page
	 */
	@GetMapping("/showReportFromRoomSearch")
	public String showReportFromRoomSearch(@RequestParam(name="id", required = true) int pRoomId, 
											@RequestParam(name="startDatetime") String pStartDatetime,
											Model model) {
		
		//Define Date Format
		SimpleDateFormat datetimeFormatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		
		//Create the Filter using the data passed
		RoomDetailScheduleReportFilter_rso_35 roomDetailScheduleReportFilterObj = new  RoomDetailScheduleReportFilter_rso_35();
		//Set the Room Id
		roomDetailScheduleReportFilterObj.setRoomId(pRoomId);
		
		//Set the Date
		try {
			roomDetailScheduleReportFilterObj.setSelectedDate(datetimeFormatter.parse(pStartDatetime));
			
		}catch (Exception ex) {
			System.out.println("showReportFromRoomSearch - Invalid pStartDatetime: " + pStartDatetime);
			model.addAttribute("warningMessage", "Passed Date/Time is empty or invalid. Using current date!");
			roomDetailScheduleReportFilterObj.setSelectedDate(new Date());
		}
		
		//Call Show Report method
		return showReport(roomDetailScheduleReportFilterObj, null, model);
		
	}	
	
	
	// ---------------- List Attributes methods ----------------
	
	/**
	 * Initialize the List of Rooms available on the system
	 * @return List of Rooms to select
	 */
	@ModelAttribute("currentRoomList") 
    public List<Room_sli_15> initializeRoomList() { 
 
    	return roomDAOImplObj.getAllRooms();
    }	
	

}
