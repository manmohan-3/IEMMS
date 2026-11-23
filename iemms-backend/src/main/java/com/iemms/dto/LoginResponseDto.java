//SHIVAAAAHHHHHH


package com.iemms.dto;

public class LoginResponseDto {
	 private String token;
	 private String tokenType;
	 private long expiresIn;
	 private String role;
	 
	 public LoginResponseDto(String token,String tokenType,long expiresIn, String role) {
		 this.token=token;
		 this.tokenType=tokenType;
		 this.expiresIn=expiresIn;
		 this.role=role;
	 }
	 
	 public String getToken() {
		 return token;
	 }
	 public String getTokenType() {
		 return tokenType;
	 }
	 public long getExpiresIn() {
		 return expiresIn;
	 }
	 public String getRole() {
		 return role;
	 }
}
