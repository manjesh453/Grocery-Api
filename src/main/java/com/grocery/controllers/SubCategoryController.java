package com.grocery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.grocery.payloads.SubCatDto;
import com.grocery.repositories.SubCategoryRepo;
import com.grocery.services.SubCatService;

@RestController
@RequestMapping("/api")
public class SubCategoryController {
    @Autowired
	private SubCatService subservice;
	
    @PostMapping("/category/{categoryId}/subcategory")
	public ResponseEntity<SubCatDto>createSubCategory(@RequestBody SubCatDto subcat,@PathVariable Integer categoryId){
		SubCatDto newSub=this.subservice.createSubCategory(subcat, categoryId);
		return new ResponseEntity<>(newSub,HttpStatus.CREATED);
	}
}
