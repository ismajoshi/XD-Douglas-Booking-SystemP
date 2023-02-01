package com.csis3275.model;

import java.io.Serializable;

public class loginUser_imo_65 implements Serializable {

	private static final long serialVersionUID = 1L;
    private String lmail;
    private String lpassword;

    public String getLmail() {
        return lmail;
    }

    public void setLmail(String mail) {
        this.lmail = mail;
    }

    public String getLpassword() {
        return lpassword;
    }

    public void setLpassword(String password) {
        this.lpassword = password;
    }
	
}
