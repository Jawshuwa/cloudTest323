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
public class UserController 
{
	// SLF4J Logger
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	// Inject the LoginBusinessService Bean
	@Autowired
	private UserBusinessService UserBusinessService;

	@Autowired
	UserDetails userDetails;
	
	@GetMapping("user")
	public String displayUser(Model model)
	{
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		logger.info(date + " " + time + ": Code 6; UserController - displayUser - entry");
		
		model.addAttribute("userDetails", userDetails);
		
		// If user is not logged in
		if (!userDetails.isLoggedIn())
		{
			model.addAttribute("title", "Home");
			logger.info(date + " " + time + ": Code 6; UserController - displayUser - entry");
			return "redirect:/";
		}
		
		model.addAttribute("loggedIn", userDetails.isLoggedIn());
		model.addAttribute("loginModel", new LoginModel());
		model.addAttribute("title", "User Details");
		logger.info(date + " " + time + ": Code 6; UserController - displayUser - exit");
		return "user";
	}
	
	@PostMapping("/doUpdate")
	public String doUpdate(@Valid LoginModel loginModel, BindingResult bindingResult, Model model)
	{
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		logger.info(date + " " + time + ": Code 6; UserController - doUpdate - entry");
		
		loginModel.setUsername(userDetails.getUsername());
		model.addAttribute("loggedIn", userDetails.isLoggedIn());
		model.addAttribute("userDetails", userDetails);
		model.addAttribute("title", "User Details");
		
		if (!UserBusinessService.updateUser(loginModel))
		{
			logger.info(date + " " + time + ": Code 4; UserController - doUpdate - SQL FAIL");
			return "user";
		}
		
		logger.info(date + " " + time + ": Code 6; UserController - doUpdate - exit");
		return "user";
	}
	
	@GetMapping("delete")
	public String displayDelete(Model model)
	{
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		logger.info(date + " " + time + ": Code 6; UserController - displayDelete - entry");
		
		model.addAttribute("loggedIn", userDetails.isLoggedIn());
		model.addAttribute("title", "Delete User Account");
		logger.info(date + " " + time + ": Code 6; UserController - displayDelete - exit");
		return "delete";
	}
	
	@GetMapping("/doDelete")
	public String doDelete(Model model)
	{
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		logger.info(date + " " + time + ": Code 6; UserController - doDelete - entry");
		
		UserBusinessService.deleteUser();
		
		userDetails.Clear();
		model.addAttribute("title", "Home");
		logger.info(date + " " + time + ": Code 6; UserController - doUpdate - exit");
		return "redirect:/";
	}
}
