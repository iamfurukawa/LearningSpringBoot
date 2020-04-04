package com.learning.spring.boot.web.myProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.learning.spring.boot.web.myProject.service.LoginService;

@Controller
@SessionAttributes("name")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		model.put("name", "admin");
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password) {
		if (!loginService.validateUser(name, password)) {
			model.put("errorMessage","Invalid Credentials");
			return "login";
		}
		 
		model.put("name", name);
		return "welcome";
	}
}
