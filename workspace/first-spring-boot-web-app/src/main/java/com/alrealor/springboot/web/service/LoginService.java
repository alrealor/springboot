package com.alrealor.springboot.web.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {
	
	public boolean isValidUser(String user, String password) {
		return user.equalsIgnoreCase("usuario") && password.equals("qwe");
	}

}
