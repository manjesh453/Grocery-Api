package com.grocery.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entities.Cart;
import com.grocery.entities.User;


public interface CartRepo extends JpaRepository<Cart, Integer>{
  List<Cart>findByUser(User user);
}
