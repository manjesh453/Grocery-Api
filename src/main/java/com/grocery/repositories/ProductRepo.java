package com.grocery.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entities.Product;
import com.grocery.entities.SubCategory;

public interface ProductRepo extends JpaRepository<Product, Integer> {

	List<Product>findBySubCategory(SubCategory subCat);
	}
