"use strict";
/*
  Base Calendar Management JavaScript Functions
  @author: Ricardo dos Santos Alves de Souza
 */

//Check if the weekdaysStartTime is before weekdaysEndTime
function checkWeekdaysTimes() {
	
	var startTime = new Date(document.getElementById("weekdaysStartTime"));
	var endTime = new Date(document.getElementById("weekdaysEndTime"));
	var alertMessage = "Weekdays Start Time must be after Weekdays End Time!"
	
	//Call checkFunction
	if(!isStartEndTimesConsistent(startTime, endTime, alertMessage)) {
		document.getElementById("weekdaysStartTime").textContent = document.getElementById("weekdaysEndTime");
	}
}

function checkWeekenddaysTime() {
	
	var startTime = new Date(document.getElementById("weekenddaysStartTime"));
	var endTime = new Date(document.getElementById("weekenddaysEndTime"));
	var alertMessage = "Weekends days Start Time must be after Weekdays End Time!"
	
	//Call checkFunction
	if(!isStartEndTimesConsistent(startTime, endTime, alertMessage)) {
		document.getElementById("weekenddaysStartTime").textContent = document.getElementById("weekenddaysEndTime");
	}
}

function checkUnavailableTime() {
	
	var startTime = new Date(document.getElementById("unavailableStartTime"));
	var endTime = new Date(document.getElementById("unavailableEndTime"));
	var alertMessage = "Weekends days Start Time must be after Weekdays End Time!"
	
	//Call checkFunction
	if(!isStartEndTimesConsistent(startTime, endTime, alertMessage)) {
		document.getElementById("weekenddaysStartTime").textContent = document.getElementById("weekenddaysEndTime");
	}
}

//Compare Times and show a pop message if start > end
function isStartEndTimesConsistent(pStartTime, pEndTime, pAlertMessage) {
	
	if(pStartTime > pEndTime) {
		alert(pAlertMessage);
		return false;
	}
	else {
		return true;
	}	
}
