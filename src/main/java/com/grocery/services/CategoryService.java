package com.grocery.services;

import java.util.List;

import com.grocery.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto cateroeyDto,Integer caetgoryId);
	void deleteCategory(Integer caetgoryId);
	CategoryDto getCategoryById(Integer categoryID);
	List<CategoryDto>getAllCategory();
}
