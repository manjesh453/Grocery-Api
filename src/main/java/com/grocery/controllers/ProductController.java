package com.grocery.controllers;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.hibernate.event.spi.PostDeleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.grocery.payloads.ApiResponse;
import com.grocery.payloads.ProductDto;
import com.grocery.services.FileService;
import com.grocery.services.ProductService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;
	
	@PostMapping(value="/sub/{subCatId}/")
	public ResponseEntity<ProductDto> createProduct(@RequestParam("image")MultipartFile image,@RequestPart ProductDto productDto,@PathVariable Integer subCatId) throws IOException{
		String filename=this.fileService.uploadImage(path, image);
		ProductDto newProduct=this.productService.createProduct(productDto, subCatId, filename);
		return new ResponseEntity<>(newProduct,HttpStatus.CREATED);
	}
	@PutMapping(value="/sub/{subCatId}/Product/{productId}/")
	public ResponseEntity<ProductDto>updateProduct(@RequestParam("image")MultipartFile image,@RequestPart ProductDto productDto,@PathVariable Integer subCatId,@PathVariable Integer productId) throws IOException{
		String filename=this.fileService.uploadImage(path, image);
		ProductDto updateProduct=this.productService.updateProduct(productDto, subCatId, filename, productId);
		return ResponseEntity.ok(updateProduct);
	}
    
	@DeleteMapping("/Product/{productId}/")
	public ResponseEntity<?>deleteProduct(@PathVariable Integer productId){
		this.productService.deleteProduct(productId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Product Deleted Successfully", true),HttpStatus.OK);	
	}
	@GetMapping("/Product/{productId}/")
	public ResponseEntity<ProductDto>getProductById(@PathVariable Integer productId){
		ProductDto getProduct=this.productService.getProductById(productId);
		return ResponseEntity.ok(getProduct);
	}
	@GetMapping("/Product/")
	public ResponseEntity<List<ProductDto>>getAll(){
		return ResponseEntity.ok(this.productService.getAllProduct());
	}
	@GetMapping("/sub/{subCatId}/Product/")
	public ResponseEntity<List<ProductDto>>getBySubCategory(@PathVariable Integer subCatId){
		return ResponseEntity.ok(this.productService.getProductBySubCategory(subCatId));
	}
	@GetMapping("/cat/{categoryId}/Product/")
	public ResponseEntity<List<ProductDto>>getByCategory(@PathVariable Integer categoryId){
		return ResponseEntity.ok(this.productService.getProductByCategory(categoryId));
	}
	
	@GetMapping(value = "/image/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE )
	public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse response)throws IOException {
		InputStream resource=this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
	} 
	
	@GetMapping("/keyword/{Keyword}/")
	public ResponseEntity<List<ProductDto>>getByKeyword(@PathVariable String Keyword){
		return ResponseEntity.ok(this.productService.findByKeyword(Keyword));
	}
}





















