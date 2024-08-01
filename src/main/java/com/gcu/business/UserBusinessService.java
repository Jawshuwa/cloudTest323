package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.UserDataService;
import com.gcu.model.LoginModel;

@Service
public class UserBusinessService 
{
	// Inject the LoginDataService Bean
	@Autowired
	UserDataService userDataService;
	
	public boolean validateLogin(LoginModel loginModel)
	{
		// Get the returned LoginModel object of finById() method of the LoginDataService class
		LoginModel loginStatus = userDataService.findByMatch(loginModel);
		
		// If returned null object, return false
		if (loginStatus == null) return false;
		
		// Otherwise return true
		return true;
	}
	
	public boolean validateRegister(LoginModel loginModel)
	{
		boolean success = userDataService.create(loginModel);
		
		return success;
	}
	
	public boolean updateUser(LoginModel loginModel)
	{
		boolean success = userDataService.update(loginModel);
		
		return success;
	}
	
	public boolean deleteUser()
	{
		boolean success = userDataService.delete(new LoginModel());
		
		return success;
	}
}
