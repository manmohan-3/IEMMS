//SHIVAAAHHHHHH


package com.iemms.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(auth->auth
				.requestMatchers("/swagger/**","/v3/api-docs/**").permitAll()
				.requestMatchers(org.springframework.http.HttpMethod.POST,"/api/maintenance-tickets").hasRole("ADMIN")
				.requestMatchers(org.springframework.http.HttpMethod.GET,"/api/maintenance-tickets").hasAnyRole("ADMIN","TECHNICIAN")
				.requestMatchers(org.springframework.http.HttpMethod.PUT,"/api/maintenance-tickets/*/assign").hasRole("ADMIN")
				.requestMatchers(org.springframework.http.HttpMethod.PUT,"/api/maintenance-tickets/*/start").hasRole("TECHNICIAN")
				.requestMatchers(org.springframework.http.HttpMethod.PUT,"/api/maintenance-tickets/*/complete").hasRole("TECHNICIAN")
				.requestMatchers(org.springframework.http.HttpMethod.PUT,"/api/maintenance-tickets/*/cancel").hasRole("ADMIN")
				.anyRequest().authenticated()).httpBasic(basic->{});
		return http.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public InMemoryUserDetailsManager userDetails(PasswordEncoder passwordEncoder) {
		UserDetails technician=User.builder().username("technician1").password(passwordEncoder.encode("Tech@123")).roles("TECHNICIAN").build();
		UserDetails admin=User.builder().username("admin").password(passwordEncoder.encode("Admin@123")).roles("ADMIN").build();
		
		return new InMemoryUserDetailsManager(technician,admin);
	}
	

}
