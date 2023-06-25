package com.grocery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
