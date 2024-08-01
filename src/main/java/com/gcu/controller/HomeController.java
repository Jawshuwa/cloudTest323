package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gcu.model.UserDetails;

@Controller
@RequestMapping("/")
public class HomeController {

	// SLF4J Logger
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	UserDetails userDetails;
	
	@GetMapping("/")
	public String displayMain(Model model)
	{
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		logger.info(date + " " + time + ": Code: 6; HomeController - displayMain - entry");
		
		model.addAttribute("title", "Home");
		model.addAttribute("loggedIn", userDetails.isLoggedIn());
		
		logger.info(date + " " + time + ": Code: 6; HomeController - displayMain - exit");
		return "home.html";
	}
	
}
