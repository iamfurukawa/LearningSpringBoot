package com.learning.spring.boot.web.myProject.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	 
	public boolean validateUser(String userid, String password) {
		return userid.equals("admin") && password.equals("admin");
	}
}
