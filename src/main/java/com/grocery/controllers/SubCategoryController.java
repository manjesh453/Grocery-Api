package com.grocery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.payloads.ApiResponse;
import com.grocery.payloads.SubCatDto;
import com.grocery.services.SubCatService;

@RestController
@RequestMapping("/api/subcategory")
public class SubCategoryController {
    @Autowired
	private SubCatService subservice;
	
    @PostMapping("/category/{categoryId}")
	public ResponseEntity<SubCatDto>createSubCategory(@RequestBody SubCatDto subcat,@PathVariable Integer categoryId){
		SubCatDto newSub=this.subservice.createSubCategory(subcat, categoryId);
		return new ResponseEntity<>(newSub,HttpStatus.CREATED);
	}
    @PutMapping("/category/{categoryId}/{subcatId}")
    public ResponseEntity<SubCatDto>updateSubCategory(@RequestBody SubCatDto subcat,@PathVariable Integer subcatId,@PathVariable Integer categoryId) {
    	SubCatDto updateSubCat=this.subservice.updateSubCategory(subcat, subcatId, categoryId);
    	return ResponseEntity.ok(updateSubCat);
    }
    @DeleteMapping("/{subCatId}/")
    public ResponseEntity<?>deleteSubCategory(@PathVariable Integer subCatId){
    	this.subservice.deleteSubCategory(subCatId);
    	return new ResponseEntity<ApiResponse>(new ApiResponse("SubCategory successfully Deleted", true),HttpStatus.OK);
    }
	@GetMapping("/{subcatId}/")	
    public ResponseEntity<SubCatDto> getSubCatById(@PathVariable Integer subcatId){
    	SubCatDto subcat=this.subservice.getSubCatById(subcatId);
    	return ResponseEntity.ok(subcat);
    }
	@GetMapping("/")
	public ResponseEntity<List<SubCatDto>> getAllSubCategory() {
		return ResponseEntity.ok(this.subservice.getAllSubCategory());
	}
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<SubCatDto>> getSubCategoryByCategory(@PathVariable Integer categoryId){
		return ResponseEntity.ok(this.subservice.getSubCategoryByCategory(categoryId));
	}
}








