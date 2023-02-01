package com.csis3275.dao;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import com.csis3275.model.loginUser_imo_65;


@Component
public class loginDAO_imo_65 {
	JdbcTemplate jdbcTemplate;
	
	
	
	private final String SQL_LOGIN = "SELECT * FROM USER WHERE EMAIL = ? AND PASSWORD = ?";	
	
	@Autowired
	public loginDAO_imo_65(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public loginDAO_imo_65() {
		JDBCLoginCheck_imo_65 ds = new JDBCLoginCheck_imo_65();
		try {
			jdbcTemplate = new JdbcTemplate(ds.mydataSource());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	public boolean UserLogin(loginUser_imo_65 user) {
		loginUser_imo_65 ruser;
		return (jdbcTemplate.queryForList(SQL_LOGIN,user.getLmail(),user.getLpassword()).size()>0);
		
	}
	
}
