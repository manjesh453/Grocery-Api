package com.grocery.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.grocery.payloads.ProductDto;
import com.grocery.services.FileService;
import com.grocery.services.ProductService;

@RestController
@RequestMapping("/product/")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/SubCategory/{subCatId}/")
	public ResponseEntity<ProductDto> createProduct(@RequestParam("image")MultipartFile image,@RequestBody ProductDto productDto,@PathVariable Integer subCatId) throws IOException{
		String filename=this.fileService.uploadImage(path, image);
		ProductDto newProduct=this.productService.createProduct(productDto, subCatId, filename);
		return new ResponseEntity<>(newProduct,HttpStatus.CREATED);
	}

}
