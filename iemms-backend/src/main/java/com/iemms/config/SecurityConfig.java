//SHIVAAAHHHHHH


package com.iemms.config;


import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${app.security.users.admin.username}")
	private String adminUsername;
	@Value("${app.security.users.admin.password}")
	private String adminPassword;
	@Value("${app.security.users.manager.username}")
	private String managerUsername;
	@Value("${app.security.users.manager.password}")
	private String managerPassword;
	@Value("${app.security.users.technician.username}")
	private String technicianUsername;
	@Value("${app.security.users.technician.password}")
	private String technicianPassword;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(auth->auth
				.requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
				.requestMatchers(org.springframework.http.HttpMethod.POST,"/api/equipment").hasAnyRole("ADMIN","MANAGER")
				.requestMatchers(org.springframework.http.HttpMethod.PUT,"/api/equipment/*").hasAnyRole("ADMIN","MANAGER")
				.requestMatchers(org.springframework.http.HttpMethod.DELETE,"/api/equipment/*").hasAnyRole("ADMIN","MANAGER")
				.requestMatchers(org.springframework.http.HttpMethod.GET,"/api/equipment").hasAnyRole("ADMIN","MANAGER","TECHNICIAN")
				.requestMatchers(org.springframework.http.HttpMethod.GET,"/api/equipment/*").hasAnyRole("ADMIN","MANAGER","TECHNICIAN")
				.requestMatchers(org.springframework.http.HttpMethod.POST,"/api/equipment/*/readings").hasAnyRole("ADMIN","MANAGER","TECHNICIAN")
				.requestMatchers(org.springframework.http.HttpMethod.GET,"/api/equipment/*/readings").hasAnyRole("ADMIN","MANAGER","TECHNICIAN")
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
				.anyRequest().authenticated()).httpBasic(basic->{});
		return http.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public InMemoryUserDetailsManager userDetails(PasswordEncoder passwordEncoder) {
		UserDetails technician=User.builder().username(technicianUsername).password(passwordEncoder.encode(technicianPassword)).roles("TECHNICIAN").build();
		UserDetails admin=User.builder().username(adminUsername).password(passwordEncoder.encode(adminPassword)).roles("ADMIN").build();
		UserDetails manager=User.builder().username(managerUsername).password(passwordEncoder.encode(managerPassword)).roles("MANAGER").build();
		
		return new InMemoryUserDetailsManager(technician,admin,manager);
	}
	

}
