package com.csis3275.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.csis3275.controller.loginController_imo_65;

public class JDBCLoginCheck_imo_65 {
	InputStream inputstream;

	public DataSource mydataSource() throws IOException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		Properties prop = new Properties();
		String propfilename = "database.properties";
		inputstream = getClass().getClassLoader().getResourceAsStream(propfilename);
		if (inputstream != null) {
			prop.load(inputstream);
		} else {
			throw new FileNotFoundException("property file '" + propfilename + "' not found in the classpath");
		}

		dataSource.setDriverClassName(prop.getProperty("driver"));
		dataSource.setUrl(prop.getProperty("url"));
		dataSource.setUsername(prop.getProperty("dbuser"));
		dataSource.setPassword(prop.getProperty("dbpassword"));

		return dataSource;
	}

	public boolean logincheck(HttpServletRequest request) {
		String Mail = (String) request.getSession().getAttribute("luser");
		if (Mail == null) {
			Mail = "";
		}
		request.getSession().setAttribute("luser", Mail);
		String password = (String) request.getSession().getAttribute("lpassword");
		if (password == null) {
			password = "";
		}
		request.getSession().setAttribute("lpassword", password);
		loginController_imo_65 l = new loginController_imo_65();
		return (!l.authenticate(Mail, password, request));

	}
}
