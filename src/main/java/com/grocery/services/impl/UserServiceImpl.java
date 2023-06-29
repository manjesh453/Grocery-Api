package com.grocery.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.grocery.config.AppConstant;
import com.grocery.entities.Role;
import com.grocery.entities.User;
import com.grocery.exceptions.ResourceNotFoundException;
import com.grocery.payloads.UserDto;
import com.grocery.repositories.RoleRepo;
import com.grocery.repositories.UserRepo;
import com.grocery.services.UserService;


@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUsers(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
		User userSave=this.userRepo.saveAndFlush(user);
		return this.modelMapper.map(userSave, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User>users=this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		this.userRepo.deleteById(userId);
		
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userId));
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userId));
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setContact(userDto.getContact());
		user.setAddress(userDto.getAddress());
		User updateUser=this.userRepo.save(user);
		UserDto userDto1=this.modelMapper.map(updateUser, UserDto.class);
		return userDto1;
		
	}
	
	public UserDto userToDto(User user) {
		return this.modelMapper.map(user, UserDto.class);
	}

	public UserDto registerNewUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
		user.setName(userDto.getName());
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role=this.roleRepo.findById(AppConstant.Normal_USER).get();
		user.getRoles().add(role);
		User newUser=this.userRepo.save(user);
		return this.modelMapper.map(newUser, UserDto.class);
	}

}
