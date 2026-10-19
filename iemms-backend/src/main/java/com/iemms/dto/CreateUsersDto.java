//SHIVAAAAHHHHH


package com.iemms.dto;

import java.time.LocalDateTime;

import com.iemms.entity.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateUsersDto {
	private Long Id;
	@NotBlank(message="Username cannot be empty")
	private String username;
	@NotBlank(message="Password cannot be empty")
	private String password;
	@NotBlank(message="Provide full name")
	@Size(max=50, message="Maximum length of Fullname is 50")
	private String fullName;
	@NotBlank(message="Email cannot be empty")
	@Email(message="Enter a valid email")
	private String email;
	@NotBlank(message="Mobile number cannot be empty")
	private String mobileNumber;
	@NotNull(message="Role must be specified")
	private UserRole role;

	
	public CreateUsersDto() {
		
	}
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	
	

}
