package com.grocery.services.impl;

import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private ModelMapper modelMapper;
    
	
	@Override
	public ProductDto createProduct(ProductDto productDto, Integer subCatId, String filename) {
		SubCategory subCat=this.subCatRepo.findById(subCatId).orElseThrow(()->new ResourceNotFoundException("Subcategory", "SubCategoryId", subCatId ));
		Product product=this.modelMapper.map(productDto, Product.class);
		product.setProductImage(filename);
		product.setSubCat(subCat);
		float proPrice=product.getProductPrice();
		float proDis=product.getProductDiscount();
		float proAmt=this.actualAmt(proPrice, proDis);
		product.setProductAmount(proAmt);
		Product newProduct=this.productRepo.save(product);
		return this.modelMapper.map(newProduct, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, Integer subCatId, String filename, Integer productId) {
		Product product=this.productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product", "ProductId", productId));
		SubCategory subCat=this.subCatRepo.findById(subCatId).orElseThrow(()->new ResourceNotFoundException("SubCategory", "SubCatId", subCatId));
		product.setProductImage(filename);
		product.setSubCat(subCat);
		product.setProductDiscount(productDto.getProductDiscount());
		product.setProductName(productDto.getProductName());
		product.setProductPrice(productDto.getProductPrice());
		float proPrice=product.getProductPrice();
		float proDis=product.getProductDiscount();
		float proAmt=this.actualAmt(proPrice, proDis);
		product.setProductAmount(proAmt);
		Product updatedProduct=this.productRepo.save(product);
		return this.modelMapper.map(updatedProduct, ProductDto.class);
	}

	@Override
	public void deleteProduct(Integer productId) {
		this.productRepo.deleteById(productId);
		
	}

	@Override
	public ProductDto getProductById(Integer productId) {
		Product pro=this.productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product", "ProductId", productId));
		return this.modelMapper.map(pro,ProductDto.class);
	}

	@Override
	public List<ProductDto> getAllProduct() {
		List<Product>list=this.productRepo.findAll();
		List<ProductDto>product=list.stream().map(product1->this.proToDto(product1)).collect(Collectors.toList());
		return product;
	}

	@Override
	public List<ProductDto> getProductBySubCategory(Integer subCatId) {
		SubCategory subCat=this.subCatRepo.findById(subCatId).orElseThrow(()->new ResourceNotFoundException("SubCategory", "SubCategoryId", subCatId));
		//List<Product>list=this.productRepo.findBySubCat(subCat);
		//List<ProductDto>product=list.stream().map(product1->this.proToDto(product1)).collect(Collectors.toList());
		return null;
	}

	@Override
	public List<ProductDto> getProductByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ProductDto proToDto(Product pro) {
		return this.modelMapper.map(pro, ProductDto.class);
	}
	
	public float actualAmt(float price,float discount ) {
		 float actualAmount = price - (price * discount / 100);
	        return actualAmount;
	}

}
