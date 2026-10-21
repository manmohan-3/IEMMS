//SHIVAAAAHHH


package com.iemms.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDto {
	
	@NotBlank(message="Username must not be blank")
	private String username;
	@NotBlank(message="Password must not be blank")
	private String password;
	
	public LoginRequestDto() {
		
	}
	
	public void setUsername(String username) {
		this.username=username;
	}
	public String getUsername() {
		return username;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public String getPassword() {
		return password;
	}

}
