//SHIVAAAAHHHHH


package com.iemms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class Users {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	@Column(nullable=false, length=50, unique=true)
	private String username;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false,length=50)
	private String fullName;
	@Column(nullable=false,unique=true,length=50)
	private String email;
	@Column(nullable=false,unique=true,length=15)
	private String mobileNumber;
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private UserRole role;
	@Column(nullable=false)
	private boolean enabled;
	@Column(nullable=false,updatable=false)
	private LocalDateTime createdAt;
	
	public Users() {
		
	}
	@PrePersist
	public void onCreate() {
		if(createdAt==null) {
			createdAt=LocalDateTime.now();
		}
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
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber=mobileNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Long getId() {
		return Id;
	}
	
	

}
