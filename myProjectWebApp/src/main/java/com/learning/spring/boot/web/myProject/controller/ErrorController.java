package com.learning.spring.boot.web.myProject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ErrorController {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest httpServletRequest, Exception exception) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", exception.getStackTrace());
		modelAndView.addObject("url", httpServletRequest.getRequestURL());
		modelAndView.setViewName("error");
		
		return modelAndView;
	}
	
}
