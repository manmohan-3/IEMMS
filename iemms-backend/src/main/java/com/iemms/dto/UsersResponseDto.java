//SHIVAAHHHH


package com.iemms.dto;

import java.time.LocalDateTime;

import com.iemms.entity.UserRole;

public class UsersResponseDto {
	
	private Long Id;
	private String username;
	private String fullName;
	private String email;
	private String mobileNumber;
	private UserRole role;
	private LocalDateTime createdAt;
	private boolean enabled;
	
	public UsersResponseDto() {
		
	}
	public UsersResponseDto(Long Id,String username,String fullName,String email,String mobileNumber,
			UserRole role,LocalDateTime createdAt,boolean enabled) {
		this.Id=Id;
		this.username=username;
		this.fullName=fullName;
		this.email=email;
		this.mobileNumber=mobileNumber;
		this.role=role;
		this.createdAt=createdAt;
		this.enabled=enabled;
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
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	

}
