package com.csis3275.model;

import java.io.IOException;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.csis3275.dao.JDBCLoginCheck_imo_65;
import com.csis3275.dao.UserMGMTDAO_imo_65;

public class UserSession_imo_65 {
	
	private int sessionid;
	private String session_code;
	private Date start_datetime;
	private Date end_datetime;
	private String status;
	private int userid;
	
	private User_imo_65 user;
	
	/**
	 * @return the sessionid
	 */
	public int getSessionid() {
		return sessionid;
	}
	/**
	 * @param sessionid the sessionid to set
	 */
	public void setSessionid(int sessionid) {
		this.sessionid = sessionid;
	}
	/**
	 * @return the session_code
	 */
	public String getSession_code() {
		return session_code;
	}
	/**
	 * @param session_code the session_code to set
	 */
	public void setSession_code(String session_code) {
		this.session_code = session_code;
	}
	/**
	 * @return the start_datetime
	 */
	public Date getStart_datetime() {
		return start_datetime;
	}
	/**
	 * @param start_datetime the start_datetime to set
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")	
	public void setStart_datetime(Date start_datetime) {
		this.start_datetime = start_datetime;
	}
	/**
	 * @return the end_datetime
	 */
	public Date getEnd_datetime() {
		return end_datetime;
	}
	/**
	 * @param end_datetime the end_datetime to set
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")	
	public void setEnd_datetime(Date end_datetime) {
		this.end_datetime = end_datetime;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}
	/**
	 * @return the user_email
	 */
	
	public User_imo_65 getUser() {
		
		return user;
	}
	public void setUser() throws IOException {
		JDBCLoginCheck_imo_65 ds = new JDBCLoginCheck_imo_65();
		UserMGMTDAO_imo_65 usersearch = new UserMGMTDAO_imo_65(ds.mydataSource());
		
		
		this.user = usersearch.getUserbyId(this.userid);
	}
	

	

}
