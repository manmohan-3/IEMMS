//SHIVAAAHHHHHH


package com.iemms.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.iemms.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.jwtAuthenticationFilter=jwtAuthenticationFilter;
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf->csrf.disable())
				.sessionManagement(session ->
				session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth->auth
				.requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
				.requestMatchers(org.springframework.http.HttpMethod.POST,"/api/auth/login").permitAll()
				.requestMatchers(org.springframework.http.HttpMethod.POST,"/api/equipment").hasAnyRole("ADMIN","MANAGER")
				.requestMatchers(org.springframework.http.HttpMethod.PUT,"/api/equipment/*").hasAnyRole("ADMIN","MANAGER")
				.requestMatchers(org.springframework.http.HttpMethod.DELETE,"/api/equipment/*").hasAnyRole("ADMIN","MANAGER")
				.requestMatchers(org.springframework.http.HttpMethod.GET,"/api/equipment").hasAnyRole("ADMIN","MANAGER","TECHNICIAN")
				.requestMatchers(org.springframework.http.HttpMethod.GET,"/api/equipment/*").hasAnyRole("ADMIN","MANAGER","TECHNICIAN")
				.requestMatchers(org.springframework.http.HttpMethod.POST,"/api/equipment/*/readings").hasAnyRole("ADMIN","MANAGER","TECHNICIAN")
				.requestMatchers(org.springframework.http.HttpMethod.GET,"/api/equipment/*/readings").hasAnyRole("ADMIN","MANAGER","TECHNICIAN")
				.requestMatchers(org.springframework.http.HttpMethod.POST,"/api/users").hasAnyRole("ADMIN", "MANAGER")
				.requestMatchers(org.springframework.http.HttpMethod.GET,"/api/users","/api/users/*").hasAnyRole("ADMIN", "MANAGER")
				.requestMatchers(org.springframework.http.HttpMethod.PUT,"/api/users/*/disable","/api/users/*/enable").hasRole("ADMIN")
				.requestMatchers(org.springframework.http.HttpMethod.GET,"/api/alerts").hasAnyRole("ADMIN","MANAGER","TECHNICIAN")
				.requestMatchers(org.springframework.http.HttpMethod.GET,"/api/alerts/*").hasAnyRole("ADMIN","MANAGER","TECHNICIAN")
				.requestMatchers(org.springframework.http.HttpMethod.GET,"/api/alerts/equipment/*").hasAnyRole("ADMIN","MANAGER","TECHNICIAN")
				.requestMatchers(org.springframework.http.HttpMethod.POST,"/api/thresholds/equipment/*").hasAnyRole("ADMIN","MANAGER")
				.requestMatchers(org.springframework.http.HttpMethod.PUT,"/api/thresholds/*").hasAnyRole("ADMIN","MANAGER")
				.requestMatchers(org.springframework.http.HttpMethod.GET,"/api/thresholds").hasAnyRole("ADMIN","MANAGER","TECHNICIAN")
				.requestMatchers(org.springframework.http.HttpMethod.DELETE,"/api/thresholds/*").hasAnyRole("ADMIN","MANAGER")
				.requestMatchers(org.springframework.http.HttpMethod.GET,"/api/thresholds/equipment/*").hasAnyRole("ADMIN","MANAGER","TECHNICIAN")
				.requestMatchers(org.springframework.http.HttpMethod.POST,"/api/maintenance-tickets").hasAnyRole("ADMIN","MANAGER")
				.requestMatchers(org.springframework.http.HttpMethod.GET,"/api/maintenance-tickets").hasAnyRole("ADMIN","MANAGER","TECHNICIAN")
				.requestMatchers(org.springframework.http.HttpMethod.GET,"/api/maintenance-tickets/*").hasAnyRole("ADMIN","MANAGER","TECHNICIAN")
				.requestMatchers(org.springframework.http.HttpMethod.GET,"/api/maintenance-tickets/equipment/*").hasAnyRole("ADMIN","MANAGER","TECHNICIAN")
				.requestMatchers(org.springframework.http.HttpMethod.PUT,"/api/maintenance-tickets/*/assign").hasAnyRole("ADMIN","MANAGER")
				.requestMatchers(org.springframework.http.HttpMethod.PUT,"/api/maintenance-tickets/*/start").hasRole("TECHNICIAN")
				.requestMatchers(org.springframework.http.HttpMethod.PUT,"/api/maintenance-tickets/*/complete").hasRole("TECHNICIAN")
				.requestMatchers(org.springframework.http.HttpMethod.PUT,"/api/maintenance-tickets/*/cancel").hasAnyRole("ADMIN","MANAGER")
				.anyRequest().authenticated()).addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationManager authenticateManager(AuthenticationConfiguration configuration) throws Exception{
		return configuration.getAuthenticationManager();
	}
	
	

}
