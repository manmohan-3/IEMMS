//SHIVAAAAHH


package com.iemms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.iemms.dto.LoginRequestDto;
import com.iemms.dto.LoginResponseDto;
import com.iemms.security.AuthService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final AuthService authService;
	public AuthController(AuthService authService) {
		this.authService=authService;
	}
	@GetMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public LoginResponseDto login(@Valid @RequestBody LoginRequestDto request) {
		return authService.login(request);
	}

}
