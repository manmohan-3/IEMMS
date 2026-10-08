//SHIVAAAAAHHHH

package com.iemms.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import com.iemms.exception.EquipmentNotFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
		HashMap<String,String> errors=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error ->{
			errors.put(error.getField(),error.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errors);
	}
	@ExceptionHandler(EquipmentNotFoundException.class)
	public ResponseEntity<String> handleEquipmentNotFoundException(EquipmentNotFoundException ex){
		
		return ResponseEntity.status(404).body(ex.getMessage());
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex){
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

}
