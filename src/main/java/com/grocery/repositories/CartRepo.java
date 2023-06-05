package com.grocery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entities.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer>{

}
