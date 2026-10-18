//SHIVAAAAAHHHH


package com.iemms.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponse {
	private LocalDateTime timeStamp;
	private int status;
	private String error;
	private String message;
	private Map<String,String> validationErrors;
	
	public ErrorResponse(int status,String error,String message) {
		this.timeStamp=LocalDateTime.now();
		this.status=status;
		this.error=error;
		this.message=message;
		
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public void setValidationErrors(Map<String,String> validationErrors) {
		this.validationErrors=validationErrors;
	}
	public Map<String,String> getValidationErrors(){
		return validationErrors;
	}
	
}
