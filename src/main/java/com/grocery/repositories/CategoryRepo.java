package com.grocery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
