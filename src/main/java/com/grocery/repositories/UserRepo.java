package com.grocery.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grocery.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	//@Query("SELECT u FROM User u WHERE u.email=:Email")
	Optional<User>findByEmail(String Email);

}
