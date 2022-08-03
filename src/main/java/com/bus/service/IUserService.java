package com.bus.service;

import java.util.List;

import com.bus.dto.UserDto;
import com.bus.entity.User;

public interface IUserService {

	public UserDto addUser(UserDto userDto);
	public UserDto updateUser(int userLoginId,UserDto userDto);
	public UserDto deleteUser(int userLoginId);
	public UserDto viewUser(int userLoginId);
	public List<User> viewAllUsers();
	public User userLogin(String userName, String password);
	
}
