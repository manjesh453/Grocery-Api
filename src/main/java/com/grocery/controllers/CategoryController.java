package com.grocery.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.grocery.payloads.ApiResponse;
import com.grocery.payloads.CategoryDto;
import com.grocery.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto>createCategory(@RequestBody CategoryDto categoryDto){
		System.out.println("dksjflska");
		CategoryDto catDto=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(catDto,HttpStatus.CREATED);
	}
	@PutMapping("/{categoryId}")
   public ResponseEntity<CategoryDto>updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
	   CategoryDto catDto=this.categoryService.updateCategory(categoryDto, categoryId);
	   return ResponseEntity.ok(catDto);
   }
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto>getById(@PathVariable Integer categoryId){
		CategoryDto catDto=this.categoryService.getCategoryById(categoryId);
		return ResponseEntity.ok(catDto);
	}
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?>deleteCategory(@PathVariable Integer categoryId){
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Successfully", true),HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>>getAllCategory(){
		return ResponseEntity.ok(this.categoryService.getAllCategory());
	}
}
