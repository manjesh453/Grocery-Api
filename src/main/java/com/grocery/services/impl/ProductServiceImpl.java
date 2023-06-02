package com.grocery.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.entities.Product;
import com.grocery.entities.SubCategory;
import com.grocery.exceptions.ResourceNotFoundException;
import com.grocery.payloads.ProductDto;
import com.grocery.repositories.ProductRepo;
import com.grocery.repositories.SubCategoryRepo;
import com.grocery.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
	private SubCategoryRepo subCatRepo;
    @Autowired
    private ProductRepo productRepo;
    
    private ModelMapper modelMapper;
    
	
	@Override
	public ProductDto createProduct(ProductDto productDto, Integer subCatId, String filename) {
		SubCategory subCat=this.subCatRepo.findById(subCatId).orElseThrow(()->new ResourceNotFoundException("Subcategory", "SubCategoryId", subCatId ));
		Product product=this.modelMapper.map(productDto, Product.class);
		product.setProductImage(filename);
		product.setSubCat(subCat);
		Product newProduct=this.productRepo.save(product);
		return this.modelMapper.map(newProduct, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, Integer subCatId, String filename, Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduct(Integer productId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProductDto getProductById(Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDto> getAllProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDto> getProductBySubCategory(Integer subCatId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDto> getProductByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

}
