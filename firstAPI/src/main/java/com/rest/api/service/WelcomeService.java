package com.rest.api.service;

import org.springframework.stereotype.Service;

@Service
public class WelcomeService {

	public String retrieveWelcomeMessage() {
		return "welcome";
	}
}
