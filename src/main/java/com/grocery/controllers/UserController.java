package com.grocery.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.grocery.payloads.ApiResponse;
import com.grocery.payloads.UserDto;
import com.grocery.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
@PostMapping("/")
	public ResponseEntity<UserDto>createUser(@RequestBody UserDto userDto){
		UserDto newUser=this.userService.createUsers(userDto);
		return new ResponseEntity<>(newUser,HttpStatus.CREATED);
	}
    
@PutMapping("/{userId}")
  public ResponseEntity<UserDto>updateUser(@RequestBody UserDto userDto,@PathVariable Integer userId){
    	UserDto updateUSer=this.userService.updateUser(userDto, userId);
    	return ResponseEntity.ok(updateUSer);
    }

@DeleteMapping("/{userId}")
public ResponseEntity<?>deleteUser(@PathVariable Integer userId){
	this.userService.deleteUser(userId);
	return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted successfully", true),HttpStatus.OK);
	
}

@GetMapping("/{userId}")
public ResponseEntity<UserDto>getUseerById(@PathVariable Integer userId){
	UserDto userDto=this.userService.getUserById(userId);
	return ResponseEntity.ok(userDto);
}

@GetMapping("/")
public ResponseEntity<List<UserDto>>getAllUser(){
	return ResponseEntity.ok(this.userService.getAllUser());
}
}















