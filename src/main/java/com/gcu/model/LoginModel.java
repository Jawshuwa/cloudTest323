package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginModel 
{
	// Validation rules for username and password
	@NotNull(message="Username is a required field.")
	@Size(min=1, max=32, message="Username must be between 1 and 32 characters")
	private String username;
	@NotNull(message="Password is a required field.")
	@Size(min=1, max=32, message="Password must be between 1 and 32 characters")
	private String password;
	
	private String firstName;
	private String lastName;
	
	// Constructors, parameterized and default
	public LoginModel(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	public LoginModel(String username, String password, String firstName, String lastName) 
	{
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public LoginModel()
	{
		this.username = null;
		this.password = null;
		this.firstName = null;
		this.lastName = null;
	}

	// Getters and setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
}
