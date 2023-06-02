package com.grocery.services;

import java.util.List;

import com.grocery.payloads.ProductDto;

public interface ProductService {

	ProductDto createProduct(ProductDto productDto,Integer subCatId,String filename);
	
	ProductDto updateProduct(ProductDto productDto,Integer subCatId,String filename,Integer productId);
	
	void deleteProduct(Integer productId);
	
	ProductDto getProductById(Integer productId);
	
	List<ProductDto>getAllProduct();
	
	List<ProductDto>getProductBySubCategory(Integer subCatId);
	
	List<ProductDto>getProductByCategory(Integer categoryId);
	
	
}
