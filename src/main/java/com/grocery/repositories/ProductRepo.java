package com.grocery.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grocery.entities.Category;
import com.grocery.entities.Product;
import com.grocery.entities.SubCategory;


public interface ProductRepo extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product p WHERE p.SubCat=:subCat")
    List<Product>findBySubCat(SubCategory subCat);
	 
	@Query("SELECT p FROM Product p WHERE p.SubCat.category=:Category")
	List<Product>findByCategory(Category Category);
	
	@Query("SELECT p FROM Product p WHERE p.productName LIKE %:keyword%")
	List<Product>findBykeyword(String keyword);
}