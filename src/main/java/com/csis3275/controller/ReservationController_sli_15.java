/**
 * 
 */
package com.csis3275.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.dao.ReservationDAOImpl_sli_15;
import com.csis3275.dao.RoomDAOImpl_sli_15;
import com.csis3275.dao.UserMGMTDAO_imo_65;
import com.csis3275.model.Reservation_sli_15;
import com.csis3275.model.Room_sli_15;
import com.csis3275.model.User_imo_65;

/**
 * @author Scott Linden 300327715
 *
 */
@Controller
@RequestMapping("/reservation")
public class ReservationController_sli_15 {
	
	@Autowired
	UserSessionService_imo_65 userSessionServiceObj;	
	@Autowired
	ReservationDAOImpl_sli_15 reservationDaoImpl;
	@Autowired
	UserMGMTDAO_imo_65 userDaoImpl;
	@Autowired
	RoomDAOImpl_sli_15 roomDAOImpl; 
	@Autowired
    private JavaMailSender sender;
	
	@ModelAttribute("Reservation")
	public Reservation_sli_15 reservation() {
		return new Reservation_sli_15();
	}
	
	@GetMapping("/showAllReservations")
	public String showAllReservations(HttpSession session, Model model) {
		
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
		
		
		List<Reservation_sli_15> allReservations = reservationDaoImpl.getAllReservations();
		
		model.addAttribute("allReservations", allReservations);
		model.addAttribute("reservation", new Reservation_sli_15());
		return "Reservation";
	}
	@GetMapping("/new")
	public String createReservation(HttpSession session, Model model) {
		
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

		model.addAttribute("reservation", new Reservation_sli_15());
		model.addAttribute("crudFunction", "create");	
		model.addAttribute("headerMessage", "Add New Reservation");
				
		return "ReservationForm";
	}
	
	@PostMapping("/create")
	public String newReservation(@ModelAttribute("reservation") Reservation_sli_15 reservation, Model model) {

		if (reservationDaoImpl.createReservation(reservation))
		{
			Reservation_sli_15 newreservation = reservationDaoImpl.getLastReservation();
			
			User_imo_65 user = userDaoImpl.getUserbyId(newreservation.getUserID());
			MimeMessage message = sender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message);

	        try {
	            helper.setTo(user.getEmail());
	            helper.setText("Greetings " + user.getfName() + ",\n\n"
	            		+ "You have successfully used the Douglas Booking System.\n"
	            		+ "Here are your reservation details:\n\n"
	            		+ "Reservation Title:\t" + newreservation.getTitle()
	            		+ "\nReservation Time:\t" + newreservation.getStart()
	            		+ "\nReservation Room:\t" + roomDAOImpl.getRoom(newreservation.getRoomID()).getNumber()
	            		+ "\nReservation Type:\t" + newreservation.getType()
	            		+ "\nReservation Attendees:\t" + newreservation.getAteendees()
	            		+ "\n\n Thank you for using the Douglas Booking System");
	            helper.setSubject("DBS Reservation " + newreservation.getReservationID() + " Created");
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            model.addAttribute("error", "There was an error sending the email!");
	        }
	        sender.send(message);
			model.addAttribute("success", "Reservation " + newreservation.getReservationID() + " has been added and an email reminder has been sent!");
		}
		else
			model.addAttribute("error", "There was an error making the Reservation!");

		List<Reservation_sli_15> allReservations = reservationDaoImpl.getAllReservations();
		model.addAttribute("allReservations", allReservations);
		
		 

