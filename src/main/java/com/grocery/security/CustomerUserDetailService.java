package com.grocery.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.grocery.entities.User;
import com.grocery.exceptions.ResourceNotFoundException;
import com.grocery.repositories.UserRepo;
@Service
public class CustomerUserDetailService implements UserDetailsService{
	
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","UserName", 0));
	    return user;
	}

}
