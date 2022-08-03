package com.bus.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bus.dto.AdminDto;
import com.bus.dto.UserDto;
import com.bus.entity.Admin;
import com.bus.entity.User;
import com.bus.serviceimpl.AdminService;
import com.bus.serviceimpl.UserService;

@CrossOrigin(origins="*")
@RestController
public class CustomerController  {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/addAdmin")
	public AdminDto addAdmin(@Valid @RequestBody  AdminDto admin) {
		return adminService.addAdmin(admin);
	}
	
	@PostMapping("/addUser")
	public UserDto addUser(@Valid @RequestBody  UserDto userDto) {
		return userService.addUser(userDto);
	}
   
	@PostMapping("/adminLogin")
	public Admin adminLogin(@RequestBody AdminDto adminDto) {
		return adminService.adminLogin(adminDto.getAdminUserName(),adminDto.getAdminPassword());
	}
	 
	@PostMapping("/userLogin")
	public User userLogin(@RequestBody UserDto userDto) {
		return userService.userLogin(userDto.getUserName(),userDto.getPassword());
	}
	
	
	
}
