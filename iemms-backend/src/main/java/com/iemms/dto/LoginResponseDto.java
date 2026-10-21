//SHIVAAAAHHHHHH


package com.iemms.dto;

public class LoginResponseDto {
	 private String token;
	 private String tokenType;
	 private long expiresIn;
	 
	 public LoginResponseDto(String token,String tokenType,long expiresIn) {
		 this.token=token;
		 this.tokenType=tokenType;
		 this.expiresIn=expiresIn;
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
}
