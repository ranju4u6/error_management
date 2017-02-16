package com.test.em.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginContoller {
	
	@RequestMapping("/login/login.htm")
	public ModelAndView login(){
		final ModelAndView modelAndView  = new ModelAndView("loginPage");
		return modelAndView;
	}
	
	
	@RequestMapping("/login/dashboard.htm")
	public ModelAndView dashboard(){
		final ModelAndView modelAndView  = new ModelAndView("dashboard");
		return modelAndView;
	}
	
	@RequestMapping("/login/faield.htm")
	public ModelAndView authFailed(){
		final ModelAndView modelAndView  = new ModelAndView("failed");
		return modelAndView;
	}
}
