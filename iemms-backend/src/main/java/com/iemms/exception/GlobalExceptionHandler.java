//SHIVAAAAAHHHH

package com.iemms.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.iemms.dto.ErrorResponse;
import com.iemms.exception.EquipmentNotFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex){
		HashMap<String,String> errors=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error ->{
			errors.put(error.getField(),error.getDefaultMessage());
		});
		ErrorResponse errorResponse=new ErrorResponse(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),
	            "Request validation failed");
		errorResponse.setValidationErrors(errors);
		return ResponseEntity.badRequest().body(errorResponse);
	}
	@ExceptionHandler(EquipmentNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEquipmentNotFoundException(EquipmentNotFoundException ex){
		
		ErrorResponse error=new ErrorResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase(),ex.getMessage());
		
		
		return ResponseEntity.status(404).body(error);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex){
		ErrorResponse error=new ErrorResponse(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),ex.getMessage());

		return ResponseEntity.badRequest().body(error);
	}
	@ExceptionHandler(AlertNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAlertNotFoundException(AlertNotFoundException ex){
		ErrorResponse error=new ErrorResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase(),ex.getMessage());
		return ResponseEntity.status(404).body(error);
		
	}
	@ExceptionHandler(MaintenanceTicketNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleMaintenanceTicketNotFoundException(MaintenanceTicketNotFoundException ex){
		ErrorResponse error=new ErrorResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase(),ex.getMessage());
		return ResponseEntity.status(404).body(error);
	}

}
