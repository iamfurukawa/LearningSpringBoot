package com.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.service.WelcomeService;

@RestController
public class WelcomeController {
	
	@Autowired
	private WelcomeService welcomeService;

	@RequestMapping("/welcome")
	public String welcome() {
		return welcomeService.retrieveWelcomeMessage();
	}
}
