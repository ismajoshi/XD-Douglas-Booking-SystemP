package com.csis3275.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.csis3275.model.RoomSearch_rso_35;

/**
 * Validator class to handle form errors regarding RoomSearch class.
 * @author Ricardo dos Santos Alves de Souza
 *
 */
@Component
public class RoomSearchFormValidator_rso_35 implements Validator {

	/**
	 * Define which objects can be validated by this Validator
	 */
	@Override
	public boolean supports(Class<?> paramClass) {

		return RoomSearch_rso_35.class.equals(paramClass);
	}

	/**
	 * Implements the validation logic
	 */
	@Override
	public void validate(Object obj, Errors errors) {
		
		RoomSearch_rso_35 roomSerachObj = (RoomSearch_rso_35) obj;
		
		//If searching for Only Available Rooms
		if(roomSerachObj.isOnlyAvailable()) {

			//Check if both Desired Dates are filled and 
			if(roomSerachObj.getDesiredStartDatetime() == null)
			{
				errors.rejectValue("desiredStartDatetime", "invalidDatetime", new Object[]{"'Desired Start Date/Time'"}, "'Desired Start Date/Time' cannot be empty");
			}
			if(roomSerachObj.getDesiredEndDatetime() == null)
			{
				errors.rejectValue("desiredEndDatetime", "invalidDatetime", new Object[]{"'Desired End Date/Time'"}, "'Desired End Date/Time' cannot be empty");
			}

			//Check if Desired Start Date is before Desired End Date  
			if (roomSerachObj.getDesiredStartDatetime() != null && roomSerachObj.getDesiredEndDatetime() != null && !roomSerachObj.getDesiredStartDatetime().before(roomSerachObj.getDesiredEndDatetime()))
			{
				errors.rejectValue("desiredEndDatetime", "EndDatetimeBeforeStartDatetime", null, "'Desired End Date/Time' must be after 'Desired Start Date/Time'");
			}
			
		}

	}

}
