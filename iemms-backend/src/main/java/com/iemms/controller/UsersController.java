//SHIVAAAAHHHh


package com.iemms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iemms.dto.CreateUsersDto;
import com.iemms.dto.UsersResponseDto;
import com.iemms.entity.UserRole;
import com.iemms.service.UsersService;
import com.iemms.security.CurrentUserService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	private UsersService usersService;
	private CurrentUserService currentUserService;
	public UsersController(UsersService usersService,CurrentUserService currentUserService) {
		this.usersService=usersService;
		this.currentUserService=currentUserService;
	}
	@PostMapping
	public UsersResponseDto createUsers(@Valid @RequestBody CreateUsersDto usersDto) {
		UserRole creatorRole = currentUserService.getCurrentUserRole();
		return usersService.createUser(usersDto, creatorRole);
	}
	@GetMapping
	public List<UsersResponseDto> getUsers(){
		return usersService.getAllUsers();
	}
	@GetMapping("/{Id}")
	public UsersResponseDto getUserById(@PathVariable Long Id) {
		return usersService.getUserById(Id);
	}
	@PutMapping("/{Id}/disable")
	public UsersResponseDto disableUser(@PathVariable Long Id) {
		return usersService.disableUser(Id);
	}
	@PutMapping("/{Id}/enable")
	public UsersResponseDto enableUser(@PathVariable Long Id) {
		return usersService.enableUser(Id);
	}

}
