package com.bus.serviceimpl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.dto.BusDto;
import com.bus.dto.UserDto;
import com.bus.entity.Bus;
import com.bus.entity.User;
import com.bus.exceptions.UserNotFoundException;
import com.bus.repository.UserRepository;

@Service

public class UserService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	public UserDto addUser(UserDto userDto){
		User user=new User();
		BeanUtils.copyProperties(userDto, user);
		userRepository.save(user);
		return userDto;
	}
	
	public UserDto updateUser(int userLoginId,UserDto userDto) {
		
		if(!userRepository.existsById(userLoginId)) {
			throw new UserNotFoundException();
		}

		 User user = userRepository.findById(userLoginId).get();
		 
		  UserDto userDto1 = new UserDto();
		  BeanUtils.copyProperties(user, userDto1);
		 userDto1.setContact(userDto.getContact());
		 userDto1.setEmail(userDto.getEmail());
		 userDto1.setFirstName(userDto.getFirstName());
		 userDto1.setLastName(userDto.getLastName());
		 userDto1.setPassword(userDto.getPassword());
		 userDto1.setUserName(userDto.getUserName());
		
		  BeanUtils.copyProperties(userDto1, user);
		 
		  userRepository.saveAndFlush(user);
		  
		  return userDto1;		
	}
	
	public UserDto deleteUser(int userLoginId) {
		
		if(!userRepository.existsById(userLoginId)) {
			throw new UserNotFoundException();
		}
		
		UserDto userDto=new UserDto();
		User user=new User();
		user = userRepository.findById(userLoginId).get();
		BeanUtils.copyProperties(user,userDto);
		userRepository.deleteById(userLoginId);
		return userDto;
	}
	
	public UserDto viewUser(int userLoginId) {
		
		if(!userRepository.existsById(userLoginId)) {
			throw new UserNotFoundException();
		}
		UserDto userDto=new UserDto();
		User user=new User();
	    user=userRepository.findById(userLoginId).get();
		BeanUtils.copyProperties(user,userDto);
		return userDto;
	}

	public List<User> viewAllUsers() {
		
		List<UserDto>userDtoList=new ArrayList<>();
		List<User>userList=userRepository.findAll();
		UserDto userDto=new UserDto();
		for(User user:userList) {
			BeanUtils.copyProperties(user, userDto);
			userDtoList.add(userDto);
		}
		return userList;
	}

	public User userLogin(String userName, String password) {
		User user = userRepository.findByUserNameAndPassword(userName, password);
		if(userRepository.findByUserNameAndPassword(userName, password)!=null) {
			return user;
		}
		
	     throw new UserNotFoundException();
	}
		
	
	
	

}
