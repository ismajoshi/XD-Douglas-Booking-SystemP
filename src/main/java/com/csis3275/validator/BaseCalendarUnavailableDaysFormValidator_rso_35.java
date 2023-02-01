package com.csis3275.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.csis3275.model.BaseCalendarUnavailableDays_rso_35;

@Component
public class BaseCalendarUnavailableDaysFormValidator_rso_35 implements Validator {
	
	/**
	 * Define which objects can be validated by this Validator
	 */
	@Override
	public boolean supports(Class<?> paramClass) {

		return BaseCalendarUnavailableDays_rso_35.class.equals(paramClass);
	}

	/**
	 * Implements the validation logic
	 */
	@Override
	public void validate(Object obj, Errors errors) {
		
		BaseCalendarUnavailableDays_rso_35 unavailableDaysObj = (BaseCalendarUnavailableDays_rso_35) obj;
		boolean hasErrors = false;
		
		//Check fields are not null
		if(unavailableDaysObj.getDate() == null) {
			errors.rejectValue("date", "null_date_value", new Object[]{"'Date'"}, "'Date' cannot be empty!");
			hasErrors = true;
		}
		if(unavailableDaysObj.getUnavailableStartTime() == null) {
			errors.rejectValue("unavailableStartTime", "null_time_value", new Object[]{"'Start Time'"}, "'Start Time' cannot be empty!");
			hasErrors = true;
		}
		if(unavailableDaysObj.getUnavailableEndTime() == null) {
			errors.rejectValue("unavailableEndTime", "null_time_value", new Object[]{"'End Time'"}, "'End Time' cannot be empty!");
			hasErrors = true;
		}
				
		//Check if has null fields. Return here if yes
		if(hasErrors)
		{
			return;
		}
		
		//Check if End time is after Start time.
		if (!unavailableDaysObj.getUnavailableEndTime().after(unavailableDaysObj.getUnavailableStartTime()))
		{
			errors.rejectValue("unavailableEndTime", "start_after_end_time", new Object[]{"'End Time'", "'Start Time'"}, "'End Time' must be after 'Start Time'");
		}

	}

}
