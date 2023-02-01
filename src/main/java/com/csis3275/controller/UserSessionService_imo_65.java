package com.csis3275.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csis3275.dao.UserMGMTDAO_imo_65;
import com.csis3275.dao.UserSessionDAO_imo_65;
import com.csis3275.model.UserSession_imo_65;
import com.csis3275.model.User_imo_65;

@Service
public class UserSessionService_imo_65 {

	public final static String ACTIVE_STATUS = "active";

	public final static String INACTIVE_STATUS = "inactive";

	public static enum Privileges {
		student, professor, admin
	}

	public static enum Authorization_Return {
		INVALID_SESSION, INSUFFICIENT_PRIVILEGE, PRIVILEGE_OK
	}

	@Autowired
	UserSessionDAO_imo_65 userSessionDAO;

	@Autowired
	UserMGMTDAO_imo_65 userMGMTDAO;

	/**
	 * 
	 * @param sessionCode http session code
	 * @param userEmail   user email
	 * @return true if ok
	 */
	public boolean registerUserSession(String sessionCode, String userEmail) {

		// Get User
		User_imo_65 userObj = userMGMTDAO.getUserbyEmail(userEmail);

		// Check USer
		if (userObj == null) {
			return false; // USer not found
		}

		// Create UserSession Obj
		UserSession_imo_65 nesUserSessionObj = new UserSession_imo_65();
		nesUserSessionObj.setSession_code(sessionCode);
		nesUserSessionObj.setStart_datetime(new Date());
		nesUserSessionObj.setStatus(ACTIVE_STATUS);
		nesUserSessionObj.setUserid(userObj.getId());

		// Register in the database
		return userSessionDAO.createNewUserSession(nesUserSessionObj);

	}

	/**
	 * 
	 * @param sessionCode http session
	 * @return true if ok
	 */
	public boolean inactiveUserSession(String sessionCode) {

		// Get User Session
		UserSession_imo_65 userSessionObj = userSessionDAO.getBySessionCodeAndStatus(sessionCode, ACTIVE_STATUS);

		// Inactive
		return userSessionDAO.updateInactive(userSessionObj, INACTIVE_STATUS, new Date());

	}

	/**
	 * 
	 * @param userSessionCode SessionID
	 * @param privilegeLevel can be UserSessionService_imo_65.Privileges.admin/user/professor
	 * @return next View page
	 */

	public Authorization_Return validateType(String userSessionCode, Privileges privilegeLevel) {
		Authorization_Return returnVal = Authorization_Return.INVALID_SESSION;

		UserSession_imo_65 userSessionObj = userSessionDAO.getBySessionbyId(userSessionCode);

		if (userSessionObj == null) {

		} else {

			int userlevel =-1;
			switch (userSessionObj.getUser().getType()) {

			case "student":
				userlevel = 0;
				break;
			case "professor":
				userlevel = 1;
				break;
			case "admin":
				userlevel = 2;
				break;

			}
			if ((privilegeLevel.ordinal() <= userlevel) &&userlevel>=0) {
				returnVal = Authorization_Return.PRIVILEGE_OK;
			} else {
				returnVal = Authorization_Return.INSUFFICIENT_PRIVILEGE;
			}

		}

		return returnVal;
	}

}
