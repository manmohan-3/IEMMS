//SHIVAAAAHHHhh


package com.iemms.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.iemms.entity.Users;
import com.iemms.exception.UserNotFoundException;
import com.iemms.repository.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private final UsersRepository usersRepository;
	public CustomUserDetailsService(UsersRepository usersRepository) {
		this.usersRepository=usersRepository;
	}
	public UserDetails loadUserByUsername(String username) {
		Users user=usersRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("User with username "
				+username+" not found..."));
		return User.builder().username(user.getUsername()).password(user.getPassword()).roles(user.getRole().name()).disabled(!user.isEnabled()).build();
		
	}

}
