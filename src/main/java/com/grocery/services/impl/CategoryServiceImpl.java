package com.grocery.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grocery.entities.Category;
import com.grocery.exceptions.ResourceNotFoundException;
import com.grocery.payloads.CategoryDto;
import com.grocery.repositories.CategoryRepo;
import com.grocery.services.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	

	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat=this.modelMapper.map(categoryDto, Category.class);
		Category newCat=this.categoryRepo.saveAndFlush(cat);
		return this.modelMapper.map(newCat, CategoryDto.class);
	}

	
	public CategoryDto updateCategory(CategoryDto cateroeyDto, Integer caetgoryId) {
		Category cat=this.categoryRepo.findById(caetgoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", caetgoryId));
		cat.setCategoryName(cateroeyDto.getCategoryName());
		Category updateCat=this.categoryRepo.save(cat);
		return this.modelMapper.map(updateCat, CategoryDto.class);
	}

	
	public void deleteCategory(Integer caetgoryId) {
	this.categoryRepo.deleteById(caetgoryId);
		
	}

	
	public CategoryDto getCategoryById(Integer categoryID) {
		Category cat=this.categoryRepo.findById(categoryID).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryID));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	
	public List<CategoryDto> getAllCategory() {
		List<Category> list=this.categoryRepo.findAll();
		List<CategoryDto> catDto=list.stream().map(category->this.catToDto(category)).collect(Collectors.toList());
		return catDto;
	}
	
	public CategoryDto catToDto(Category cat) {
		return this.modelMapper.map(cat, CategoryDto.class);
	}
	

}
