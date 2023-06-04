package com.grocery.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grocery.entities.Product;
import com.grocery.entities.SubCategory;


public interface ProductRepo extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product WHERE p.subCat=:subCat")
List<Product>findBySubCat(SubCategory subCat);
}