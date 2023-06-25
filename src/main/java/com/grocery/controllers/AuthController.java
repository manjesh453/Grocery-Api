package com.grocery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.exceptions.ApiException;
import com.grocery.payloads.JwtAuthRequest;
import com.grocery.payloads.JwtAuthResponse;
import com.grocery.payloads.UserDto;
import com.grocery.security.JwtTokenHelper;
import com.grocery.services.UserService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse>createToken(@RequestBody JwtAuthRequest request) throws Exception{
		this.authenticate(request.getUsername(),request.getPassword());
		 UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUsername());
		 System.out.println(userDetails);
		String generateToken=this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(generateToken);
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);
		try {
			this.authenticationManager.authenticate(authenticationToken);
		}catch (BadCredentialsException e) {
			throw new ApiException("Invalid username or Password");
		}
		
	}
	@PostMapping("/register")
	public ResponseEntity<UserDto>regesterUser(@RequestBody UserDto userDto){
		UserDto newUser=this.userService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(newUser,HttpStatus.CREATED);
	}


}
