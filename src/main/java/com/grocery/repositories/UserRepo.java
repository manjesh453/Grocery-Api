package com.grocery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
