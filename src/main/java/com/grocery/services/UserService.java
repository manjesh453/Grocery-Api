package com.grocery.services;

import java.util.List;

import com.grocery.payloads.UserDto;

public interface UserService {
	
	UserDto createUsers(UserDto userDto);
	List<UserDto>getAllUser();
	void deleteUser(Integer userId);
	UserDto getUserById(Integer userId);
	UserDto updateUser(UserDto userDto,Integer userId);
	

}
