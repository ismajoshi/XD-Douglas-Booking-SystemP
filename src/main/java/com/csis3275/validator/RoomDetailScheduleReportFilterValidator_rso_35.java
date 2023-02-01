package com.csis3275.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.csis3275.model.RoomDetailScheduleReportFilter_rso_35;

/**
 * Validator class to handle form errors regarding RoomDetailScheduleReportFilter class.
 * @author Ricardo dos Santos Alves de Souza
 *
 */
@Component
public class RoomDetailScheduleReportFilterValidator_rso_35 implements Validator {
	
	/**
	 * Define which objects can be validated by this Validator
	 */
	@Override
	public boolean supports(Class<?> paramClass) {

		return RoomDetailScheduleReportFilter_rso_35.class.equals(paramClass);
	}

	/**
	 * Implements the validation logic
	 */
	@Override
	public void validate(Object obj, Errors errors) {
		
		RoomDetailScheduleReportFilter_rso_35 roomDetailScheduleReportFilterObj = (RoomDetailScheduleReportFilter_rso_35) obj;
		
		//check roomId
		if(roomDetailScheduleReportFilterObj.getRoomId() <= 0)
		{
			errors.rejectValue("roomId", "invalid_value", new Object[]{"'Room ID'"}, "'Room ID is invalid!'");
		}
		
		//Check if both Desired Dates are filled and 
		if(roomDetailScheduleReportFilterObj.getSelectedDate() == null)
		{
			errors.rejectValue("selectedDate", "invalidDatetime", new Object[]{"'Selected Date'"}, "'Selected Date' cannot be empty!");
		}		
		
	}	

}
