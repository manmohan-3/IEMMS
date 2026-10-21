//SHIVAAAAHHH


package com.iemms.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.iemms.dto.LoginRequestDto;
import com.iemms.dto.LoginResponseDto;

@Service
public class AuthService {
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	public AuthService(AuthenticationManager authenticationManager,JwtService jwtService) {
		this.authenticationManager=authenticationManager;
		this.jwtService=jwtService;
	}
	public LoginResponseDto login(LoginRequestDto request) {
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.getUsername(),request.getPassword()));
		String username=authentication.getName();
		String token=jwtService.generateToken(username);
		return new LoginResponseDto(token,"Bearer",3600);
	}

}
