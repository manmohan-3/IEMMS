//SHIVAAAAAHHHH


package com.iemms.service;


import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iemms.dto.CreateUsersDto;
import com.iemms.dto.UsersResponseDto;
import com.iemms.entity.UserRole;
import com.iemms.entity.Users;
import com.iemms.repository.UsersRepository;

import jakarta.transaction.Transactional;

@Service
public class UsersService {
	
	private UsersRepository usersRepository;
	private PasswordEncoder passwordEncoder;
	public UsersService(UsersRepository usersRepository,PasswordEncoder passwordEncoder) {
		this.passwordEncoder=passwordEncoder;
		this.usersRepository=usersRepository;
	}
	@Transactional
	public UsersResponseDto createUser(CreateUsersDto usersDto,UserRole creatorRole) {
		if(usersDto==null) {
			throw new IllegalArgumentException("User details cannot be empty");
		}
		if(usersDto.getUsername()==null||usersDto.getUsername().isBlank()) {
			throw new IllegalArgumentException("Username cannot be empty");
		}
		if(usersDto.getEmail()==null||usersDto.getEmail().isBlank()) {
			throw new IllegalArgumentException("Email cannot be empty");
		}
		if(usersDto.getFullName()==null||usersDto.getFullName().isBlank()) {
			throw new IllegalArgumentException("Name of User must be provided");
		}
		if(usersDto.getMobileNumber()==null||usersDto.getMobileNumber().isBlank()) {
			throw new IllegalArgumentException("Mobile Number cannot be empty");
		}
		if(usersDto.getPassword()==null||usersDto.getPassword().isBlank()) {
			throw new IllegalArgumentException("Password cannot be empty");
		}
		if(usersDto.getRole()==null) {
			throw new IllegalArgumentException("Role must be specified");
		}
		
//		validateNewUser(users);
		String username=usersDto.getUsername().trim();
		String email=usersDto.getEmail().trim();
		String mobileNumber=usersDto.getMobileNumber().trim();
		String fullName=usersDto.getFullName().trim();
		String password=usersDto.getPassword();
		UserRole role=usersDto.getRole();
		validateCreationPermission(creatorRole,role);
		validateNewUser(username,email,mobileNumber);
		
		Users users=new Users();
		users.setUsername(username);
		users.setEmail(email);
		users.setEnabled(true);
		users.setMobileNumber(mobileNumber);
		users.setFullName(fullName);
		users.setPassword(passwordEncoder.encode(password));
		users.setRole(role);
		
		
		return convertToDto(usersRepository.save(users));
	}
	public void validateCreationPermission(UserRole creatorRole,UserRole requestedRole) {
		if(creatorRole==UserRole.ADMIN) {
			if(requestedRole==UserRole.MANAGER||requestedRole==UserRole.TECHNICIAN) {
				return;
			}
		}
		if(creatorRole==UserRole.MANAGER) {
			if(requestedRole==UserRole.TECHNICIAN) {
				return;
			}
		}
		throw new IllegalArgumentException("You are not allowed to create user of this role");
	}
	
	public void validateNewUser(String username,String email,String mobileNumber) {
		if(usersRepository.existsByUsername(username)) {
			throw new IllegalArgumentException("Username already exists");
		}
		if(usersRepository.existsByEmail(email)) {
			throw new IllegalArgumentException("Email already exists");
		}
		if(usersRepository.existsByMobileNumber(mobileNumber)) {
			throw new IllegalArgumentException("Mobile number already exists");
		}
	}
	public List<UsersResponseDto> getAllUsers(){
		return usersRepository.findAll().stream().map(this::convertToDto).toList();
	}
	public UsersResponseDto getUserById(Long Id) {
		Users user=usersRepository.findById(Id).orElseThrow(()->new IllegalArgumentException("No user with provided Id"));
		return convertToDto(user) ;
	}
	@Transactional
	public UsersResponseDto disableUser(Long Id) {
		Users user=usersRepository.findById(Id).orElseThrow(()->new IllegalArgumentException("No user with provided Id"));
		user.setEnabled(false);
		return convertToDto(usersRepository.save(user));
	}
	@Transactional
	public UsersResponseDto enableUser(Long Id) {
		Users user=usersRepository.findById(Id).orElseThrow(()->new IllegalArgumentException("No user with provided Id"));
		user.setEnabled(true);
		return convertToDto(usersRepository.save(user));
	}
	public UsersResponseDto convertToDto(Users users) {
		return new UsersResponseDto(users.getId(),users.getUsername(),users.getFullName(),users.getEmail(),users.getMobileNumber()
				,users.getRole(),users.getCreatedAt(),users.isEnabled());
	}
	
	

}
