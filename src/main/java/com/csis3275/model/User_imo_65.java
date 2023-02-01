package com.csis3275.model;

import java.util.Date;

public class User_imo_65 {
	
	private int id;
	private String fName;
	private String LName;
	private String email;
	private String  phone;
	private Date regDate;
	private String password;
	private String type;
	private String status;
	/**
	 * 
	 * @return ID Value form database
	 */
	public int getId() {
		return id;
	}
	/**
	 * 
	 * @param id Id key from db
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 
	 * @return First Name Value
	 */
	public String getfName() {
		return fName;
	}
	/**
	 * 
	 * @param fName First Name Value to be assigned
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}
	/**
	 * 
	 * @return Last Name value
	 */
	public String getLName() {
		return LName;
	}
	/**
	 * 
	 * @param lName Last Name Value to be Assigned
	 */
	public void setLName(String lName) {
		LName = lName;
	}
	/**
	 * 
	 * @return email value form user
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 
	 * @param email email string to be saved
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 
	 * @return phone number value
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 
	 * @param phone phone number to be assigned
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 
	 * @return registration date, when the user is created
	 */
	public Date getRegDate() {
		return regDate;
	}
	/**
	 * 
	 * @param regDate registration date to be saved on database
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	/**
	 * 
	 * @return Password from user
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 
	 * @param password to be set for the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 
	 * @return user type can be  Administrator/Student/Professor
	 */
	public String getType() {
		return type;
	}
	/**
	 * 
	 * @param type can be  Administrator/Student/Professor
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 
	 * @return status logged in or not
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 
	 * @param status can be set only to login/ out
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	

}