		return "Reservation";
	}
	
	@GetMapping("edit")
	public String updateForm(@RequestParam(name="id", required = true) int id, Model model, HttpSession session) {
		
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
		
		Reservation_sli_15 reservation = reservationDaoImpl.getReservation(id);
		
		model.addAttribute("reservation", reservation);
		model.addAttribute("crudFunction", "update");
		model.addAttribute("headerMessage", "Edit Reservation");
		
		return "ReservationForm";
	}
	
	@PostMapping("/update")
	public String updateReservation(@ModelAttribute("reservation") Reservation_sli_15 reservation, Model model, HttpSession session) {
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

		if (reservationDaoImpl.updateReservation(reservation))
		{			
			User_imo_65 user = userDaoImpl.getUserbyId(reservation.getUserID());
			MimeMessage message = sender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message);

	        try {
	            helper.setTo(user.getEmail());
	            helper.setText("Greetings " + user.getfName() + ",\n\n"
	            		+ "You have successfully updated your reservation in the Douglas Booking System.\n"
	            		+ "Here are your new reservation details:\n\n"
	            		+ "Reservation Title:\t" + reservation.getTitle()
	            		+ "\nReservation Time:\t" + reservation.getStart()
	            		+ "\nReservation Room:\t" + roomDAOImpl.getRoom(reservation.getRoomID()).getNumber()
	            		+ "\nReservation Type:\t" + reservation.getType()
	            		+ "\nReservation Attendees:\t" + reservation.getAteendees()
	            		+ "\n\n Thank you for using the Douglas Booking System");
	            helper.setSubject("Reservation " + reservation.getReservationID() + " Updated");
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            model.addAttribute("error", "There was an error sending the email!");
	        }
	        sender.send(message);
			model.addAttribute("success", "Reservation " + reservation.getReservationID() + " has been updated and an email reminder has been sent!");

		}
		else
			model.addAttribute("error", "There was an error updating the Reservation!");

		List<Reservation_sli_15> allReservations = reservationDaoImpl.getAllReservations();
		model.addAttribute("allReservations", allReservations);

		return "Reservation";
	}
	
	@GetMapping("/delete")
	public String deleteReservation(@RequestParam int id, @ModelAttribute("reservation") Reservation_sli_15 room, Model model, HttpSession session) {
		
		
		if (reservationDaoImpl.deleteReservation(id))
		{
			model.addAttribute("success", "Reservation " + id + " has been deleted!");
		}
		else
			model.addAttribute("error", "There was an error deleting Reservation " + id);
		

		List<Reservation_sli_15> allReservations = reservationDaoImpl.getAllReservations();
		model.addAttribute("allReservations", allReservations);		
		
		return "Reservation";
	}
	

	@GetMapping("/newFromRoomSearch")
	public String createReservationFromRoomSearch(HttpSession session, @RequestParam(name="id", required = true) int pRoomId, @RequestParam(name="startDatetime") String pStartDatetime, @RequestParam(name="endDatetime") String pEndDatetime, Model model) {

		//Define Date Format
		SimpleDateFormat datetimeFormatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		
		//Create Reservation Form Object
		Reservation_sli_15 reservatioObj = new Reservation_sli_15();
		//Set paramenters
		reservatioObj.setRoomID(pRoomId); //RoomId
		//Start Datetime
		if(!pStartDatetime.isBlank()) {
			try {
				reservatioObj.setStart(datetimeFormatter.parse(pStartDatetime));
			}catch (Exception ex) {
				System.out.println("Invalid pStartDatetime: " + pStartDatetime);
			}
		}
		//End Datetime
		if(!pEndDatetime.isBlank()) {
			try {
				reservatioObj.setEnd(datetimeFormatter.parse(pEndDatetime));
			}catch (Exception ex) {
				System.out.println("Invalid pStartDatetime: " + pEndDatetime);
			}
		}
		
		model.addAttribute("reservation", reservatioObj);
		model.addAttribute("crudFunction", "create");	
		model.addAttribute("headerMessage", "Add New Reservation");
				
		return "ReservationForm";
	}

	
	
	
	@ModelAttribute("users")
	public List<String> initializeUserList() {
		List<User_imo_65> users = userDaoImpl.getAllUsers();
		List<String> ids = new ArrayList<String>();
		for (User_imo_65 var : users)
			ids.add(String.valueOf(var.getId()));
		return ids;
	}
	
	@ModelAttribute("rooms")
	public List<Room_sli_15> initializeRoomList() {
		List<Room_sli_15> rooms = roomDAOImpl.getAllRooms();	
		return rooms;
	}
	
	@ModelAttribute("statusList") 
    public List<String> initializeStatusList() { 
        List<String> statusList = new ArrayList<String>();       
        statusList.add(""); 
        statusList.add("Active");  
        statusList.add("Cancelled"); 
        return statusList; 
    }
		
	@ModelAttribute("reservationTypeList") 
    public List<String> initializeReservationTypeList() { 
        List<String> typeList = new ArrayList<String>();       
        typeList.add(""); 
        typeList.add("Class");  
        typeList.add("Study Group"); 
        typeList.add("Presentation");
        typeList.add("Meeting");
        
        return typeList; 
    }
}
