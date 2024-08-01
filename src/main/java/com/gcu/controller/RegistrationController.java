package com.gcu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.UserBusinessService;
import com.gcu.model.LoginModel;
import com.gcu.model.UserDetails;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class RegistrationController 
{
	// SLF4J Logger
	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	private UserDetails userDetails;
	
	@Autowired
	private UserBusinessService userBusinessService;
	
	@GetMapping("register")
	public String displayRegister(Model model)
	{
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		logger.info(date + " " + time + ": Code 6; RegistrationController - displayRegister - entry");
		
		model.addAttribute("loggedIn", userDetails.isLoggedIn());
		model.addAttribute("loginModel", new LoginModel());
		
		// User is already logged in
		if (userDetails.isLoggedIn())
		{
			logger.info(date + " " + time + ": Code 6; RegistrationController - displayRegister - exit");
			model.addAttribute("title", "Home");
			return "redirect:/";
		}
		
		logger.info(date + " " + time + ": Code 6; RegistrationController - displayRegister - exit");
		model.addAttribute("title", "Register");
		return "register";
	}
	
	@PostMapping("doRegister")
	public String doRegister(@Valid LoginModel loginModel, BindingResult bindingResult, Model model)
	{
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		logger.info(date + " " + time + ": Code 6; RegistrationController - doRegister - entry");
		
		boolean registerSuccess = userBusinessService.validateRegister(loginModel);
		
		// Check for validation errors
		if (bindingResult.hasErrors() || !registerSuccess) {
			logger.info(date + " " + time + ": Code 4; RegistrationController - doRegister - Register failure");
			model.addAttribute("title", "Register");
			model.addAttribute("loggedIn", userDetails.isLoggedIn());
			return "register";
		}
		
		// Finish assigning userDetail information and redirect
		userDetails.setLoggedIn(true);
		model.addAttribute("loggedIn", userDetails.isLoggedIn());
		userDetails.setUsername(loginModel.getUsername());
		userDetails.setPassword(loginModel.getPassword());
		logger.info(date + " " + time + ": Code 6; RegistrationController - doRegister - exit");
		
		model.addAttribute("title", "Home");
		return "redirect:/";
	}
}
