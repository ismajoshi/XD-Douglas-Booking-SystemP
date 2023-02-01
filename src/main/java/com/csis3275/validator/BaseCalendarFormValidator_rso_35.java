package com.csis3275.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.csis3275.model.BaseCalendar_rso_35;

/**
 * Validator class to handle form errors regarding Base Calendar class.
 *  
 * @author Ricardo dos Santos Alves de Souza
 *
 */
@Component
public class BaseCalendarFormValidator_rso_35 implements Validator {
	
	/**
	 * Define which objects can be validated by this Validator
	 */
	@Override
	public boolean supports(Class<?> paramClass) {

		return BaseCalendar_rso_35.class.equals(paramClass);
	}

	/**
	 * Implements the validation logic
	 */
	@Override
	public void validate(Object obj, Errors errors) {
		
		BaseCalendar_rso_35 baseCalendarObj = (BaseCalendar_rso_35) obj;
		boolean hasErrors = false;
		
		System.out.println("Validating BaseCalendar_rso_35....");
		
		//Check weekdays fields are not null
		if(baseCalendarObj.getWeekdaysStartTime() == null) {
			errors.rejectValue("weekdaysStartTime", "null_time_value", new Object[]{"'Weekdays Start Time'"}, "'Weekdays Start Time' cannot be empty!");
			hasErrors = true;
		}
		if(baseCalendarObj.getWeekdaysEndTime() == null) {
			errors.rejectValue("weekdaysEndTime", "null_time_value", new Object[]{"'Weekdays End Time'"}, "'Weekdays End Time' cannot be empty!");
			hasErrors = true;
		}

		//Check weekenddays fields are not null
		if(baseCalendarObj.getWeekenddaysStartTime() == null) {
			errors.rejectValue("weekenddaysStartTime", "null_time_value", new Object[]{"'Weekend days Start Time'"}, "'Weekend days Start Time' cannot be empty!");
			hasErrors = true;
		}
		if(baseCalendarObj.getWeekdaysStartTime() == null) {
			errors.rejectValue("weekenddaysEndTime", "null_time_value", new Object[]{"'Weekend days End Time'"}, "'Weekend days End Time' cannot be empty!");
			hasErrors = true;
		}
		
		//Check if has null fields. Return here if yes
		if(hasErrors)
		{
			return;
		}
		
		//Check if Weekdays End time is after Start time.
		if (!baseCalendarObj.getWeekdaysEndTime().after(baseCalendarObj.getWeekdaysStartTime()))
		{
			errors.rejectValue("weekdaysEndTime", "start_after_end_time", new Object[]{"'Week days End Time'", "'Week days Start Time'"}, "'Week days End Time' must be after 'Week days Start Time'");
		}
		
		//Check if Weekend days End time is after Start time.
		if (!baseCalendarObj.getWeekenddaysEndTime().after(baseCalendarObj.getWeekenddaysStartTime()))
		{
			errors.rejectValue("weekenddaysEndTime", "start_after_end_time", new Object[]{"'Weekend days End Time'", "'Weekend days Start Time'"}, "'Weekend days End Time' must be after 'Weekend days Start Time'");
		}
		

	}

}
